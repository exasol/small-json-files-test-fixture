# Small Json Files Test Fixture 0.1.9, released 2024-01-23

Code name: Improve stability of deleting files

## Summary

This release improves stability when deleting files and improves log messages.

## Bugfixes

* #31: Improved stability deleting files

## Dependency Updates

### Small Json Files Test Fixture

#### Compile Dependency Updates

* Updated `org.mockito:mockito-core:5.7.0` to `5.9.0`
* Updated `software.amazon.awssdk:apache-client:2.21.26` to `2.23.7`
* Updated `software.amazon.awssdk:iam:2.21.26` to `2.23.7`
* Updated `software.amazon.awssdk:lambda:2.21.26` to `2.23.7`
* Updated `software.amazon.awssdk:s3:2.21.26` to `2.23.7`
* Updated `software.amazon.awssdk:sts:2.21.26` to `2.23.7`

#### Test Dependency Updates

* Updated `nl.jqno.equalsverifier:equalsverifier:3.15.3` to `3.15.6`
* Updated `org.slf4j:slf4j-jdk14:2.0.9` to `2.0.11`

#### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:2.9.16` to `3.0.0`
* Updated `org.apache.maven.plugins:maven-clean-plugin:2.5` to `3.3.2`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.2.2` to `3.2.3`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.6.2` to `3.6.3`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.2.2` to `3.2.3`
* Added `org.apache.maven.plugins:maven-toolchains-plugin:3.1.0`
* Added `org.codehaus.mojo:exec-maven-plugin:3.1.1`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.16.1` to `2.16.2`

### CreateJsonFilesLambda

#### Compile Dependency Updates

* Updated `@aws-sdk/client-lambda:^3.363.0` to `^3.496.0`
* Updated `@aws-sdk/client-s3:^3.363.0` to `^3.496.0`

#### Development Dependency Updates

* Updated `eslint:^8.44.0` to `^8.56.0`
* Updated `eslint-plugin-n:^16.0.1` to `^16.6.2`
* Updated `@types/node:^20.3.3` to `^20.11.5`
* Updated `eslint-plugin-import:^2.26.0` to `^2.29.1`
* Updated `eslint-plugin-jsdoc:^46.4.3` to `^48.0.2`
* Updated `typescript:^5.1.6` to `^5.3.3`
* Updated `@types/aws-lambda:^8.10.119` to `^8.10.131`
