package com.exasol.smalljsonfilesfixture;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;
import java.util.logging.Logger;

import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;

/**
 * This class creates a test setup with small JSON files on AWS S3. Since creating is slow and expensive this class
 * checks if the setup is already there before creating it.
 */
public class SmallJsonFilesTestSetup {
    private static final String CREATE_JSON_FILES_LAMBDA = "createJsonFilesLambda/createJsonFilesLambda.js";
    private static final Logger LOGGER = Logger.getLogger(SmallJsonFilesTestSetup.class.getName());
    private static final String SETUP_DESCRIPTION_KEY = ".setup-description.json";
    /**
     * Maximum number of files generated by a single lambda.
     * <p>
     * Please note that generating more than 5,000 files per lambda may fail with a
     * {@link io.netty.handler.timeout.ReadTimeoutException} when running with GitHub Actions.
     */
    private static final int MAX_FILE_COUNT_PER_LAMBDA = 5_000;

    private final Map<String, String> tags;
    private final AwsCredentialsProvider credentialsProvider;
    private final String bucket;

    /**
     * Create a new instance.
     *
     * @param credentialsProvider the AWS credential provider
     * @param tags                the tags to use for resources created in AWS
     * @param bucket              S3 bucket name
     */
    public SmallJsonFilesTestSetup(final AwsCredentialsProvider credentialsProvider, final Map<String, String> tags,
            final String bucket) {
        this.credentialsProvider = credentialsProvider;
        this.tags = tags;
        this.bucket = bucket;
    }

    /**
     * Create the test fixture.
     *
     * @param numberOfJsonFiles total number of files
     */
    public void setup(final int numberOfJsonFiles) {
        setup(numberOfJsonFiles, MAX_FILE_COUNT_PER_LAMBDA);
    }

    /**
     * Create the test fixture.
     *
     * @param numberOfJsonFiles total number of files
     * @param filesPerLambda    number of files to read per lambda function
     */
    void setup(final int numberOfJsonFiles, final int filesPerLambda) {
        final TestSetupDescription testSetupDescription = new TestSetupDescription(numberOfJsonFiles,
                getLambdaFunctionHash());
        if (isCached(testSetupDescription, this.credentialsProvider)) {
            LOGGER.info("Test setup is already in-place");
        } else {
            try (final S3TestSetupLambdaController controller = S3TestSetupLambdaController.create(this.tags,
                    this.bucket, this.credentialsProvider)) {
                controller.deleteFiles();
                controller.createFiles(numberOfJsonFiles, filesPerLambda);
                uploadSetupDescription(testSetupDescription, this.credentialsProvider);
            }
        }
    }

    private void uploadSetupDescription(final TestSetupDescription testSetupDescription,
            final AwsCredentialsProvider credentialsProvider) {
        try (final S3Client s3Client = createS3Client(credentialsProvider)) {
            s3Client.putObject(request -> request.bucket(this.bucket).key(SETUP_DESCRIPTION_KEY),
                    RequestBody.fromString(testSetupDescription.toJson()));
        }
    }

    private boolean isCached(final TestSetupDescription requestedSetup,
            final AwsCredentialsProvider credentialsProvider) {
        final Optional<TestSetupDescription> actualSetup = fetchSetupDescription(credentialsProvider, this.bucket);
        if (actualSetup.isEmpty()) {
            LOGGER.info(() -> "Setup description does not exist at s3://" + this.bucket + "/" + SETUP_DESCRIPTION_KEY);
            return false;
        } else {
            if (!actualSetup.get().equals(requestedSetup)) {
                LOGGER.info(() -> "Actual setup " + actualSetup.get() + " does not match requested configuration "
                        + requestedSetup);
                return false;
            } else {
                LOGGER.info(() -> "Actual setup " + actualSetup.get() + " matches requested configuration "
                        + requestedSetup);
                return true;
            }
        }
    }

    private String getLambdaFunctionHash() {
        try {
            final var hashBuilder = MessageDigest.getInstance("SHA-256");
            try (final InputStream lambdaAsStream = Objects.requireNonNull(
                    getClass().getClassLoader().getResourceAsStream(CREATE_JSON_FILES_LAMBDA),
                    "Resource " + CREATE_JSON_FILES_LAMBDA + " not found")) {
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

    private Optional<TestSetupDescription> fetchSetupDescription(final AwsCredentialsProvider credentialsProvider,
            final String bucket) {
        try (final S3Client s3Client = createS3Client(credentialsProvider)) {
            final String json = new String(
                    s3Client.getObject(request -> request.bucket(bucket).key(SETUP_DESCRIPTION_KEY)).readAllBytes(),
                    StandardCharsets.UTF_8);
            return Optional.of(TestSetupDescription.fromJson(json));
        } catch (final NoSuchKeyException exception) {
            return Optional.empty();
        } catch (final IOException exception) {
            throw new UncheckedIOException("Failed to fetch setup description.", exception);
        }
    }

    private S3Client createS3Client(final AwsCredentialsProvider credentialsProvider) {
        return S3Client.builder().credentialsProvider(credentialsProvider)
                .region(S3TestSetupLambdaController.AWS_REGION).build();
    }
}
