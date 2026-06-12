# Small Json Files Test Fixture 0.1.15, released 2026-06-12

Code name: Fix vulnerabilities in netty

## Summary

This release fixes vulnerabilities in the following dependencies:

* `io.netty:netty-codec-http2:jar:4.1.126.Final:runtime`
  * CVE-2026-33871 (CWE-770): Allocation of Resources Without Limits or Throttling (7.5)
  * CVE-2026-42587 (CWE-400): Uncontrolled Resource Consumption ('Resource Exhaustion') (8.7)
  * CVE-2026-48043 (CWE-400): Uncontrolled Resource Consumption ('Resource Exhaustion') (5.3)
  * CVE-2026-50560 (CWE-770): Allocation of Resources Without Limits or Throttling (6.3)
  * CVE-2026-47244 (CWE-400): Uncontrolled Resource Consumption ('Resource Exhaustion') (5.3)
* `io.netty:netty-handler:jar:4.1.126.Final:runtime`
  * CVE-2026-44249 (CWE-1025): Comparison Using Wrong Factors (8.1)
  * CVE-2026-45416 (CWE-770): Allocation of Resources Without Limits or Throttling (7.5)
* `io.netty:netty-codec-http:jar:4.1.126.Final:runtime`
  * CVE-2025-67735 (CWE-93): Improper Neutralization of CRLF Sequences ('CRLF Injection') (6.5)
  * CVE-2026-33870 (CWE-444): Inconsistent Interpretation of HTTP Requests ('HTTP Request Smuggling') (8.7)
  * CVE-2026-41417 (CWE-444): Inconsistent Interpretation of HTTP Requests ('HTTP Request Smuggling') (5.3)
  * CVE-2026-42580 (CWE-190): Integer Overflow or Wraparound (6.5)
  * CVE-2026-42581 (CWE-444): Inconsistent Interpretation of HTTP Requests ('HTTP Request Smuggling') (9.8)
  * CVE-2026-42584 (CWE-444): Inconsistent Interpretation of HTTP Requests ('HTTP Request Smuggling') (9.1)
  * CVE-2026-42585 (CWE-444): Inconsistent Interpretation of HTTP Requests ('HTTP Request Smuggling') (7.5)
  * CVE-2026-42587 (CWE-400): Uncontrolled Resource Consumption ('Resource Exhaustion') (8.7)
* `io.netty:netty-codec:jar:4.1.126.Final:runtime`
  * CVE-2026-42583 (CWE-400): Uncontrolled Resource Consumption ('Resource Exhaustion') (7.5)

## Security

* #48: Update dependencies

## Dependency Updates

### Small Json Files Test Fixture

#### Compile Dependency Updates

* Updated `com.exasol:error-reporting-java:1.0.1` to `1.0.2`
* Updated `jakarta.json.bind:jakarta.json.bind-api:3.0.1` to `3.0.2`
* Updated `org.mockito:mockito-core:5.20.0` to `5.23.0`
* Updated `software.amazon.awssdk:apache-client:2.34.0` to `2.46.9`
* Updated `software.amazon.awssdk:iam:2.34.0` to `2.46.9`
* Updated `software.amazon.awssdk:lambda:2.34.0` to `2.46.9`
* Updated `software.amazon.awssdk:s3:2.34.0` to `2.46.9`
* Updated `software.amazon.awssdk:sts:2.34.0` to `2.46.9`

#### Test Dependency Updates

* Updated `org.junit.jupiter:junit-jupiter-params:5.13.4` to `5.14.4`
* Updated `org.slf4j:slf4j-jdk14:2.0.17` to `2.0.18`

#### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:2.0.4` to `2.0.7`
* Updated `com.exasol:project-keeper-maven-plugin:5.2.3` to `5.6.2`
* Updated `com.exasol:quality-summarizer-maven-plugin:0.2.0` to `0.2.1`
* Updated `io.github.git-commit-id:git-commit-id-maven-plugin:9.0.1` to `10.0.0`
* Updated `org.apache.maven.plugins:maven-artifact-plugin:3.6.0` to `3.6.1`
* Updated `org.apache.maven.plugins:maven-clean-plugin:3.3.2` to `3.5.0`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.14.0` to `3.15.0`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.5.0` to `3.6.2`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.5.3` to `3.5.5`
* Updated `org.apache.maven.plugins:maven-gpg-plugin:3.2.7` to `3.2.8`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.11.2` to `3.12.0`
* Updated `org.apache.maven.plugins:maven-resources-plugin:3.3.1` to `3.5.0`
* Updated `org.apache.maven.plugins:maven-source-plugin:3.2.1` to `3.4.0`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.5.3` to `3.5.5`
* Updated `org.codehaus.mojo:exec-maven-plugin:3.2.0` to `3.6.3`
* Updated `org.codehaus.mojo:flatten-maven-plugin:1.7.0` to `1.7.3`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.18.0` to `2.21.0`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.13` to `0.8.14`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:5.1.0.4751` to `5.5.0.6356`
* Updated `org.sonatype.central:central-publishing-maven-plugin:0.7.0` to `0.10.0`

### CreateJsonFilesLambda

#### Compile Dependency Updates

* Updated `@aws-sdk/client-lambda:^3.744.0` to `^3.1067.0`
* Updated `@aws-sdk/client-s3:^3.744.0` to `^3.1067.0`

#### Development Dependency Updates

* Updated `eslint:^9.20.1` to `^10.4.1`
* Updated `@eslint/js:^9.20.0` to `^10.0.1`
* Updated `@types/node:^22.13.1` to `^25.9.3`
* Updated `globals:^15.14.0` to `^17.6.0`
* Updated `typescript:^5.7.3` to `^6.0.3`
* Updated `@types/aws-lambda:^8.10.147` to `^8.10.162`
