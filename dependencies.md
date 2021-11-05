<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                                                   | License                                                                                                        |
| ------------------------------------------------------------ | -------------------------------------------------------------------------------------------------------------- |
| [AWS Java SDK :: Services :: Amazon S3][0]                   | [Apache License, Version 2.0][1]                                                                               |
| [error-reporting-java][2]                                    | [MIT][3]                                                                                                       |
| [AWS Java SDK :: Services :: AWS Lambda][0]                  | [Apache License, Version 2.0][1]                                                                               |
| [AWS Java SDK :: Services :: AWS IAM][0]                     | [Apache License, Version 2.0][1]                                                                               |
| [AWS Java SDK :: Services :: AWS STS][0]                     | [Apache License, Version 2.0][1]                                                                               |
| [AWS Java SDK :: HTTP Clients :: Netty Non-Blocking I/O][10] | [Apache License, Version 2.0][1]                                                                               |
| [Jakarta JSON Processing API][12]                            | [Eclipse Public License 2.0][13]; [GNU General Public License, version 2 with the GNU Classpath Exception][14] |
| [JSON-B API][15]                                             | [Eclipse Public License 2.0][13]; [GNU General Public License, version 2 with the GNU Classpath Exception][14] |
| [mockito-core][18]                                           | [The MIT License][19]                                                                                          |
| [Project Lombok][20]                                         | [The MIT License][21]                                                                                          |

## Test Dependencies

| Dependency                 | License                           |
| -------------------------- | --------------------------------- |
| [Hamcrest][22]             | [BSD License 3][23]               |
| [JUnit Jupiter Engine][24] | [Eclipse Public License v2.0][25] |
| [JUnit Jupiter Params][24] | [Eclipse Public License v2.0][25] |
| [SnakeYAML][28]            | [Apache License, Version 2.0][29] |
| [EqualsVerifier][30]       | [Apache License, Version 2.0][29] |

## Runtime Dependencies

| Dependency                    | License                                                                                                        |
| ----------------------------- | -------------------------------------------------------------------------------------------------------------- |
| [JSON-P Default Provider][12] | [Eclipse Public License 2.0][13]; [GNU General Public License, version 2 with the GNU Classpath Exception][14] |
| [org.eclipse.yasson][35]      | [Eclipse Public License v. 2.0][36]; [Eclipse Distribution License v. 1.0][37]                                 |

## Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [Maven Surefire Plugin][38]                             | [Apache License, Version 2.0][39]              |
| [Maven Failsafe Plugin][40]                             | [Apache License, Version 2.0][39]              |
| [Apache Maven Compiler Plugin][42]                      | [Apache License, Version 2.0][39]              |
| [Versions Maven Plugin][44]                             | [Apache License, Version 2.0][39]              |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][46] | [ASL2][29]                                     |
| [Apache Maven Enforcer Plugin][48]                      | [Apache License, Version 2.0][39]              |
| [Project keeper maven plugin][50]                       | [MIT][3]                                       |
| [error-code-crawler-maven-plugin][52]                   | [MIT][3]                                       |
| [Reproducible Build Maven Plugin][54]                   | [Apache 2.0][29]                               |
| [Lombok Maven Plugin][56]                               | [The MIT License][3]                           |
| [Apache Maven GPG Plugin][58]                           | [Apache License, Version 2.0][29]              |
| [Maven Deploy Plugin][60]                               | [The Apache Software License, Version 2.0][29] |
| [Nexus Staging Maven Plugin][62]                        | [Eclipse Public License][63]                   |
| [Apache Maven Source Plugin][64]                        | [Apache License, Version 2.0][39]              |
| [Apache Maven Javadoc Plugin][66]                       | [Apache License, Version 2.0][39]              |
| [JaCoCo :: Maven Plugin][68]                            | [Eclipse Public License 2.0][69]               |
| [Maven Clean Plugin][70]                                | [The Apache Software License, Version 2.0][29] |
| [Maven Resources Plugin][72]                            | [The Apache Software License, Version 2.0][29] |
| [Maven JAR Plugin][74]                                  | [The Apache Software License, Version 2.0][29] |
| [Maven Install Plugin][76]                              | [The Apache Software License, Version 2.0][29] |
| [Maven Site Plugin 3][78]                               | [The Apache Software License, Version 2.0][29] |

[50]: https://github.com/exasol/project-keeper-maven-plugin
[68]: https://www.eclemma.org/jacoco/index.html
[28]: http://www.snakeyaml.org
[2]: https://github.com/exasol/error-reporting-java
[36]: http://www.eclipse.org/legal/epl-v20.html
[29]: http://www.apache.org/licenses/LICENSE-2.0.txt
[20]: https://projectlombok.org
[38]: https://maven.apache.org/surefire/maven-surefire-plugin/
[62]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[70]: http://maven.apache.org/plugins/maven-clean-plugin/
[35]: https://projects.eclipse.org/projects/ee4j.yasson
[0]: https://aws.amazon.com/sdkforjava
[3]: https://opensource.org/licenses/MIT
[18]: https://github.com/mockito/mockito
[40]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[44]: http://www.mojohaus.org/versions-maven-plugin/
[56]: http://anthonywhitford.com/lombok.maven/lombok-maven-plugin/
[23]: http://opensource.org/licenses/BSD-3-Clause
[42]: https://maven.apache.org/plugins/maven-compiler-plugin/
[58]: http://maven.apache.org/plugins/maven-gpg-plugin/
[69]: https://www.eclipse.org/legal/epl-2.0/
[63]: http://www.eclipse.org/legal/epl-v10.html
[1]: https://aws.amazon.com/apache2.0
[19]: https://github.com/mockito/mockito/blob/main/LICENSE
[21]: https://projectlombok.org/LICENSE
[54]: http://zlika.github.io/reproducible-build-maven-plugin
[74]: http://maven.apache.org/plugins/maven-jar-plugin/
[13]: https://projects.eclipse.org/license/epl-2.0
[37]: http://www.eclipse.org/org/documents/edl-v10.php
[39]: https://www.apache.org/licenses/LICENSE-2.0.txt
[48]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[25]: https://www.eclipse.org/legal/epl-v20.html
[76]: http://maven.apache.org/plugins/maven-install-plugin/
[24]: https://junit.org/junit5/
[46]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[12]: https://github.com/eclipse-ee4j/jsonp
[30]: http://www.jqno.nl/equalsverifier
[64]: https://maven.apache.org/plugins/maven-source-plugin/
[10]: https://sdk.amazonaws.com/java/api/latest/software/amazon/awssdk/http/nio/netty/NettyNioAsyncHttpClient.html
[15]: https://github.com/eclipse-ee4j/jsonb-api
[14]: https://projects.eclipse.org/license/secondary-gpl-2.0-cp
[22]: http://hamcrest.org/JavaHamcrest/
[60]: http://maven.apache.org/plugins/maven-deploy-plugin/
[78]: http://maven.apache.org/plugins/maven-site-plugin/
[72]: http://maven.apache.org/plugins/maven-resources-plugin/
[66]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[52]: https://github.com/exasol/error-code-crawler-maven-plugin
