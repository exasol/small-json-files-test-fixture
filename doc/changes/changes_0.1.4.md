# Small Json Files Test Fixture 0.1.4, released 2023-01-25

Code name: Fix vulnerabilities in dependencies

## Summary

This release fixes CVE-2022-41915 in dependency `io.netty:netty-codec:jar:4.1.77.Final` by upgrading it to `4.1.86.Final`.

## Features

* #16: Fixed vulnerability in dependencies

## Dependency Updates

### Compile Dependency Updates

* Updated `org.mockito:mockito-core:4.8.0` to `5.0.0`
* Updated `software.amazon.awssdk:iam:2.17.281` to `2.19.23`
* Updated `software.amazon.awssdk:lambda:2.17.281` to `2.19.23`
* Updated `software.amazon.awssdk:netty-nio-client:2.17.281` to `2.19.23`
* Updated `software.amazon.awssdk:s3:2.17.281` to `2.19.23`
* Updated `software.amazon.awssdk:sts:2.17.281` to `2.19.23`

### Runtime Dependency Updates

* Removed `org.glassfish:jakarta.json:2.0.1`

### Test Dependency Updates

* Updated `nl.jqno.equalsverifier:equalsverifier:3.10.1` to `3.12.3`
* Updated `org.junit.jupiter:junit-jupiter-engine:5.9.1` to `5.9.2`
* Updated `org.junit.jupiter:junit-jupiter-params:5.9.1` to `5.9.2`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:1.1.2` to `1.2.1`
* Updated `com.exasol:project-keeper-maven-plugin:2.8.0` to `2.9.1`
* Updated `io.github.zlika:reproducible-build-maven-plugin:0.15` to `0.16`
* Updated `org.apache.maven.plugins:maven-deploy-plugin:3.0.0-M1` to `3.0.0`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.0.0-M5` to `3.0.0-M7`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.4.0` to `3.4.1`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.0.0-M5` to `3.0.0-M7`
* Updated `org.codehaus.mojo:flatten-maven-plugin:1.2.7` to `1.3.0`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.10.0` to `2.13.0`
