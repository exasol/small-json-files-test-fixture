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
| [mockito-core][15]                                           | [The MIT License][16]                                                                                          |
| [Project Lombok][17]                                         | [The MIT License][18]                                                                                          |

## Test Dependencies

| Dependency                 | License                           |
| -------------------------- | --------------------------------- |
| [Hamcrest][19]             | [BSD License 3][20]               |
| [JUnit Jupiter Engine][21] | [Eclipse Public License v2.0][22] |
| [JUnit Jupiter Params][21] | [Eclipse Public License v2.0][22] |
| [SnakeYAML][25]            | [Apache License, Version 2.0][26] |

## Runtime Dependencies

| Dependency                    | License                                                                                                        |
| ----------------------------- | -------------------------------------------------------------------------------------------------------------- |
| [JSON-P Default Provider][12] | [Eclipse Public License 2.0][13]; [GNU General Public License, version 2 with the GNU Classpath Exception][14] |

## Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [Maven Surefire Plugin][30]                             | [Apache License, Version 2.0][31]              |
| [Maven Failsafe Plugin][32]                             | [Apache License, Version 2.0][31]              |
| [Apache Maven Compiler Plugin][34]                      | [Apache License, Version 2.0][31]              |
| [Versions Maven Plugin][36]                             | [Apache License, Version 2.0][31]              |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][38] | [ASL2][26]                                     |
| [Apache Maven Enforcer Plugin][40]                      | [Apache License, Version 2.0][31]              |
| [Project keeper maven plugin][42]                       | [MIT][3]                                       |
| [error-code-crawler-maven-plugin][44]                   | [MIT][3]                                       |
| [Reproducible Build Maven Plugin][46]                   | [Apache 2.0][26]                               |
| [Lombok Maven Plugin][48]                               | [The MIT License][3]                           |
| [Apache Maven GPG Plugin][50]                           | [Apache License, Version 2.0][26]              |
| [Maven Deploy Plugin][52]                               | [The Apache Software License, Version 2.0][26] |
| [Nexus Staging Maven Plugin][54]                        | [Eclipse Public License][55]                   |
| [Apache Maven Source Plugin][56]                        | [Apache License, Version 2.0][31]              |
| [Apache Maven Javadoc Plugin][58]                       | [Apache License, Version 2.0][31]              |
| [JaCoCo :: Maven Plugin][60]                            | [Eclipse Public License 2.0][61]               |
| [Maven Clean Plugin][62]                                | [The Apache Software License, Version 2.0][26] |
| [Maven Resources Plugin][64]                            | [The Apache Software License, Version 2.0][26] |
| [Maven JAR Plugin][66]                                  | [The Apache Software License, Version 2.0][26] |
| [Maven Install Plugin][68]                              | [The Apache Software License, Version 2.0][26] |
| [Maven Site Plugin 3][70]                               | [The Apache Software License, Version 2.0][26] |

[42]: https://github.com/exasol/project-keeper-maven-plugin
[60]: https://www.eclemma.org/jacoco/index.html
[25]: http://www.snakeyaml.org
[2]: https://github.com/exasol/error-reporting-java
[26]: http://www.apache.org/licenses/LICENSE-2.0.txt
[17]: https://projectlombok.org
[30]: https://maven.apache.org/surefire/maven-surefire-plugin/
[54]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[62]: http://maven.apache.org/plugins/maven-clean-plugin/
[0]: https://aws.amazon.com/sdkforjava
[3]: https://opensource.org/licenses/MIT
[15]: https://github.com/mockito/mockito
[32]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[36]: http://www.mojohaus.org/versions-maven-plugin/
[48]: http://anthonywhitford.com/lombok.maven/lombok-maven-plugin/
[20]: http://opensource.org/licenses/BSD-3-Clause
[34]: https://maven.apache.org/plugins/maven-compiler-plugin/
[50]: http://maven.apache.org/plugins/maven-gpg-plugin/
[61]: https://www.eclipse.org/legal/epl-2.0/
[55]: http://www.eclipse.org/legal/epl-v10.html
[1]: https://aws.amazon.com/apache2.0
[16]: https://github.com/mockito/mockito/blob/main/LICENSE
[18]: https://projectlombok.org/LICENSE
[46]: http://zlika.github.io/reproducible-build-maven-plugin
[66]: http://maven.apache.org/plugins/maven-jar-plugin/
[13]: https://projects.eclipse.org/license/epl-2.0
[31]: https://www.apache.org/licenses/LICENSE-2.0.txt
[40]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[22]: https://www.eclipse.org/legal/epl-v20.html
[68]: http://maven.apache.org/plugins/maven-install-plugin/
[21]: https://junit.org/junit5/
[38]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[12]: https://github.com/eclipse-ee4j/jsonp
[56]: https://maven.apache.org/plugins/maven-source-plugin/
[10]: https://sdk.amazonaws.com/java/api/latest/software/amazon/awssdk/http/nio/netty/NettyNioAsyncHttpClient.html
[14]: https://projects.eclipse.org/license/secondary-gpl-2.0-cp
[19]: http://hamcrest.org/JavaHamcrest/
[52]: http://maven.apache.org/plugins/maven-deploy-plugin/
[70]: http://maven.apache.org/plugins/maven-site-plugin/
[64]: http://maven.apache.org/plugins/maven-resources-plugin/
[58]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[44]: https://github.com/exasol/error-code-crawler-maven-plugin
