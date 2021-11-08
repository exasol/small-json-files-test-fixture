package com.exasol.smalljsonfilesfixture;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.paginators.ListObjectsV2Iterable;

public class S3TestUtils {
    public static void emptyS3Bucket(final String bucketName, final S3Client s3Client) {
        final ListObjectsV2Iterable pages = s3Client.listObjectsV2Paginator(request -> request.bucket(bucketName));
        for (final ListObjectsV2Response page : pages) {
            page.contents().forEach(
                    s3Object -> s3Client.deleteObject(builder -> builder.bucket(bucketName).key(s3Object.key())));
        }
    }

    public static int countDataFiles(final S3Client s3Client, final String bucket) {
        final var pages = s3Client.listObjectsV2Paginator(request -> request.bucket(bucket).prefix("test-data"));
        int objectCounter = 0;
        for (final ListObjectsV2Response page : pages) {
            objectCounter += page.contents().size();
        }
        return objectCounter;
    }
}
