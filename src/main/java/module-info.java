/**
 * Test fixture that allows creating many small JSON files on AWS S3.
 */
module small.json.files.test.fixture {
    exports com.exasol.smalljsonfilesfixture;

    requires jakarta.json;
    requires jakarta.json.bind;
    requires java.logging;
    requires software.amazon.awssdk.auth;
    requires software.amazon.awssdk.awscore;
    requires software.amazon.awssdk.core;
    requires software.amazon.awssdk.http;
    requires software.amazon.awssdk.http.nio.netty;
    requires software.amazon.awssdk.regions;
    requires software.amazon.awssdk.services.iam;
    requires software.amazon.awssdk.services.lambda;
    requires software.amazon.awssdk.services.s3;
    requires software.amazon.awssdk.services.sts;
    requires software.amazon.awssdk.utils;
}
