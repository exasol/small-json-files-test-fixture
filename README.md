# Small Json Files Test Fixture

[![Build Status](https://github.com/exasol/small-json-files-test-fixture/actions/workflows/ci-build.yml/badge.svg)](https://github.com/exasol/small-json-files-test-fixture/actions/workflows/ci-build.yml)
[![Maven Central &ndash; Small Json Files Test Fixture](https://img.shields.io/maven-central/v/com.exasol/small-json-files-test-fixture)](https://search.maven.org/artifact/com.exasol/small-json-files-test-fixture)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Asmall-json-files-test-fixture&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.exasol%3Asmall-json-files-test-fixture)

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Asmall-json-files-test-fixture&metric=security_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Asmall-json-files-test-fixture)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Asmall-json-files-test-fixture&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Asmall-json-files-test-fixture)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Asmall-json-files-test-fixture&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Asmall-json-files-test-fixture)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Asmall-json-files-test-fixture&metric=sqale_index)](https://sonarcloud.io/dashboard?id=com.exasol%3Asmall-json-files-test-fixture)

[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Asmall-json-files-test-fixture&metric=code_smells)](https://sonarcloud.io/dashboard?id=com.exasol%3Asmall-json-files-test-fixture)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Asmall-json-files-test-fixture&metric=coverage)](https://sonarcloud.io/dashboard?id=com.exasol%3Asmall-json-files-test-fixture)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Asmall-json-files-test-fixture&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=com.exasol%3Asmall-json-files-test-fixture)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Asmall-json-files-test-fixture&metric=ncloc)](https://sonarcloud.io/dashboard?id=com.exasol%3Asmall-json-files-test-fixture)

Test fixture with many small JSON files on S3.

The setup can be used for creating datasets with about 1,000,000 files on S3. Doing this from a local PC would take very long, since the connections over the internet have high latency. For that reason this setup creates the files using many parallel lambda functions directly in the AWS Cloud.

The setup consists of JSON files like:

```json
{
  "id": 1,
  "name": "teinge1Chah0esh2shee"
}
```

`Id` is a consecutive number, `name` a random string with 20 characters.

## Usage

```java
new SmallJsonFilesTestSetup(awsCredentialsProvider,
        Map.of("exa:project", "MYPROJ", "exa:owner", "me@example.com"),
        "myBucket"
    ).setup(1_000_000 /* total number of files */);
```

This will create the files in the bucket if it's not already there. It will **not** delete the setup. The idea is that you keep the files in your account.

Currently only region `eu-central-1` is supported.

## Costs

Creating 1,000,000 S3 objects costs about $5. Compared to that storage is cheap, since the files are so small.

## Architecture

Creating and deleting many small files in S3 requires many HTTP requests. To reduce network latency for single requests and therefore speed up execution time we execute the requests for creating and deleting in an AWS Lambda. That's why we split the code into the client library written in Java and lambda code written in JavaScript.

### Deleting Files

The time required for deleting files is dominated by the time to list all files in S3 as listing only returns 1,000 files at a time. That's why the Java code calls the lambda with request action type `delete`. The lambda lists all files, collects the file names and once 50,000 files are found, invokes another instance of itself with request action type `deleteList`, passing the files. This way listing and deleting can run in parallel.

Please note that using this approach we can only delete around 1,100,000 files before hitting the lambda execution limit of 15 minutes.

### Creating Files

To speed up creating files, the Java code invokes multiple lambdas in parallel with request action type `create`.

We need to consider the following constraints when creating 1,000,000 files on S3:

* AWS Lambdas have a maximum runtime of 15 minutes, so we can't create too many files in a single lambda. 20,000 files per lambda seems to be OK.
* When invoking too many lambdas in parallel (around 100) they will send too many requests in a short time to S3. Some of these requests will then fail with error `SlowDown: Please reduce your request rate`. 50 parallel invocations (i.e. 20,000 files per lambda) seem to be OK.
  * The recommended solution to use a random hash in the file name to allow S3 partitioning didn't work.
* We want to invoke Lambdas synchronously so that we know when they are done. In order to avoid HTTP request timeouts we increased the read timeout for the S3 library's HTTP client to more than 15 minutes. This works, but recently these long running HTTP request (20,000 files per lambda) started to fail with a read timeout on GitHub Actions, see issue [#24](https://github.com/exasol/small-json-files-test-fixture/issues/24). Reducing the number of files per lambda to 5,000 reduces the runtime sufficiently to avoid these timeouts.

To solve these constraints we use a fixed thread pool for synchronously invoking the lambdas. This way only a limited number of lambdas is running concurrently. Experiments showed that a concurrency of 75 works well.

We tried using the asynchronous client for AWS Lambda and limiting the maximum number of allowed concurrent requests to 50. The first 50 requests succeeded but the others failed with error `Signature expired: ... is now earlier than ...`. It seems that all requests including their signature are created when they are scheduled, so that the signature is expired once they are executed. The simplest solution was to use the synchronous Lambda client using a fixed thread pool.

## Additional Information

* [Changelog](doc/changes/changelog.md)
* [Dependencies](dependencies.md)
