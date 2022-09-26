# Small Json Files Test Fixture 0.1.3, released 2022-09-26


Code name: Fix Security Issues

## Summary

In this release we removed test dependencies on snakeyaml and compile time dependency [Lombok](https://projectlombok.org/).

## Features

* #14: Fixed security issue in dependency

## Dependency Updates

### Compile Dependency Updates

* Updated `com.exasol:error-reporting-java:0.4.1` to `1.0.0`
* Updated `org.mockito:mockito-core:4.7.0` to `4.8.0`
* Updated `software.amazon.awssdk:iam:2.17.263` to `2.17.281`
* Updated `software.amazon.awssdk:lambda:2.17.263` to `2.17.281`
* Updated `software.amazon.awssdk:netty-nio-client:2.17.263` to `2.17.281`
* Updated `software.amazon.awssdk:s3:2.17.263` to `2.17.281`
* Updated `software.amazon.awssdk:sts:2.17.263` to `2.17.281`

### Runtime Dependency Updates

* Updated `org.eclipse:yasson:3.0.1` to `3.0.2`

### Test Dependency Updates

* Updated `org.junit.jupiter:junit-jupiter-engine:5.9.0` to `5.9.1`
* Updated `org.junit.jupiter:junit-jupiter-params:5.9.0` to `5.9.1`
* Removed `org.yaml:snakeyaml:1.31`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:2.6.2` to `2.8.0`
* Removed `org.projectlombok:lombok-maven-plugin:1.18.20.0`
