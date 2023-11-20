# Small Json Files Test Fixture 0.1.8, released 2023-11-20

Code name: Fix CVE-2023-4043 in `org.eclipse.parsson:parsson`

## Summary

This release fixes vulnerability CVE-2023-4043 in `org.eclipse.parsson:parsson`. It also excludes CVE-2023-4586 in transitive dependency `io.netty:netty-handler` of `software.amazon.awssdk:s3`. According to [this comment](https://github.com/aws/aws-sdk-java-v2/issues/4584#issuecomment-1760547020) in the AWS SDK, the AWS SDK is not affected by this vulnerability in netty.

## Security

* #28: Fixed CVE-2023-4043 in `org.eclipse.parsson:parsson`

## Dependency Updates

### Small Json Files Test Fixture

#### Compile Dependency Updates

* Updated `jakarta.json:jakarta.json-api:2.1.2` to `2.1.3`
* Updated `org.mockito:mockito-core:5.4.0` to `5.7.0`
* Updated `software.amazon.awssdk:apache-client:2.20.97` to `2.21.26`
* Updated `software.amazon.awssdk:iam:2.20.97` to `2.21.26`
* Updated `software.amazon.awssdk:lambda:2.20.97` to `2.21.26`
* Updated `software.amazon.awssdk:s3:2.20.97` to `2.21.26`
* Updated `software.amazon.awssdk:sts:2.20.97` to `2.21.26`

#### Runtime Dependency Updates

* Added `org.eclipse.parsson:parsson:1.1.5`

#### Test Dependency Updates

* Updated `nl.jqno.equalsverifier:equalsverifier:3.14.3` to `3.15.3`
* Updated `org.junit.jupiter:junit-jupiter-params:5.9.3` to `5.10.1`
* Updated `org.slf4j:slf4j-jdk14:2.0.7` to `2.0.9`

#### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:1.2.3` to `1.3.1`
* Updated `com.exasol:project-keeper-maven-plugin:2.9.7` to `2.9.16`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.3.0` to `3.4.1`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.0.0` to `3.2.2`
* Updated `org.apache.maven.plugins:maven-gpg-plugin:3.0.1` to `3.1.0`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.5.0` to `3.6.2`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.0.0` to `3.2.2`
* Updated `org.basepom.maven:duplicate-finder-maven-plugin:1.5.1` to `2.0.1`
* Updated `org.codehaus.mojo:flatten-maven-plugin:1.4.1` to `1.5.0`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.15.0` to `2.16.1`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.9` to `0.8.11`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.1.2184` to `3.10.0.2594`
