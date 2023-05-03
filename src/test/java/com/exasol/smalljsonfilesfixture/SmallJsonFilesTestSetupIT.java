package com.exasol.smalljsonfilesfixture;

import static com.exasol.smalljsonfilesfixture.S3TestUtils.countDataFiles;
import static com.exasol.smalljsonfilesfixture.S3TestUtils.emptyS3Bucket;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.io.IOException;
import java.util.logging.Logger;

import org.junit.jupiter.api.*;

import software.amazon.awssdk.services.s3.S3Client;

class SmallJsonFilesTestSetupIT {
    private static final Logger LOG = Logger.getLogger(SmallJsonFilesTestSetupIT.class.getName());
    private static final TestConfig TEST_CONFIG = TestConfig.instance();
    private static S3Client s3Client;
    private static String bucketName;

    @BeforeAll
    static void beforeAll() {
        bucketName = "small-json-files-test-fixture-" + System.currentTimeMillis();
        s3Client = S3Client.builder().credentialsProvider(TEST_CONFIG.getAwsCredentialsProvider()).build();
        s3Client.createBucket(request -> request.bucket(bucketName));
    }

    @AfterAll
    static void afterAll() {
        LOG.info(() -> "Deleting bucket " + bucketName + "...");
        s3Client.deleteBucket(request -> request.bucket(bucketName));
    }

    @AfterEach
    void afterEach() {
        emptyS3Bucket(bucketName, s3Client);
    }

    @Test
    void testCreateFileWithCache() throws IOException {
        final int fileCount = 100;
        createSetup(fileCount);
        final long start = System.currentTimeMillis();
        createSetup(fileCount);
        final int elapsed = (int) (System.currentTimeMillis() - start);
        assertAll(//
                () -> assertThat("elapsed time [ms]", elapsed, lessThan(1000)), //
                () -> assertThat("number of files", countDataFiles(s3Client, bucketName), equalTo(fileCount))//
        );
    }

    @Test
    void testManyFilesPerLambda() throws IOException {
        final int count = 20_000;
        final int countPerLambda = count;
        createSetup(count, countPerLambda);
        assertThat(countDataFiles(s3Client, bucketName), equalTo(count));
    }

    private void createSetup(final int fileCount) throws IOException {
        createSetup(fileCount, 10);
    }

    private void createSetup(final int fileCount, final int filesPerLambda) throws IOException {
        new SmallJsonFilesTestSetup().setup(TEST_CONFIG.getTags(), bucketName, TEST_CONFIG.getAwsCredentialsProvider(),
                fileCount, filesPerLambda);
    }

    @Test
    void testInvalidateCache() throws IOException {
        createSetup(100);
        final int newFileCount = 10;
        createSetup(newFileCount);
        assertThat(countDataFiles(s3Client, bucketName), equalTo(newFileCount));
    }
}