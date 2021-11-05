package com.exasol.smalljsonfilesfixture;

import static java.util.logging.Level.INFO;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import jakarta.json.*;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.http.async.SdkAsyncHttpClient;
import software.amazon.awssdk.http.nio.netty.NettyNioAsyncHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.Policy;
import software.amazon.awssdk.services.iam.model.Role;
import software.amazon.awssdk.services.lambda.LambdaAsyncClient;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.*;
import software.amazon.awssdk.services.lambda.model.Runtime;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.sts.StsClient;

/**
 * This class deploys a AWS lambda function that can create and delete a test fixture with many small JSON files on S3.
 */
public class S3TestSetupLambdaController implements AutoCloseable {
    private static final String LAMBDA_FUNCTION_NAME = "create-json-files";
    private static final String ROLE_NAME = LAMBDA_FUNCTION_NAME + "-role";
    private static final String POLICY_NAME = LAMBDA_FUNCTION_NAME + "-policy";
    private static final Logger LOGGER = Logger.getLogger(S3TestSetupLambdaController.class.getName());
    private static final String ACTION_CREATE = "create";
    private static final String ACTION_DELETE = "delete";
    private static final String FILE_PREFIX = "test-data-";
    private static final String CREATE_JSON_FILES_LAMBDA = "createJsonFilesLambda/createJsonFilesLambda.js";
    private static final String SETUP_DESCRIPTION_KEY = ".setup-description.json";
    private final List<Closeable> createdResources = new ArrayList<>();
    private final String accountId;
    private final String bucket;
    private final AwsCredentialsProvider credentialsProvider;
    private final LambdaAsyncClient asyncLambdaClient;
    private final Map<String, String> tags;

    /**
     * Create a new instance of {@link S3TestSetupLambdaController}.
     * 
     * @param tags                tags for the AWS resources
     * @param bucket              s3-bucket
     * @param credentialsProvider AWS credentials provider
     * @throws IOException if something goes wrong
     */
    public S3TestSetupLambdaController(final Map<String, String> tags, final String bucket,
            final AwsCredentialsProvider credentialsProvider) throws IOException {
        this.tags = tags;
        this.bucket = bucket;
        this.credentialsProvider = credentialsProvider;
        try {
            this.accountId = StsClient.builder().credentialsProvider(credentialsProvider).build().getCallerIdentity()
                    .account();
            final LambdaClient lambdaClient = LambdaClient.builder().credentialsProvider(credentialsProvider).build();
            this.asyncLambdaClient = LambdaAsyncClient.builder().httpClient(getHttpClientWithIncreasedTimeouts())
                    .credentialsProvider(credentialsProvider).build();
            this.createdResources.add(this.asyncLambdaClient::close);
            deployFunction(credentialsProvider, lambdaClient);
        } catch (final Exception exception) {
            close();
            throw exception;
        }
    }

    /**
     * Create test files.
     * 
     * @param numberOfJsonFiles total number of files
     * @param filesPerLambda    number of files to read per lambda function
     */
    public void createFiles(final int numberOfJsonFiles, final int filesPerLambda) {
        final TestSetupDescription testSetupDescription = new TestSetupDescription(numberOfJsonFiles,
                getLambdaFunctionHash());
        if (isCached(testSetupDescription)) {
            LOGGER.info("Test setup is already in-place");
        } else {
            deleteFiles();
            runLambdas(numberOfJsonFiles, filesPerLambda);
            uploadSetupDescription(testSetupDescription);
        }
    }

    private void uploadSetupDescription(final TestSetupDescription testSetupDescription) {
        try (final S3Client s3Client = S3Client.builder().credentialsProvider(this.credentialsProvider).build()) {
            s3Client.putObject(request -> request.bucket(this.bucket).key(SETUP_DESCRIPTION_KEY),
                    RequestBody.fromString(testSetupDescription.toJson()));
        }
    }

    private boolean isCached(final TestSetupDescription requestedSetup) {
        final Optional<TestSetupDescription> actualSetup = fetchSetupDescription();
        if (actualSetup.isEmpty()) {
            return false;
        } else {
            return actualSetup.get().equals(requestedSetup);
        }
    }

    private Optional<TestSetupDescription> fetchSetupDescription() {
        try (final S3Client s3Client = S3Client.builder().credentialsProvider(this.credentialsProvider).build()) {
            final String json = new String(s3Client
                    .getObject(request -> request.bucket(this.bucket).key(SETUP_DESCRIPTION_KEY)).readAllBytes(),
                    StandardCharsets.UTF_8);
            return Optional.of(TestSetupDescription.fromJson(json));
        } catch (final NoSuchKeyException exception) {
            return Optional.empty();
        } catch (final IOException exception) {
            throw new IllegalStateException("Failed to fetch setup description.", exception);
        }
    }

    /**
     * Delete the test files from the bucket.
     */
    public void deleteFiles() {
        LOGGER.info("Deleting small-json-files test setup");
        final JsonObjectBuilder eventBuilder = Json.createObjectBuilder();
        eventBuilder.add("action", ACTION_DELETE);
        eventBuilder.add("bucket", this.bucket);
        final CompletableFuture<InvokeResponse> future = startLambda(eventBuilder);
        try {
            final InvokeResponse result = future.get();
            if (result.functionError() != null) {
                throw new IllegalStateException(result.payload().asUtf8String());
            }
            LOGGER.info("Delete done");
        } catch (final InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while waiting for delete-files lambda to finish.", exception);
        } catch (final ExecutionException | IllegalStateException exception) {
            throw new IllegalStateException("The delete-files lambda function failed.", exception);
        }
    }

    private void deployFunction(final AwsCredentialsProvider credentialsProvider, final LambdaClient lambdaClient)
            throws IOException {
        final Role role = createRoleForLambda(credentialsProvider);
        final SdkBytes zipBytes = getZippedCreateFilesLambda();
        lambdaClient.createFunction(builder -> builder.functionName(LAMBDA_FUNCTION_NAME)
                .architectures(Architecture.ARM64).code(FunctionCode.builder().zipFile(zipBytes).build())
                .role(role.arn()).runtime(Runtime.NODEJS14_X).handler("createJsonFilesLambda.handler").timeout(15 * 60)
                .tags(this.tags));
        this.createdResources
                .add(() -> lambdaClient.deleteFunction(request -> request.functionName(LAMBDA_FUNCTION_NAME)));
    }

    private SdkAsyncHttpClient getHttpClientWithIncreasedTimeouts() {
        return NettyNioAsyncHttpClient.builder().readTimeout(Duration.ofMinutes(16))
                .connectionAcquisitionTimeout(Duration.ofMinutes(1)).writeTimeout(Duration.ofMinutes(1))
                .connectionTimeout(Duration.ofMinutes(1)).maxConcurrency(600).build();
    }

    private Role createRoleForLambda(final AwsCredentialsProvider credentialsProvider) {
        final IamClient iamClient = IamClient.builder().region(Region.AWS_GLOBAL)
                .credentialsProvider(credentialsProvider).build();
        final Policy policy = iamClient
                .createPolicy(request -> request.policyName(POLICY_NAME).policyDocument(getPolicyDocument())).policy();
        this.createdResources.add(() -> iamClient.deletePolicy(request -> request.policyArn(policy.arn())));
        final Role role = iamClient.createRole(request -> request.roleName(ROLE_NAME)
                .assumeRolePolicyDocument(getAssumeRolePolicyDocument()).path("/service-role/")).role();
        this.createdResources.add(() -> iamClient.deleteRole(request -> request.roleName(role.roleName())));
        iamClient.attachRolePolicy(request -> request.roleName(ROLE_NAME).policyArn(policy.arn()));
        this.createdResources.add(
                () -> iamClient.detachRolePolicy(request -> request.policyArn(policy.arn()).roleName(role.roleName())));
        waitForRoleBeingFullyCreated();
        return role;
    }

    @SuppressWarnings("java:S2925") // we have no alternative to active waiting here since AWS is only eventual
                                    // consistent
    private void waitForRoleBeingFullyCreated() {
        LOGGER.log(INFO, "Waiting for lambda role...");
        try {
            Thread.sleep(30000);
        } catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while waiting.");
        }
    }

    private String getPolicyDocument() {
        final String policyTemplate = getResourceAsString("createJsonFilesLambda/policy.json");
        return policyTemplate.replace("{ACCOUNT}", this.accountId).replace("{BUCKET}", this.bucket);
    }

    private String getAssumeRolePolicyDocument() {
        return getResourceAsString("createJsonFilesLambda/assumeRolePolicyDocument.json");
    }

    private String getResourceAsString(final String resourceName) {
        try (final InputStream stream = getClass().getClassLoader().getResourceAsStream(resourceName)) {
            return new String(Objects.requireNonNull(stream).readAllBytes(), StandardCharsets.UTF_8);
        } catch (final IOException exception) {
            throw new UncheckedIOException("Failed to read test resource '" + resourceName + "'.", exception);
        }
    }

    private SdkBytes getZippedCreateFilesLambda() throws IOException {
        try (final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            try (final InputStream lambdaStream = Objects
                    .requireNonNull(getClass().getClassLoader().getResourceAsStream(CREATE_JSON_FILES_LAMBDA));
                    final ZipOutputStream zip = new ZipOutputStream(byteArrayOutputStream)) {
                final ZipEntry entry = new ZipEntry("createJsonFilesLambda.js");
                zip.putNextEntry(entry);
                lambdaStream.transferTo(zip);
                zip.closeEntry();
            }
            return SdkBytes.fromByteArray(byteArrayOutputStream.toByteArray());
        }
    }

    private void runLambdas(final int numberOfJsonFiles, final int filesPerLambda) {
        if (numberOfJsonFiles % filesPerLambda != 0) {
            throw new IllegalArgumentException(
                    "Number of JSON files must be a multiple of filesPerLambda(" + filesPerLambda + ").");
        }
        final int numberOfLambdas = numberOfJsonFiles / filesPerLambda;
        if (numberOfLambdas > 1000) {
            throw new IllegalArgumentException("More then 1000 lambdas are currently not supported.");
        }
        LOGGER.log(INFO, "Creating {0} files using {1} lambda functions.",
                new Object[] { numberOfJsonFiles, numberOfLambdas });
        final List<CompletableFuture<InvokeResponse>> lambdaFutures = new ArrayList<>(numberOfLambdas);
        for (int lambdaCounter = 0; lambdaCounter < numberOfLambdas; lambdaCounter++) {
            final JsonObjectBuilder eventBuilder = Json.createObjectBuilder();
            eventBuilder.add("bucket", this.bucket);
            eventBuilder.add("offset", lambdaCounter * filesPerLambda);
            eventBuilder.add("numberOfFiles", filesPerLambda);
            eventBuilder.add("prefix", FILE_PREFIX);
            eventBuilder.add("action", ACTION_CREATE);
            final var future = startLambda(eventBuilder);
            future.exceptionally(exception -> {
                LOGGER.severe("lambda error:" + exception.getMessage());
                throw new IllegalStateException("Failed to run lambda", exception);
            });
            lambdaFutures.add(future);
        }
        waitForLambdasToFinish(lambdaFutures);
        LOGGER.log(INFO, "create done");
    }

    private void waitForLambdasToFinish(final List<CompletableFuture<InvokeResponse>> lambdaFutures) {
        try {
            final CompletableFuture<Void> combinedFuture = CompletableFuture
                    .allOf(lambdaFutures.toArray(CompletableFuture[]::new));
            combinedFuture.get();
            for (final var future : lambdaFutures) {
                final InvokeResponse invokeResponse = future.get();
                if (invokeResponse.functionError() != null) {
                    throw new IllegalStateException("Failed to run lambda function: "
                            + invokeResponse.payload().asString(StandardCharsets.UTF_8));
                }
            }
        } catch (final InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while running lambda functions.");
        } catch (final ExecutionException exception) {
            throw new IllegalStateException("One or more lambda functions failed.", exception);
        }
    }

    private CompletableFuture<InvokeResponse> startLambda(final JsonObjectBuilder eventBuilder) {
        try {
            final byte[] serializedEvent = serializeJson(eventBuilder);
            return this.asyncLambdaClient.invoke(request -> request.functionName(LAMBDA_FUNCTION_NAME)
                    .invocationType(InvocationType.REQUEST_RESPONSE).payload(SdkBytes.fromByteArray(serializedEvent)));
        } catch (final IOException exception) {
            throw new UncheckedIOException("Failed to start lambda function.", exception);
        }
    }

    private byte[] serializeJson(final JsonObjectBuilder eventBuilder) throws IOException {
        try (final ByteArrayOutputStream bufferStream = new ByteArrayOutputStream();
                final JsonWriter jsonWriter = Json.createWriter(bufferStream)) {
            jsonWriter.write(eventBuilder.build());
            return bufferStream.toByteArray();
        }
    }

    private String getLambdaFunctionHash() {
        try {
            final var hashBuilder = MessageDigest.getInstance("sha512");
            try (final InputStream lambdaAsStream = Objects
                    .requireNonNull(getClass().getClassLoader().getResourceAsStream(CREATE_JSON_FILES_LAMBDA))) {
                final byte[] byteArray = new byte[1024];
                int readBytes = 0;
                while ((readBytes = lambdaAsStream.read(byteArray)) != -1) {
                    hashBuilder.update(byteArray, 0, readBytes);
                }
            }
            final byte[] hash = hashBuilder.digest();
            final StringBuilder hashStringBuilder = new StringBuilder();
            for (final byte hashByte : hash) {
                hashStringBuilder.append(Integer.toString((hashByte & 0xff) + 0x100, 16).substring(1));
            }
            return hashStringBuilder.toString();
        } catch (final Exception exception) {
            throw new IllegalStateException("Failed to build hash-code of lambda function", exception);
        }
    }

    @Override
    public void close() throws IOException {
        Collections.reverse(this.createdResources);
        for (final Closeable resource : this.createdResources) {
            resource.close();
        }
    }
}
