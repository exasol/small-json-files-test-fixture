package com.exasol.smalljsonfilesfixture;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.logging.Logger;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.paginators.ListObjectsV2Iterable;

public class S3TestUtils {
    private static final Logger LOG = Logger.getLogger(S3TestUtils.class.getName());

    public static void emptyS3Bucket(final String bucketName, final S3Client s3Client) {
        final ListObjectsV2Iterable pages = s3Client.listObjectsV2Paginator(request -> request.bucket(bucketName));
        final Collection<ObjectIdentifier> objects = pages.stream().flatMap(
                page -> page.contents().stream().map(object -> ObjectIdentifier.builder().key(object.key()).build()))
                .collect(toList());
        if (objects.isEmpty()) {
            LOG.info(() -> "Bucket " + bucketName + " is already empty, nothing to delete");
            return;
        }
        LOG.info(() -> "Deleting all " + objects.size() + " objects from bucket " + bucketName + "...");
        final DeleteObjectsRequest multiObjectDeleteRequest = DeleteObjectsRequest.builder().bucket(bucketName)
                .delete(Delete.builder().objects(objects).build()).build();
        s3Client.deleteObjects(multiObjectDeleteRequest);
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
