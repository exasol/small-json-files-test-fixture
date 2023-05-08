package com.exasol.smalljsonfilesfixture;

import static java.util.logging.Level.INFO;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import jakarta.json.*;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.http.SdkHttpClient;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.Policy;
import software.amazon.awssdk.services.iam.model.Role;
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
    /** Using too many concurrent executions will cause S3 PUT requests to fail with a "SlowDown" error. */
    private static final int CONCURRENT_LAMBDA_EXECUTIONS = 150;
    private static final String ACTION_CREATE = "create";
    private static final String ACTION_DELETE = "delete";
    private static final String FILE_PREFIX = "test-data-";

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
     * <p>
     * Please note that the caller is responsible for {@link #close() closing} this controller.
     *
     * @param tags                tags for the AWS resources
     * @param bucket              S3 Bucket
     * @param credentialsProvider AWS credentials provider
     * @return created {@link S3TestSetupLambdaController}
     */
    public static S3TestSetupLambdaController create(final Map<String, String> tags, final String bucket,
            final AwsCredentialsProvider credentialsProvider) {
        final String accountId = StsClient.builder().credentialsProvider(credentialsProvider).region(AWS_REGION).build()
                .getCallerIdentity().account();
        @SuppressWarnings("java:S2095") // The caller is responsible for closing the controller.
        final S3TestSetupLambdaController controller = new S3TestSetupLambdaController(accountId, bucket,
                credentialsProvider, tags);
        try {
            controller.deployFunction();
        } catch (final RuntimeException exception) {
            controller.close();
            throw new IllegalStateException("Failed to deploy lambda", exception);
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
        try (final var lambdaClient = createLambdaClient()) {
            final InvokeResponse result = startLambda(eventBuilder.build(), lambdaClient);
            if (result.functionError() != null) {
                throw new IllegalStateException(
                        "Deleting files from bucket " + this.bucket + " failed:" + result.payload().asUtf8String());
            }
            LOGGER.info(() -> "Delete done in " + Duration.between(start, Instant.now()));
        }
    }

    private void deployFunction() {
        final Role role = createRoleForLambda();
        final SdkBytes zipBytes = getZippedCreateFilesLambda();
        try (final LambdaClient lambdaClient = createLambdaClient()) {
            lambdaClient.createFunction(
                    builder -> builder.functionName(this.lambdaFunctionName).architectures(Architecture.ARM64) //
                            .code(codeBuilder -> codeBuilder.zipFile(zipBytes)) //
                            .role(role.arn()) //
                            .runtime(Runtime.NODEJS18_X) //
                            .handler("createJsonFilesLambda.handler") //
                            .timeout(15 * 60) //
                            .tags(this.tags));
            sleep("lambda '" + this.lambdaFunctionName + "' being fully created", Duration.ofSeconds(5));
        }
        this.createdResources.add(() -> {
            try (final LambdaClient lambdaClient = createLambdaClient()) {
                LOGGER.fine(() -> "Deleting lambda " + this.lambdaFunctionName);
                lambdaClient.deleteFunction(request -> request.functionName(this.lambdaFunctionName));
            }
        });
    }

    private LambdaClient createLambdaClient() {
        return LambdaClient.builder().credentialsProvider(this.credentialsProvider).region(AWS_REGION)
                .httpClient(getHttpClientWithIncreasedTimeouts()).build();
    }

    private SdkHttpClient getHttpClientWithIncreasedTimeouts() {
        final Duration timeout = Duration.ofMinutes(1);
        return ApacheHttpClient.builder() //
                .socketTimeout(Duration.ofMinutes(16)) //
                .connectionAcquisitionTimeout(Duration.ofMinutes(10)) //
                .connectionTimeout(timeout) //
                .build();
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

    private byte[] readResource(final String resourceName) {
        final URL resource = getClass().getClassLoader().getResource(resourceName);
        if (resource == null) {
            throw new IllegalStateException("Resource '" + resourceName + "' not found");
        }
        try (InputStream stream = resource.openStream()) {
            return stream.readAllBytes();
        } catch (final IOException exception) {
            throw new UncheckedIOException("Failed to read resource '" + resourceName + "'", exception);
        }
    }

    private SdkBytes getZippedCreateFilesLambda() {
        try (final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            try (final ZipOutputStream zip = new ZipOutputStream(byteArrayOutputStream)) {
                zip.putNextEntry(new ZipEntry("createJsonFilesLambda.js"));
                zip.write(readResource("createJsonFilesLambda/createJsonFilesLambda.js"));
                zip.closeEntry();
                zip.putNextEntry(new ZipEntry("package.json"));
                zip.write(readResource("createJsonFilesLambda/package.json"));
                zip.closeEntry();
            }
            return SdkBytes.fromByteArray(byteArrayOutputStream.toByteArray());
        } catch (final IOException exception) {
            throw new UncheckedIOException("Failed to create zipped lambda package", exception);
        }
    }

    private void runLambdas(final int files, final int filesPerLambda) {
        final Packager packager = new Packager(files, filesPerLambda);
        final ExecutorService executor = Executors.newFixedThreadPool(CONCURRENT_LAMBDA_EXECUTIONS);
        try (final var lambdaClient = createLambdaClient()) {
            final int lambdas = packager.getNumberOfPackages();
            LOGGER.log(INFO, "Creating {0} files using {1} lambda functions (max concurrency: {2}) in bucket {3}...",
                    new Object[] { files, lambdas, CONCURRENT_LAMBDA_EXECUTIONS, this.bucket });
            final Instant start = Instant.now();
            for (final Packager.Package pkg : packager) {
                executor.execute(() -> startLambdaForPackage(lambdaClient, pkg));
            }
            LOGGER.info(() -> "Waiting for " + lambdas + " lambdas to complete...");
            executor.shutdown();
            try {
                executor.awaitTermination(30, TimeUnit.MINUTES);
            } catch (final InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
            LOGGER.info(() -> "Create done in " + Duration.between(start, Instant.now()));
        } finally {
            executor.shutdownNow();
        }
    }

    private void startLambdaForPackage(final LambdaClient lambdaClient, final Packager.Package pkg) {
        final JsonObject event = createLambdaEvent(pkg.getSize(), pkg.getNumber());
        final String lambdaDescription = "Lambda #" + pkg.getNumber() + " of " + pkg.getTotalCount();
        try {
            LOGGER.info(() -> "Starting " + lambdaDescription + "...");
            final Instant start = Instant.now();
            final InvokeResponse result = startLambda(event, lambdaClient);
            LOGGER.info(() -> lambdaDescription + " finished with status " + result.statusCode() + " and payload '"
                    + result.payload().asUtf8String() + "' after " + Duration.between(start, Instant.now()));
        } catch (final RuntimeException exception) {
            final String message = lambdaDescription + " failed: " + exception.getMessage();
            LOGGER.severe(message);
            throw new IllegalStateException(message, exception);
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

    private InvokeResponse startLambda(final JsonObject event, final LambdaClient lambdaClient) {
        final byte[] serializedEvent = serializeJson(event);
        return lambdaClient.invoke(request -> request.functionName(this.lambdaFunctionName) //
                .invocationType(InvocationType.REQUEST_RESPONSE) //
                .logType(LogType.TAIL) //
                .payload(SdkBytes.fromByteArray(serializedEvent)));
    }

    private byte[] serializeJson(final JsonObject json) {
        try (final ByteArrayOutputStream bufferStream = new ByteArrayOutputStream();
                final JsonWriter jsonWriter = Json.createWriter(bufferStream)) {
            jsonWriter.write(json);
            return bufferStream.toByteArray();
        } catch (final IOException exception) {
            throw new UncheckedIOException("Failed to serialize json object " + json, exception);
        }
    }

    @Override
    public void close() {
        Collections.reverse(this.createdResources);
        LOGGER.fine(() -> "Closing/deleting " + this.createdResources.size() + " resources...");
        try {
            for (final Closeable resource : this.createdResources) {
                resource.close();
            }
        } catch (final IOException exception) {
            throw new UncheckedIOException("Failed to delete resources. Please delete them manually.", exception);
        }
    }
}
