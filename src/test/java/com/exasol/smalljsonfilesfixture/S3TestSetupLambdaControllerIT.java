package com.exasol.smalljsonfilesfixture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.Map;

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
        controller = S3TestSetupLambdaController.create(
                Map.of("exa:owner", TEST_CONFIG.getOwner(), "exa:project", "SJFTF"), bucketName,
                TEST_CONFIG.getAwsCredentialsProvider());
        s3Client = S3Client.builder().credentialsProvider(TEST_CONFIG.getAwsCredentialsProvider()).build();
        s3Client.createBucket(request -> request.bucket(bucketName));
    }

    @AfterAll
    static void afterAll() throws IOException {
        controller.close();
        s3Client.deleteBucket(request -> request.bucket(bucketName));
    }

    @AfterEach
    void afterEach() {
        emptyS3Bucket(bucketName);
    }

    @Test
    void testCreateFiles() {
        final int fileCount = 100;
        controller.createFiles(fileCount, 10);
        assertThat(countDataFiles(), equalTo(fileCount));
    }

    @Test
    void testCreateFileWithCache() {
        final int fileCount = 100;
        controller.createFiles(fileCount, 10);
        final long start = System.currentTimeMillis();
        controller.createFiles(fileCount, 10);
        final int elapsed = (int) (System.currentTimeMillis() - start);
        assertAll(//
                () -> assertThat(elapsed, lessThan(1000)), //
                () -> assertThat(countDataFiles(), equalTo(fileCount))//
        );
    }

    @Test
    void testInvalidateCache() {
        controller.createFiles(100, 10);
        final int newFileCount = 10;
        controller.createFiles(newFileCount, 10);
        assertThat(countDataFiles(), equalTo(newFileCount));
    }

    @Test
    void testDeleteFiles() {
        s3Client.putObject(request -> request.bucket(bucketName).key("test-data-1.json"),
                RequestBody.fromString("myObject"));
        controller.deleteFiles();
        assertThat("the file was not deleted", countDataFiles(), equalTo(0));
    }

    @Test
    void testTooManyLambdasException() {
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> controller.createFiles(2000, 1));
        assertThat(exception.getMessage(), equalTo("More then 1000 lambdas are currently not supported."));
    }

    @Test
    void testNotAMultiple() {
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> controller.createFiles(10, 3));
        assertThat(exception.getMessage(), equalTo("Number of JSON files must be a multiple of filesPerLambda(3)."));
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