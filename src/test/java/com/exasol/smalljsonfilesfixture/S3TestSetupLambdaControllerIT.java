package com.exasol.smalljsonfilesfixture;

import static com.exasol.smalljsonfilesfixture.S3TestUtils.countDataFiles;
import static com.exasol.smalljsonfilesfixture.S3TestUtils.emptyS3Bucket;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOException;

import org.junit.jupiter.api.*;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;

class S3TestSetupLambdaControllerIT {
    private static final TestConfig TEST_CONFIG = TestConfig.instance();
    private static S3Client s3Client;
    private static S3TestSetupLambdaController controller;
    private static String bucketName;

    @BeforeAll
    static void beforeAll() throws IOException {
        bucketName = "small-json-files-test-fixture-" + System.currentTimeMillis();
        controller = S3TestSetupLambdaController.create(TEST_CONFIG.getTags(), bucketName,
                TEST_CONFIG.getAwsCredentialsProvider());
        s3Client = S3Client.builder().credentialsProvider(TEST_CONFIG.getAwsCredentialsProvider()).build();
        s3Client.createBucket(request -> request.bucket(bucketName));
    }

    @AfterAll
    static void afterAll() throws IOException {
        if (controller != null) {
            controller.close();
        }
        if (s3Client != null) {
            s3Client.deleteBucket(request -> request.bucket(bucketName));
        }
    }

    @AfterEach
    void afterEach() {
        emptyS3Bucket(bucketName, s3Client);
    }

    @Test
    void testCreateFiles() {
        final int fileCount = 100;
        controller.createFiles(fileCount, 10);
        assertThat(countDataFiles(s3Client, bucketName), equalTo(fileCount));
    }

    @Test
    void testDeleteFiles() {
        s3Client.putObject(request -> request.bucket(bucketName).key("test-data-1.json"),
                RequestBody.fromString("myObject"));
        controller.deleteFiles();
        assertThat("the file was not deleted", countDataFiles(s3Client, bucketName), equalTo(0));
    }

    @Test
    void testDeleteFilesFromEmptyBucketSucceeds() {
        assertDoesNotThrow(controller::deleteFiles);
        assertThat("bucket is still empty", countDataFiles(s3Client, bucketName), equalTo(0));
    }
}