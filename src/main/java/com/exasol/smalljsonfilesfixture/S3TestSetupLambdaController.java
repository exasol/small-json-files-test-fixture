package com.exasol.smalljsonfilesfixture;

import static java.util.logging.Level.INFO;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import jakarta.json.*;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
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
import software.amazon.awssdk.services.sts.StsClient;

/**
 * This class deploys an AWS lambda function that can create and delete a test fixture with many small JSON files on S3.
 */
class S3TestSetupLambdaController implements AutoCloseable {
    static final Region AWS_REGION = Region.EU_CENTRAL_1;
    private static final Logger LOGGER = Logger.getLogger(S3TestSetupLambdaController.class.getName());
    private static final String ACTION_CREATE = "create";
    private static final String ACTION_DELETE = "delete";
    private static final String FILE_PREFIX = "test-data-";
    private static final String CREATE_JSON_FILES_LAMBDA = "createJsonFilesLambda/createJsonFilesLambda.js";

    private final String lambdaFunctionName;
    private final String roleName;
    private final String policyName;
    private final List<Closeable> createdResources = new ArrayList<>();
    private final String accountId;
    private final String bucket;
    private final AwsCredentialsProvider credentialsProvider;
    private final Map<String, String> tags;

    private S3TestSetupLambdaController(final String accountId, final String bucket,
            final AwsCredentialsProvider credentialsProvider, final Map<String, String> tags) {
        this.accountId = accountId;
        this.bucket = bucket;
        this.credentialsProvider = credentialsProvider;
        this.tags = tags;
        this.lambdaFunctionName = "create-json-files-" + System.currentTimeMillis();
        this.roleName = this.lambdaFunctionName + "-role";
        this.policyName = this.lambdaFunctionName + "-policy";
    }

    /**
     * Create a new instance of {@link S3TestSetupLambdaController}.
     * 
     * @param tags                tags for the AWS resources
     * @param bucket              S3 Bucket
     * @param credentialsProvider AWS credentials provider
     * @return created {@link S3TestSetupLambdaController}
     * @throws IOException if something goes wrong
     */
    public static S3TestSetupLambdaController create(final Map<String, String> tags, final String bucket,
            final AwsCredentialsProvider credentialsProvider) throws IOException {
        final String accountId = StsClient.builder().credentialsProvider(credentialsProvider).region(AWS_REGION).build()
                .getCallerIdentity().account();
        final S3TestSetupLambdaController controller = new S3TestSetupLambdaController(accountId, bucket,
                credentialsProvider, tags);
        try {
            controller.deployFunction();
        } catch (final IOException exception) {
            controller.close();
            throw exception;
        }
        return controller;
    }

    /**
     * Create test files.
     * 
     * @param numberOfJsonFiles total number of files
     * @param filesPerLambda    number of files to read per lambda function
     */
    public void createFiles(final int numberOfJsonFiles, final int filesPerLambda) {
        runLambdas(numberOfJsonFiles, filesPerLambda);
    }

    /**
     * Delete the test files from the bucket.
     */
    public void deleteFiles() {
        LOGGER.info(() -> "Deleting small-json-files test setup from bucket " + this.bucket + "...");
        final Instant start = Instant.now();
        final JsonObjectBuilder eventBuilder = Json.createObjectBuilder();
        eventBuilder.add("action", ACTION_DELETE);
        eventBuilder.add("bucket", this.bucket);
        try (final var asyncLambdaClient = createAsyncLambdaClient()) {
            final CompletableFuture<InvokeResponse> future = startLambda(eventBuilder.build(), asyncLambdaClient);
            final InvokeResponse result = future.get();
            if (result.functionError() != null) {
                throw new IllegalStateException(
                        "Deleting files from bucket " + this.bucket + " failed:" + result.payload().asUtf8String());
            }
            LOGGER.info(() -> "Delete done in " + Duration.between(start, Instant.now()));
        } catch (final InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while waiting for delete-files lambda to finish.", exception);
        } catch (final ExecutionException | IllegalStateException exception) {
            throw new IllegalStateException("The delete-files lambda function failed: " + exception.getMessage(),
                    exception);
        }
    }

    private LambdaAsyncClient createAsyncLambdaClient() {
        return LambdaAsyncClient.builder().httpClient(getHttpClientWithIncreasedTimeouts())
                .credentialsProvider(this.credentialsProvider).build();
    }

    private void deployFunction() throws IOException {
        final Role role = createRoleForLambda();
        final SdkBytes zipBytes = getZippedCreateFilesLambda();
        try (final LambdaClient lambdaClient = createLambdaClient()) {
            lambdaClient.createFunction(builder -> builder.functionName(this.lambdaFunctionName)
                    .architectures(Architecture.ARM64).code(codeBuilder -> codeBuilder.zipFile(zipBytes))
                    .role(role.arn()).runtime(Runtime.NODEJS18_X).handler("createJsonFilesLambda.handler")
                    .timeout(15 * 60).tags(this.tags));
            sleep("lambda '" + this.lambdaFunctionName + "' being fully created", Duration.ofSeconds(5));
        }
        this.createdResources.add(() -> {
            try (final LambdaClient lambdaClient = createLambdaClient()) {
                lambdaClient.deleteFunction(request -> request.functionName(this.lambdaFunctionName));
            }
        });
    }

    private LambdaClient createLambdaClient() {
        return LambdaClient.builder().credentialsProvider(this.credentialsProvider).region(AWS_REGION).build();
    }

    private SdkAsyncHttpClient getHttpClientWithIncreasedTimeouts() {
        return NettyNioAsyncHttpClient.builder().readTimeout(Duration.ofMinutes(16))
                .connectionAcquisitionTimeout(Duration.ofMinutes(1)).writeTimeout(Duration.ofMinutes(1))
                .connectionTimeout(Duration.ofMinutes(1)).maxConcurrency(600).build();
    }

    private Role createRoleForLambda() {
        LOGGER.info(() -> "Creating role '" + this.roleName + "'...");
        final IamClient iamClient = IamClient.builder().region(Region.AWS_GLOBAL)
                .credentialsProvider(this.credentialsProvider).build();
        final Policy policy = iamClient
                .createPolicy(request -> request.policyName(this.policyName).policyDocument(getPolicyDocument()))
                .policy();
        this.createdResources.add(iamClient::close);
        this.createdResources.add(() -> iamClient.deletePolicy(request -> request.policyArn(policy.arn())));
        final Role role = iamClient.createRole(request -> request.roleName(this.roleName)
                .assumeRolePolicyDocument(getAssumeRolePolicyDocument()).path("/service-role/")).role();
        this.createdResources.add(() -> iamClient.deleteRole(request -> request.roleName(role.roleName())));
        iamClient.attachRolePolicy(request -> request.roleName(this.roleName).policyArn(policy.arn()));
        this.createdResources.add(
                () -> iamClient.detachRolePolicy(request -> request.policyArn(policy.arn()).roleName(role.roleName())));
        sleep("role '" + this.roleName + "' being fully created", Duration.ofSeconds(30));
        return role;
    }

    @SuppressWarnings("java:S2925") // we have no alternative to active waiting here since AWS is only eventual
                                    // consistent
    private void sleep(final String reason, final Duration duration) {
        LOGGER.info(() -> "Waiting " + duration + " for " + reason + "...");
        try {
            Thread.sleep(duration.toMillis());
        } catch (final InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while waiting.", exception);
        }
    }

    private String getPolicyDocument() {
        final String policyTemplate = getResourceAsString("createJsonFilesLambda/policy.json");
        return policyTemplate.replace("{ACCOUNT}", this.accountId).replace("{BUCKET}", this.bucket)
                .replace("{FUNCTION}", this.lambdaFunctionName);
    }

    private String getAssumeRolePolicyDocument() {
        return getResourceAsString("createJsonFilesLambda/assumeRolePolicyDocument.json");
    }

    private String getResourceAsString(final String resourceName) {
        try (final InputStream stream = getClass().getClassLoader().getResourceAsStream(resourceName)) {
            if (stream == null) {
                throw new IllegalStateException("Failed to read resource '" + resourceName + "'");
            }
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
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
                    "Number of JSON files must be a multiple of filesPerLambda (" + filesPerLambda + ").");
        }
        try (final var asyncLambdaClient = createAsyncLambdaClient()) {
            final int numberOfLambdas = numberOfJsonFiles / filesPerLambda;
            if (numberOfLambdas > 1000) {
                throw new IllegalArgumentException("More then 1000 lambdas are currently not supported.");
            }
            LOGGER.log(INFO, "Creating {0} files using {1} lambda functions in bucket {2}...",
                    new Object[] { numberOfJsonFiles, numberOfLambdas, this.bucket });
            final Instant start = Instant.now();
            final List<CompletableFuture<InvokeResponse>> lambdaFutures = new ArrayList<>(numberOfLambdas);
            for (int lambdaCounter = 0; lambdaCounter < numberOfLambdas; lambdaCounter++) {
                final JsonObject event = createLambdaEvent(filesPerLambda, lambdaCounter);
                final String lambdaDescription = "Lambda #" + lambdaCounter + " " + event.toString();
                final var future = startLambda(event, asyncLambdaClient);
                future.exceptionally(exception -> {
                    LOGGER.severe(lambdaDescription + " failed :" + exception.getMessage());
                    throw new IllegalStateException("Failed to run lambda", exception);
                });
                lambdaFutures.add(future);
            }
            waitForLambdasToFinish(lambdaFutures);
            LOGGER.info(() -> "Create done in " + Duration.between(start, Instant.now()));
        }
    }

    private JsonObject createLambdaEvent(final int filesPerLambda, final int lambdaCounter) {
        final JsonObjectBuilder eventBuilder = Json.createObjectBuilder();
        eventBuilder.add("bucket", this.bucket);
        eventBuilder.add("offset", lambdaCounter * filesPerLambda);
        eventBuilder.add("numberOfFiles", filesPerLambda);
        eventBuilder.add("prefix", FILE_PREFIX);
        eventBuilder.add("action", ACTION_CREATE);
        return eventBuilder.build();
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
            throw new IllegalStateException("Interrupted while running lambda functions.", exception);
        } catch (final ExecutionException exception) {
            throw new IllegalStateException("One or more lambda functions failed.", exception);
        }
    }

    private CompletableFuture<InvokeResponse> startLambda(final JsonObject event,
            final LambdaAsyncClient asyncLambdaClient) {
        try {
            final byte[] serializedEvent = serializeJson(event);
            return asyncLambdaClient.invoke(request -> request.functionName(this.lambdaFunctionName)
                    .invocationType(InvocationType.REQUEST_RESPONSE).payload(SdkBytes.fromByteArray(serializedEvent)));
        } catch (final IOException exception) {
            throw new UncheckedIOException("Failed to start lambda function.", exception);
        }
    }

    private byte[] serializeJson(final JsonObject json) throws IOException {
        try (final ByteArrayOutputStream bufferStream = new ByteArrayOutputStream();
                final JsonWriter jsonWriter = Json.createWriter(bufferStream)) {
            jsonWriter.write(json);
            return bufferStream.toByteArray();
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
