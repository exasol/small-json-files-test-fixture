# Small Json Files Test Fixture 0.1.12, released 2025-02-12

Code name: Fix vulnerabilities CVE-2025-25193 and CVE-2025-24970 in dependencies

## Summary

This release fixes the following vulnerabilities in dependencies:

* `io.netty:netty-common:jar:4.1.115.Final:runtime`: CVE-2025-25193
* `io.netty:netty-handler:jar:4.1.115.Final:runtime`: CVE-2025-24970

## Security

* #38: Fixed CVE-2025-25193 in `io.netty:netty-common:jar:4.1.115.Final:runtime`
* #37: Fixed CVE-2025-24970 in `io.netty:netty-handler:jar:4.1.115.Final:runtime`

## Dependency Updates

### Small Json Files Test Fixture

#### Compile Dependency Updates

* Updated `org.mockito:mockito-core:5.14.2` to `5.15.2`
* Updated `software.amazon.awssdk:apache-client:2.29.15` to `2.30.18`
* Updated `software.amazon.awssdk:iam:2.29.15` to `2.30.18`
* Updated `software.amazon.awssdk:lambda:2.29.15` to `2.30.18`
* Updated `software.amazon.awssdk:s3:2.29.15` to `2.30.18`
* Updated `software.amazon.awssdk:sts:2.29.15` to `2.30.18`

#### Test Dependency Updates

* Updated `nl.jqno.equalsverifier:equalsverifier:3.17.3` to `3.19`
* Updated `org.junit.jupiter:junit-jupiter-params:5.11.3` to `5.11.4`

#### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:4.4.0` to `4.5.0`
* Updated `org.apache.maven.plugins:maven-deploy-plugin:3.1.2` to `3.1.3`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.5.1` to `3.5.2`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.10.1` to `3.11.1`
* Updated `org.apache.maven.plugins:maven-site-plugin:3.9.1` to `3.21.0`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.5.1` to `3.5.2`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.17.1` to `2.18.0`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:4.0.0.4121` to `5.0.0.4389`

### CreateJsonFilesLambda

#### Compile Dependency Updates

* Updated `@aws-sdk/client-lambda:^3.552.0` to `^3.744.0`
* Updated `@aws-sdk/client-s3:^3.552.0` to `^3.744.0`

#### Development Dependency Updates

* Updated `eslint:^8.57.0` to `^9.20.1`
* Updated `@eslint/js:^9.0.0` to `^9.20.0`
* Updated `@types/node:^20.12.7` to `^22.13.1`
* Updated `globals:^15.0.0` to `^15.14.0`
* Updated `typescript:^5.4.4` to `^5.7.3`
* Updated `@types/aws-lambda:^8.10.137` to `^8.10.147`
* Removed `eslint-plugin-n:^16.6.2`
* Removed `@eslint/eslintrc:^3.0.2`
* Removed `eslint-plugin-promise:^6.1.1`
* Removed `eslint-plugin-import:^2.29.1`
* Removed `eslint-config-standard:^17.1.0`
* Removed `eslint-plugin-jsdoc:^48.2.3`
