package com.exasol.smalljsonfilesfixture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.junit.jupiter.api.*;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.paginators.ListObjectsV2Iterable;

class S3TestSetupLambdaControllerIT {
    private static final TestConfig TEST_CONFIG = TestConfig.instance();
    private static S3Client s3Client;
    private static S3TestSetupLambdaController controller;
    private static String bucketName;

    @BeforeAll
    static void beforeAll() throws IOException {
        bucketName = "small-json-files-test-fixture-" + System.currentTimeMillis();
        controller = new S3TestSetupLambdaController(TEST_CONFIG.getOwner(), bucketName,
                TEST_CONFIG.getAwsCredentialsProvider());
        s3Client = S3Client.builder().credentialsProvider(TEST_CONFIG.getAwsCredentialsProvider()).build();
    }

    @AfterAll
    static void afterAll() throws IOException {
        controller.close();
        s3Client.deleteBucket(request -> request.bucket(bucketName));
    }

    @BeforeEach
    void beforeEach() {
        s3Client.createBucket(request -> request.bucket(bucketName));
    }

    @AfterEach
    void afterEach() {
        emptyS3Bucket(bucketName);
    }

    @Test
    void testCreateFiles() throws IOException {
        final int fileCount = 100;
        controller.createFiles(fileCount, 10);
        assertThat(countDataFiles(), equalTo(fileCount));
    }

    @Test
    void testDeleteFiles() {
        s3Client.putObject(request -> request.bucket(bucketName).key("test-data-1.json"),
                RequestBody.fromString("myObject"));
        controller.deleteFiles();
        assertThat("the file was not deleted", countDataFiles(), equalTo(0));
    }

    private void emptyS3Bucket(final String bucketName) {
        final ListObjectsV2Iterable pages = s3Client.listObjectsV2Paginator(request -> request.bucket(bucketName));
        for (final ListObjectsV2Response page : pages) {
            page.contents().forEach(
                    s3Object -> s3Client.deleteObject(builder -> builder.bucket(bucketName).key(s3Object.key())));
        }
    }

    private int countDataFiles() {
        final var pages = s3Client.listObjectsV2Paginator(request -> request.bucket(bucketName).prefix("test-data"));
        int objectCounter = 0;
        for (final ListObjectsV2Response page : pages) {
            objectCounter += page.contents().size();
        }
        return objectCounter;
    }
}