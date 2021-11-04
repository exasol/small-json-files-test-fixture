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
| [JUnit][25]                | [Eclipse Public License 1.0][26]  |
| [SnakeYAML][27]            | [Apache License, Version 2.0][28] |

## Runtime Dependencies

| Dependency                    | License                                                                                                        |
| ----------------------------- | -------------------------------------------------------------------------------------------------------------- |
| [JSON-P Default Provider][12] | [Eclipse Public License 2.0][13]; [GNU General Public License, version 2 with the GNU Classpath Exception][14] |

## Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [Maven Surefire Plugin][32]                             | [Apache License, Version 2.0][33]              |
| [Maven Failsafe Plugin][34]                             | [Apache License, Version 2.0][33]              |
| [Apache Maven Compiler Plugin][36]                      | [Apache License, Version 2.0][33]              |
| [Versions Maven Plugin][38]                             | [Apache License, Version 2.0][33]              |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][40] | [ASL2][28]                                     |
| [Apache Maven Enforcer Plugin][42]                      | [Apache License, Version 2.0][33]              |
| [Project keeper maven plugin][44]                       | [MIT][3]                                       |
| [error-code-crawler-maven-plugin][46]                   | [MIT][3]                                       |
| [Reproducible Build Maven Plugin][48]                   | [Apache 2.0][28]                               |
| [Lombok Maven Plugin][50]                               | [The MIT License][3]                           |
| [Apache Maven GPG Plugin][52]                           | [Apache License, Version 2.0][28]              |
| [Maven Deploy Plugin][54]                               | [The Apache Software License, Version 2.0][28] |
| [Nexus Staging Maven Plugin][56]                        | [Eclipse Public License][26]                   |
| [Apache Maven Source Plugin][58]                        | [Apache License, Version 2.0][33]              |
| [Apache Maven Javadoc Plugin][60]                       | [Apache License, Version 2.0][33]              |
| [JaCoCo :: Maven Plugin][62]                            | [Eclipse Public License 2.0][63]               |
| [Maven Clean Plugin][64]                                | [The Apache Software License, Version 2.0][28] |
| [Maven Resources Plugin][66]                            | [The Apache Software License, Version 2.0][28] |
| [Maven JAR Plugin][68]                                  | [The Apache Software License, Version 2.0][28] |
| [Maven Install Plugin][70]                              | [The Apache Software License, Version 2.0][28] |
| [Maven Site Plugin 3][72]                               | [The Apache Software License, Version 2.0][28] |

[44]: https://github.com/exasol/project-keeper-maven-plugin
[62]: https://www.eclemma.org/jacoco/index.html
[27]: http://www.snakeyaml.org
[2]: https://github.com/exasol/error-reporting-java
[28]: http://www.apache.org/licenses/LICENSE-2.0.txt
[17]: https://projectlombok.org
[32]: https://maven.apache.org/surefire/maven-surefire-plugin/
[56]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[64]: http://maven.apache.org/plugins/maven-clean-plugin/
[0]: https://aws.amazon.com/sdkforjava
[3]: https://opensource.org/licenses/MIT
[15]: https://github.com/mockito/mockito
[34]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[10]: https://aws.amazon.com/sdkforjava/http-clients/netty-nio-client
[38]: http://www.mojohaus.org/versions-maven-plugin/
[50]: http://anthonywhitford.com/lombok.maven/lombok-maven-plugin/
[20]: http://opensource.org/licenses/BSD-3-Clause
[36]: https://maven.apache.org/plugins/maven-compiler-plugin/
[52]: http://maven.apache.org/plugins/maven-gpg-plugin/
[25]: http://junit.org
[63]: https://www.eclipse.org/legal/epl-2.0/
[26]: http://www.eclipse.org/legal/epl-v10.html
[1]: https://aws.amazon.com/apache2.0
[16]: https://github.com/mockito/mockito/blob/main/LICENSE
[18]: https://projectlombok.org/LICENSE
[48]: http://zlika.github.io/reproducible-build-maven-plugin
[68]: http://maven.apache.org/plugins/maven-jar-plugin/
[13]: https://projects.eclipse.org/license/epl-2.0
[33]: https://www.apache.org/licenses/LICENSE-2.0.txt
[42]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[22]: https://www.eclipse.org/legal/epl-v20.html
[70]: http://maven.apache.org/plugins/maven-install-plugin/
[21]: https://junit.org/junit5/
[40]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[12]: https://github.com/eclipse-ee4j/jsonp
[58]: https://maven.apache.org/plugins/maven-source-plugin/
[14]: https://projects.eclipse.org/license/secondary-gpl-2.0-cp
[19]: http://hamcrest.org/JavaHamcrest/
[54]: http://maven.apache.org/plugins/maven-deploy-plugin/
[72]: http://maven.apache.org/plugins/maven-site-plugin/
[66]: http://maven.apache.org/plugins/maven-resources-plugin/
[60]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[46]: https://github.com/exasol/error-code-crawler-maven-plugin
