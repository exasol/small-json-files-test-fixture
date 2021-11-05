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

## Runtime Dependencies

| Dependency                    | License                                                                                                        |
| ----------------------------- | -------------------------------------------------------------------------------------------------------------- |
| [JSON-P Default Provider][12] | [Eclipse Public License 2.0][13]; [GNU General Public License, version 2 with the GNU Classpath Exception][14] |
| [org.eclipse.yasson][33]      | [Eclipse Public License v. 2.0][34]; [Eclipse Distribution License v. 1.0][35]                                 |

## Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [Maven Surefire Plugin][36]                             | [Apache License, Version 2.0][37]              |
| [Maven Failsafe Plugin][38]                             | [Apache License, Version 2.0][37]              |
| [Apache Maven Compiler Plugin][40]                      | [Apache License, Version 2.0][37]              |
| [Versions Maven Plugin][42]                             | [Apache License, Version 2.0][37]              |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][44] | [ASL2][29]                                     |
| [Apache Maven Enforcer Plugin][46]                      | [Apache License, Version 2.0][37]              |
| [Project keeper maven plugin][48]                       | [MIT][3]                                       |
| [error-code-crawler-maven-plugin][50]                   | [MIT][3]                                       |
| [Reproducible Build Maven Plugin][52]                   | [Apache 2.0][29]                               |
| [Lombok Maven Plugin][54]                               | [The MIT License][3]                           |
| [Apache Maven GPG Plugin][56]                           | [Apache License, Version 2.0][29]              |
| [Maven Deploy Plugin][58]                               | [The Apache Software License, Version 2.0][29] |
| [Nexus Staging Maven Plugin][60]                        | [Eclipse Public License][61]                   |
| [Apache Maven Source Plugin][62]                        | [Apache License, Version 2.0][37]              |
| [Apache Maven Javadoc Plugin][64]                       | [Apache License, Version 2.0][37]              |
| [JaCoCo :: Maven Plugin][66]                            | [Eclipse Public License 2.0][67]               |
| [Maven Clean Plugin][68]                                | [The Apache Software License, Version 2.0][29] |
| [Maven Resources Plugin][70]                            | [The Apache Software License, Version 2.0][29] |
| [Maven JAR Plugin][72]                                  | [The Apache Software License, Version 2.0][29] |
| [Maven Install Plugin][74]                              | [The Apache Software License, Version 2.0][29] |
| [Maven Site Plugin 3][76]                               | [The Apache Software License, Version 2.0][29] |

[48]: https://github.com/exasol/project-keeper-maven-plugin
[66]: https://www.eclemma.org/jacoco/index.html
[28]: http://www.snakeyaml.org
[2]: https://github.com/exasol/error-reporting-java
[34]: http://www.eclipse.org/legal/epl-v20.html
[29]: http://www.apache.org/licenses/LICENSE-2.0.txt
[20]: https://projectlombok.org
[36]: https://maven.apache.org/surefire/maven-surefire-plugin/
[60]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[68]: http://maven.apache.org/plugins/maven-clean-plugin/
[33]: https://projects.eclipse.org/projects/ee4j.yasson
[0]: https://aws.amazon.com/sdkforjava
[3]: https://opensource.org/licenses/MIT
[15]: https://eclipse-ee4j.github.io/jsonb-api
[18]: https://github.com/mockito/mockito
[38]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[42]: http://www.mojohaus.org/versions-maven-plugin/
[54]: http://anthonywhitford.com/lombok.maven/lombok-maven-plugin/
[23]: http://opensource.org/licenses/BSD-3-Clause
[40]: https://maven.apache.org/plugins/maven-compiler-plugin/
[56]: http://maven.apache.org/plugins/maven-gpg-plugin/
[67]: https://www.eclipse.org/legal/epl-2.0/
[61]: http://www.eclipse.org/legal/epl-v10.html
[1]: https://aws.amazon.com/apache2.0
[19]: https://github.com/mockito/mockito/blob/main/LICENSE
[21]: https://projectlombok.org/LICENSE
[52]: http://zlika.github.io/reproducible-build-maven-plugin
[72]: http://maven.apache.org/plugins/maven-jar-plugin/
[13]: https://projects.eclipse.org/license/epl-2.0
[35]: http://www.eclipse.org/org/documents/edl-v10.php
[37]: https://www.apache.org/licenses/LICENSE-2.0.txt
[46]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[25]: https://www.eclipse.org/legal/epl-v20.html
[74]: http://maven.apache.org/plugins/maven-install-plugin/
[24]: https://junit.org/junit5/
[44]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[12]: https://github.com/eclipse-ee4j/jsonp
[62]: https://maven.apache.org/plugins/maven-source-plugin/
[10]: https://sdk.amazonaws.com/java/api/latest/software/amazon/awssdk/http/nio/netty/NettyNioAsyncHttpClient.html
[14]: https://projects.eclipse.org/license/secondary-gpl-2.0-cp
[22]: http://hamcrest.org/JavaHamcrest/
[58]: http://maven.apache.org/plugins/maven-deploy-plugin/
[76]: http://maven.apache.org/plugins/maven-site-plugin/
[70]: http://maven.apache.org/plugins/maven-resources-plugin/
[64]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[50]: https://github.com/exasol/error-code-crawler-maven-plugin
