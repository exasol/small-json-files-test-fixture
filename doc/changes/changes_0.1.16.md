# Small Json Files Test Fixture 0.1.16, released 2026-09-15

Code name: Fixed vulnerabilities CVE-2026-59901, CVE-2026-59898, CVE-2026-56746, CVE-2026-55831, CVE-2026-59921, CVE-2026-56745, CVE-2026-55833, CVE-2026-59899, CVE-2026-59900, CVE-2026-56819, CVE-2026-54399, CVE-2026-54428, CVE-2026-9563, CVE-2026-89044, CVE-2026-89044, CVE-2026-71290, CVE-2026-62243, CVE-2026-75595, CVE-2026-75596, CVE-2026-64607, CVE-2026-59903

## Summary

This release fixes the following 21 vulnerabilities:

### CVE-2026-59901, GHSA-558V-64GR-WGG4 (CWE-835) in dependency `io.netty:netty-codec:jar:4.1.135.Final:compile`
Netty: [Bzip2Decoder] Infinite Loop in RLE State Machine Leads to Event-Loop Thread Hang
#### References
* https://github.com/netty/netty/security/advisories/GHSA-558v-64gr-wgg4
* https://github.com/netty/netty
* https://github.com/netty/netty/releases/tag/netty-4.1.136.Final
* https://github.com/netty/netty/releases/tag/netty-4.2.16.Final

### CVE-2026-59898, GHSA-4MP9-239F-G9HG (CWE-444) in dependency `io.netty:netty-codec-http:jar:4.1.135.Final:compile`
Netty: WebSockets V07/V08 handshaker missing Connection/Upgrade validation
#### References
* https://github.com/netty/netty/security/advisories/GHSA-4mp9-239f-g9hg
* https://github.com/netty/netty
* https://github.com/netty/netty/releases/tag/netty-4.1.136.Final
* https://github.com/netty/netty/releases/tag/netty-4.2.16.Final

### CVE-2026-56746, GHSA-6CQP-G7GG-8HR5 (CWE-284) in dependency `io.netty:netty-codec-http:jar:4.1.135.Final:compile`
Netty: Security Control Bypass via CORS Short-Circuit Failure
#### References
* https://github.com/netty/netty/security/advisories/GHSA-6cqp-g7gg-8hr5
* https://nvd.nist.gov/vuln/detail/CVE-2026-56746
* https://github.com/netty/netty
* https://github.com/netty/netty/releases/tag/netty-4.1.136.Final
* https://github.com/netty/netty/releases/tag/netty-4.2.16.Final

### CVE-2026-55831, GHSA-6JQX-86GH-F27W (CWE-400) in dependency `io.netty:netty-codec-http:jar:4.1.135.Final:compile`
Netty SPDY SETTINGS frame count materializes unbounded settings map
#### References
* https://github.com/netty/netty/security/advisories/GHSA-6jqx-86gh-f27w
* https://nvd.nist.gov/vuln/detail/CVE-2026-55831
* https://github.com/netty/netty/commit/5b68c61f37aa4a3045cba624cbea239655c9003b
* https://github.com/netty/netty/commit/bb2ff68a1fb71cb4b0eb9a9e17b66c52aff680c6
* https://github.com/netty/netty
* https://github.com/netty/netty/releases/tag/netty-4.1.136.Final
* https://github.com/netty/netty/releases/tag/netty-4.2.16.Final

### CVE-2026-59921, GHSA-GCJF-9MGH-3P7G (CWE-93) in dependency `io.netty:netty-codec-http:jar:4.1.135.Final:compile`
Netty: CRLF Injection via Multipart Filename in Netty HttpPostRequestEncoder
#### References
* https://github.com/netty/netty/security/advisories/GHSA-gcjf-9mgh-3p7g
* https://github.com/netty/netty
* https://github.com/netty/netty/releases/tag/netty-4.1.136.Final
* https://github.com/netty/netty/releases/tag/netty-4.2.16.Final

### CVE-2026-56745, GHSA-JPPX-W49H-X2QQ (CWE-400) in dependency `io.netty:netty-codec-http:jar:4.1.135.Final:compile`
Netty: [SpdyHttpDecoder] ByteBuf Reference Leak on RST_STREAM Leads to Native Memory Exhaustion
#### References
* https://github.com/netty/netty/security/advisories/GHSA-jppx-w49h-x2qq
* https://nvd.nist.gov/vuln/detail/CVE-2026-56745
* https://github.com/netty/netty/commit/5b68c61f37aa4a3045cba624cbea239655c9003b
* https://github.com/netty/netty/commit/bb2ff68a1fb71cb4b0eb9a9e17b66c52aff680c6
* https://github.com/netty/netty
* https://github.com/netty/netty/releases/tag/netty-4.1.136.Final
* https://github.com/netty/netty/releases/tag/netty-4.2.16.Final

### CVE-2026-55833, GHSA-MVH2-CRG5-V77C (CWE-400) in dependency `io.netty:netty-codec-http:jar:4.1.135.Final:compile`
Netty SPDY zlib header block continues decoded expansion after maxHeaderSize truncation
#### References
* https://github.com/netty/netty/security/advisories/GHSA-mvh2-crg5-v77c
* https://nvd.nist.gov/vuln/detail/CVE-2026-55833
* https://github.com/netty/netty/commit/5b68c61f37aa4a3045cba624cbea239655c9003b
* https://github.com/netty/netty/commit/bb2ff68a1fb71cb4b0eb9a9e17b66c52aff680c6
* https://github.com/netty/netty
* https://github.com/netty/netty/releases/tag/netty-4.1.136.Final
* https://github.com/netty/netty/releases/tag/netty-4.2.16.Final

### CVE-2026-59899, GHSA-Q4F6-JM68-57WW (CWE-770) in dependency `io.netty:netty-codec-http:jar:4.1.135.Final:compile`
Netty: [HttpContentEncoder] Unbounded Per-Connection Queue Growth via HTTP/1.1 Pipelining Leads to Denial of Service
#### References
* https://github.com/netty/netty/security/advisories/GHSA-q4f6-jm68-57ww
* https://github.com/netty/netty
* https://github.com/netty/netty/releases/tag/netty-4.1.136.Final
* https://github.com/netty/netty/releases/tag/netty-4.2.16.Final

### CVE-2026-59900, GHSA-C69G-56F8-XWQJ (CWE-444) in dependency `io.netty:netty-codec-http2:jar:4.1.135.Final:compile`
Netty: [codec-http2] Lack of Host Header Deduplication in HTTP/2→HTTP/1.x Translation Leads to Request Routing Bypass
#### References
* https://github.com/netty/netty/security/advisories/GHSA-c69g-56f8-xwqj
* https://github.com/netty/netty
* https://github.com/netty/netty/releases/tag/netty-4.1.136.Final
* https://github.com/netty/netty/releases/tag/netty-4.2.16.Final

### CVE-2026-56819 (CWE-400) in dependency `io.netty:netty-codec-http2:jar:4.1.135.Final:compile`
Netty is a network application framework for development of protocol servers and clients. In versions 4.2.0.Final through 4.2.15.Final and 4.1.0.Final through 4.1.135.Final, a remote unauthenticated peer can leak one direct `ByteBuf` per HTTP/2 `DATA` frame in applications that enable HTTP/2 content decompression via `DelegatingDecompressorFrameListener`. When a `DATA` frame is processed for a stream whose decompressor has already been closed, `Http2Decompressor.decompress(...)` calls `decompressor.writeInbound(data.retain())` and does not release the retained buffer on the error path, eventually exhausting direct memory and crashing the JVM. This issue is fixed in versions 4.1.136.Final and 4.2.16.Final.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-56819?component-type=maven&component-name=io.netty%2Fnetty-codec-http2&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-56819
* https://github.com/netty/netty/security/advisories/GHSA-93wv-jw9v-4972

### CVE-2026-54399 (CWE-400) in dependency `org.apache.httpcomponents.core5:httpcore5:jar:5.4.2:compile`
Uncontrolled Resource Consumption vulnerability in the HTTP/1.1 message parserÂ in Apache HttpComponents Core (5.4.2 and earlier, 5.5-beta1 and earlier) allowsÂ an remote attacker to cause a denial of service through memory exhaustion by sending messages with excessive number of headers / excessive header length

Sonatype's research suggests that this CVE's details differ from those defined at NVD. See https://guide.sonatype.com/vulnerability/CVE-2026-54399 for details
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-54399?component-type=maven&component-name=org.apache.httpcomponents.core5%2Fhttpcore5&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-54399
* https://lists.apache.org/thread/zmxh1pl2zohov5ntdh4lt85gfrlchgpy
* http://www.openwall.com/lists/oss-security/2026/07/01/4

### CVE-2026-54428 (CWE-400) in dependency `org.apache.httpcomponents.core5:httpcore5-h2:jar:5.4:compile`
Allocation of resources without limits or throttling in the HTTP/2 HPACK decoder in Apache HttpComponents Core (5.4.2 and earlier, 5.5-beta1 and earlier) allows an remote attacker to cause a denial of service through memory exhaustion by sending oversized compressed header blocks before the HTTP/2 SETTINGS acknowledgement causes the configured header list size limit to be applied.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-54428?component-type=maven&component-name=org.apache.httpcomponents.core5%2Fhttpcore5-h2&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-54428
* https://lists.apache.org/thread/5zjp8vczvxq19pw2rvhs21q446bhl0sd

### CVE-2026-9563 (CWE-400) in dependency `org.eclipse.parsson:parsson:jar:1.1.7:compile`
In Eclipse Parsson published Maven Central artifacts before version 1.1.8, the JSON parser did not enforce a default maximum on the number of characters consumed while parsing a single JSON document. Applications that parse attacker- controlled JSON can be forced to consume excessive CPU and memory by processing very large documents, including large arrays, objects, strings, numbers, whitespace, or nested structures, resulting in a denial of service. Eclipse Parsson 1.1.8 introduces a configurable maximum parsing limit with a default limit of 15 million parser-consumed characters.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-9563?component-type=maven&component-name=org.eclipse.parsson%2Fparsson&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-9563
* https://github.com/eclipse-ee4j/parsson/pull/169
* https://gitlab.eclipse.org/security/vulnerability-reports/-/work_items/444

## Security

* #72: Fixed vulnerability CVE-2026-89044 in dependency `io.netty:netty-codec-http2:jar:4.1.136.Final:runtime`
* #71: Fixed vulnerability CVE-2026-89044 in dependency `io.netty:netty-codec-http:jar:4.1.136.Final:runtime`
* #70: Fixed vulnerability CVE-2026-71290 in dependency `org.apache.httpcomponents.client5:httpclient5:jar:5.6.2:runtime`
* #69: Fixed vulnerability CVE-2026-62243 in dependency `io.netty:netty-handler:jar:4.1.136.Final:runtime`
* #68: Fixed vulnerability CVE-2026-75595 in dependency `io.netty:netty-handler:jar:4.1.136.Final:runtime`
* #67: Fixed vulnerability CVE-2026-75596 in dependency `io.netty:netty-handler:jar:4.1.136.Final:runtime`
* #66: Fixed vulnerability CVE-2026-64607 in dependency `org.apache.httpcomponents.client5:httpclient5:jar:5.6.2:runtime`
* #65: Fixed vulnerability CVE-2026-59903 in dependency `io.netty:netty-codec-http:jar:4.1.136.Final:runtime`
* #53: Fixed vulnerability CVE-2026-59901, GHSA-558V-64GR-WGG4 in dependency `io.netty:netty-codec:jar:4.1.135.Final:compile`
* #60: Fixed vulnerability CVE-2026-59898, GHSA-4MP9-239F-G9HG in dependency `io.netty:netty-codec-http:jar:4.1.135.Final:compile`
* #59: Fixed vulnerability CVE-2026-56746, GHSA-6CQP-G7GG-8HR5 in dependency `io.netty:netty-codec-http:jar:4.1.135.Final:compile`
* #56: Fixed vulnerability CVE-2026-55831, GHSA-6JQX-86GH-F27W in dependency `io.netty:netty-codec-http:jar:4.1.135.Final:compile`
* #54: Fixed vulnerability CVE-2026-59921, GHSA-GCJF-9MGH-3P7G in dependency `io.netty:netty-codec-http:jar:4.1.135.Final:compile`
* #58: Fixed vulnerability CVE-2026-56745, GHSA-JPPX-W49H-X2QQ in dependency `io.netty:netty-codec-http:jar:4.1.135.Final:compile`
* #57: Fixed vulnerability CVE-2026-55833, GHSA-MVH2-CRG5-V77C in dependency `io.netty:netty-codec-http:jar:4.1.135.Final:compile`
* #61: Fixed vulnerability CVE-2026-59899, GHSA-Q4F6-JM68-57WW in dependency `io.netty:netty-codec-http:jar:4.1.135.Final:compile`
* #62: Fixed vulnerability CVE-2026-59900, GHSA-C69G-56F8-XWQJ in dependency `io.netty:netty-codec-http2:jar:4.1.135.Final:compile`
* #55: Fixed vulnerability CVE-2026-56819 in dependency `io.netty:netty-codec-http2:jar:4.1.135.Final:compile`
* #50: Fixed vulnerability CVE-2026-54399 in dependency `org.apache.httpcomponents.core5:httpcore5:jar:5.4.2:compile`
* #51: Fixed vulnerability CVE-2026-54428 in dependency `org.apache.httpcomponents.core5:httpcore5-h2:jar:5.4:compile`
* #52: Fixed vulnerability CVE-2026-9563 in dependency `org.eclipse.parsson:parsson:jar:1.1.7:compile`

## Dependency Updates

### Small Json Files Test Fixture

#### Compile Dependency Updates

* Updated `jakarta.json.bind:jakarta.json.bind-api:3.0.2` to `3.0.3`
* Removed `software.amazon.awssdk:apache-client:2.46.9`
* Added `software.amazon.awssdk:apache5-client:2.54.18`
* Updated `software.amazon.awssdk:iam:2.46.9` to `2.54.18`
* Updated `software.amazon.awssdk:lambda:2.46.9` to `2.54.18`
* Updated `software.amazon.awssdk:s3:2.46.9` to `2.54.18`
* Updated `software.amazon.awssdk:signin:2.46.9` to `2.54.18`
* Updated `software.amazon.awssdk:sts:2.46.9` to `2.54.18`

#### Runtime Dependency Updates

* Added `org.eclipse.yasson:yasson:3.0.5`
* Removed `org.eclipse:yasson:3.0.4`

#### Test Dependency Updates

* Updated `org.slf4j:slf4j-jdk14:2.0.18` to `2.0.19`

#### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:2.0.7` to `2.1.1`
* Updated `com.exasol:project-keeper-maven-plugin:5.6.2` to `5.7.5`
* Removed `com.exasol:quality-summarizer-maven-plugin:0.2.1`
* Updated `io.github.git-commit-id:git-commit-id-maven-plugin:10.0.0` to `10.0.1`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.6.2` to `3.6.3`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.5.5` to `3.5.6`
* Updated `org.apache.maven.plugins:maven-site-plugin:3.21.0` to `3.22.0`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.5.5` to `3.5.6`
* Updated `org.apache.maven.plugins:maven-toolchains-plugin:3.2.0` to `3.3.0`
* Added `org.codehaus.mojo:build-helper-maven-plugin:3.6.1`
* Updated `org.codehaus.mojo:exec-maven-plugin:3.6.3` to `3.6.4`
* Updated `org.codehaus.mojo:flatten-maven-plugin:1.7.3` to `1.8.0`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.14` to `0.8.15`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:5.5.0.6356` to `5.7.0.6970`
* Updated `org.sonatype.central:central-publishing-maven-plugin:0.10.0` to `0.11.0`
* Added `org.spdx:spdx-maven-plugin:1.0.4`

### CreateJsonFilesLambda

#### Compile Dependency Updates

* Updated `@aws-sdk/client-lambda:^3.1067.0` to `^3.1132.0`
* Updated `@aws-sdk/client-s3:^3.1067.0` to `^3.1132.0`

#### Development Dependency Updates

* Updated `eslint:^10.4.1` to `^10.10.0`
* Updated `@types/node:^25.9.3` to `^26.5.1`
* Updated `globals:^17.6.0` to `^17.12.0`
* Updated `typescript:^6.0.3` to `^7.0.2`
* Updated `@types/aws-lambda:^8.10.162` to `^8.10.163`
