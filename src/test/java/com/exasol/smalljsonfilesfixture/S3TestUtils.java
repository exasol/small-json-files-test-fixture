package com.exasol.smalljsonfilesfixture;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

public class S3TestUtils {
    private static final Logger LOG = Logger.getLogger(S3TestUtils.class.getName());
    /**
     * The {@link DeleteObjectsRequest} must contain 1,000 or less objects, else it will fail with the following error
     * message:
     *
     * <pre>
     * The XML you provided was not well-formed or did not validate against our published schema
     * </pre>
     */
    private static final int MAX_COUNT_PER_DELETE_REQUEST = 1_000;

    public static void emptyS3Bucket(final String bucketName, final S3Client s3Client) {
        LOG.info(() -> "Deleting all objects from bucket " + bucketName + "...");
        s3Client.listObjectsV2Paginator(request -> request.bucket(bucketName).maxKeys(MAX_COUNT_PER_DELETE_REQUEST)
                .encodingType(EncodingType.URL)) //
                .stream() //
                .map(page -> createDeleteRequest(bucketName, page)) //
                .filter(Optional::isPresent) //
                .map(Optional::get) //
                .forEach(request -> {
                    LOG.info(() -> "Deleting " + request.delete().objects().size() + " objects...");
                    s3Client.deleteObjects(request);
                });
    }

    private static Optional<DeleteObjectsRequest> createDeleteRequest(final String bucketName,
            final ListObjectsV2Response listResponse) {
        final List<S3Object> s3Objects = listResponse.contents();
        if (s3Objects.isEmpty()) {
            return Optional.empty();
        }
        if (s3Objects.size() > MAX_COUNT_PER_DELETE_REQUEST) {
            throw new IllegalArgumentException(
                    "Trying to delete more than " + MAX_COUNT_PER_DELETE_REQUEST + " objects: " + s3Objects.size());
        }
        final List<ObjectIdentifier> objects = s3Objects.stream().map(S3TestUtils::objectId) //
                .collect(toList());
        return Optional.of(DeleteObjectsRequest.builder() //
                .bucket(bucketName) //
                .delete(delete -> delete.objects(objects)) //
                .build());
    }

    private static ObjectIdentifier objectId(final S3Object object) {
        return ObjectIdentifier.builder().key(object.key()).build();
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
