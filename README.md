# Small Json Files Test Fixture

[![Build Status](https://github.com/exasol/small-json-files-test-fixture/actions/workflows/ci-build.yml/badge.svg)](https://github.com/exasol/small-json-files-test-fixture/actions/workflows/ci-build.yml)
[![Maven Central](https://img.shields.io/maven-central/v/com.exasol/small-json-files-test-fixture)](https://search.maven.org/artifact/com.exasol/small-json-files-test-fixture)

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
new SmallJsonFilesTestSetup().setup(
        Map.of("exa:project","MYPROJ","exa:owner","me@examle.com"),
        "myBucket",
        awsCredentialsProvider,
        1_000_000 /* total number of files */,
        20_000 /* files per lambda function */);
```

This will create the files in the bucket if it's not already there. It will **not** delete the setup. The idea is that you keep the files in you account.

## Costs

Creating 1_000_000 S3 objects costs about $5. Compared to that storage is cheap, since the files are so small.

## Additional Information

* [Changelog](doc/changes/changelog.md)
* [Dependencies](dependencies.md)