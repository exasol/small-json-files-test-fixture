# Small Json Files Test Fixture 0.1.6, released 2023-05-04

Code name: Fix read timeout on GitHub Actions

## Summary

Setup was failing with a read timeout error on GitHub Actions when using 20,000 files per lambda. We didn't find a solution for the root cause but implemented a workaround. Now you can only specify the total number of files to create while the number of files per lambda is hard coded to 5,000.

Please note that this is a breaking change as the API changes. See the [readme](https://github.com/exasol/small-json-files-test-fixture/#usage) for example usage.

## Refactoring

* #22: Migrated CI isolation to AWS CDK v2

## Bugfixes

* #24: Fixed read timeout when running on GitHub Actions

## Dependency Updates

### Small Json Files Test Fixture

#### Compile Dependency Updates

* Removed `commons-codec:commons-codec:1.15`
* Updated `org.mockito:mockito-core:5.2.0` to `5.3.1`
* Updated `software.amazon.awssdk:iam:2.20.31` to `2.20.59`
* Updated `software.amazon.awssdk:lambda:2.20.31` to `2.20.59`
* Updated `software.amazon.awssdk:netty-nio-client:2.20.31` to `2.20.59`
* Updated `software.amazon.awssdk:s3:2.20.31` to `2.20.59`
* Updated `software.amazon.awssdk:sts:2.20.31` to `2.20.59`

#### Runtime Dependency Updates

* Updated `org.eclipse:yasson:3.0.2` to `3.0.3`

#### Test Dependency Updates

* Removed `org.junit.jupiter:junit-jupiter-engine:5.9.2`
* Updated `org.junit.jupiter:junit-jupiter-params:5.9.2` to `5.9.3`

#### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:1.2.2` to `1.2.3`
* Updated `com.exasol:project-keeper-maven-plugin:2.9.6` to `2.9.7`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.10.1` to `3.11.0`
* Updated `org.apache.maven.plugins:maven-deploy-plugin:3.1.0` to `3.1.1`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.2.1` to `3.3.0`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.0.0-M8` to `3.0.0`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.4.1` to `3.5.0`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.0.0-M8` to `3.0.0`
* Added `org.basepom.maven:duplicate-finder-maven-plugin:1.5.1`
* Updated `org.codehaus.mojo:flatten-maven-plugin:1.3.0` to `1.4.1`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.14.2` to `2.15.0`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.8` to `0.8.9`

### CreateJsonFilesLambda

#### Compile Dependency Updates

* Added `@aws-sdk/client-lambda:^3.326.0`
* Added `@aws-sdk/client-s3:^3.326.0`
* Removed `aws-sdk:^2.1342.0`

#### Development Dependency Updates

* Updated `eslint:^8.36.0` to `^8.39.0`
* Added `eslint-plugin-n:^15.7.0`
* Updated `@types/node:^16.11.7` to `^18.16.3`
* Added `eslint-plugin-promise:^6.1.1`
* Added `eslint-plugin-import:^2.27.5`
* Added `eslint-config-standard:^17.0.0`
* Added `eslint-plugin-jsdoc:^43.1.1`
* Updated `typescript:^5.0.2` to `^5.0.4`
* Updated `@types/aws-lambda:^8.10.114` to `^8.10.115`
