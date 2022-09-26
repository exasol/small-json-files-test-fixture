<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                                                  | License                                                                                                      |
| ----------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------ |
| [AWS Java SDK :: Services :: Amazon S3][0]                  | [Apache License, Version 2.0][1]                                                                             |
| [Apache Commons Codec][2]                                   | [Apache License, Version 2.0][3]                                                                             |
| [error-reporting-java][4]                                   | [MIT][5]                                                                                                     |
| [AWS Java SDK :: Services :: AWS Lambda][0]                 | [Apache License, Version 2.0][1]                                                                             |
| [AWS Java SDK :: Services :: AWS IAM][0]                    | [Apache License, Version 2.0][1]                                                                             |
| [AWS Java SDK :: Services :: AWS STS][0]                    | [Apache License, Version 2.0][1]                                                                             |
| [AWS Java SDK :: HTTP Clients :: Netty Non-Blocking I/O][6] | [Apache License, Version 2.0][1]                                                                             |
| [Jakarta JSON Processing API][7]                            | [Eclipse Public License 2.0][8]; [GNU General Public License, version 2 with the GNU Classpath Exception][9] |
| [JSON-B API][10]                                            | [Eclipse Public License 2.0][8]; [GNU General Public License, version 2 with the GNU Classpath Exception][9] |
| [mockito-core][11]                                          | [The MIT License][12]                                                                                        |
| [Project Lombok][13]                                        | [The MIT License][14]                                                                                        |

## Test Dependencies

| Dependency                                | License                           |
| ----------------------------------------- | --------------------------------- |
| [Hamcrest][15]                            | [BSD License 3][16]               |
| [JUnit Jupiter Engine][17]                | [Eclipse Public License v2.0][18] |
| [JUnit Jupiter Params][17]                | [Eclipse Public License v2.0][18] |
| [EqualsVerifier | release normal jar][19] | [Apache License, Version 2.0][3]  |
| [SLF4J JDK14 Binding][20]                 | [MIT License][21]                 |

## Runtime Dependencies

| Dependency                   | License                                                                                                      |
| ---------------------------- | ------------------------------------------------------------------------------------------------------------ |
| [JSON-P Default Provider][7] | [Eclipse Public License 2.0][8]; [GNU General Public License, version 2 with the GNU Classpath Exception][9] |
| [Yasson][22]                 | [Eclipse Public License v. 2.0][23]; [Eclipse Distribution License v. 1.0][24]                               |

## Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][25]                       | [GNU LGPL 3][26]                               |
| [Apache Maven Compiler Plugin][27]                      | [Apache License, Version 2.0][3]               |
| [Apache Maven Enforcer Plugin][28]                      | [Apache License, Version 2.0][3]               |
| [Maven Flatten Plugin][29]                              | [Apache Software Licenese][30]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][31] | [ASL2][30]                                     |
| [Maven Surefire Plugin][32]                             | [Apache License, Version 2.0][3]               |
| [Versions Maven Plugin][33]                             | [Apache License, Version 2.0][3]               |
| [Apache Maven Deploy Plugin][34]                        | [Apache License, Version 2.0][3]               |
| [Apache Maven GPG Plugin][35]                           | [Apache License, Version 2.0][3]               |
| [Apache Maven Source Plugin][36]                        | [Apache License, Version 2.0][3]               |
| [Apache Maven Javadoc Plugin][37]                       | [Apache License, Version 2.0][3]               |
| [Nexus Staging Maven Plugin][38]                        | [Eclipse Public License][39]                   |
| [Maven Failsafe Plugin][40]                             | [Apache License, Version 2.0][3]               |
| [JaCoCo :: Maven Plugin][41]                            | [Eclipse Public License 2.0][42]               |
| [error-code-crawler-maven-plugin][43]                   | [MIT License][44]                              |
| [Reproducible Build Maven Plugin][45]                   | [Apache 2.0][30]                               |
| [Project keeper maven plugin][46]                       | [The MIT License][47]                          |
| [Maven Clean Plugin][48]                                | [The Apache Software License, Version 2.0][30] |
| [Maven Resources Plugin][49]                            | [The Apache Software License, Version 2.0][30] |
| [Maven JAR Plugin][50]                                  | [The Apache Software License, Version 2.0][30] |
| [Maven Install Plugin][51]                              | [The Apache Software License, Version 2.0][30] |
| [Maven Site Plugin 3][52]                               | [The Apache Software License, Version 2.0][30] |

[0]: https://aws.amazon.com/sdkforjava
[1]: https://aws.amazon.com/apache2.0
[2]: https://commons.apache.org/proper/commons-codec/
[3]: https://www.apache.org/licenses/LICENSE-2.0.txt
[4]: https://github.com/exasol/error-reporting-java
[5]: https://opensource.org/licenses/MIT
[6]: https://sdk.amazonaws.com/java/api/latest/software/amazon/awssdk/http/nio/netty/NettyNioAsyncHttpClient.html
[7]: https://github.com/eclipse-ee4j/jsonp
[8]: https://projects.eclipse.org/license/epl-2.0
[9]: https://projects.eclipse.org/license/secondary-gpl-2.0-cp
[10]: https://github.com/eclipse-ee4j/jsonb-api
[11]: https://github.com/mockito/mockito
[12]: https://github.com/mockito/mockito/blob/main/LICENSE
[13]: https://projectlombok.org
[14]: https://projectlombok.org/LICENSE
[15]: http://hamcrest.org/JavaHamcrest/
[16]: http://opensource.org/licenses/BSD-3-Clause
[17]: https://junit.org/junit5/
[18]: https://www.eclipse.org/legal/epl-v20.html
[19]: https://www.jqno.nl/equalsverifier
[20]: http://www.slf4j.org
[21]: http://www.opensource.org/licenses/mit-license.php
[22]: https://projects.eclipse.org/projects/ee4j.yasson
[23]: http://www.eclipse.org/legal/epl-v20.html
[24]: http://www.eclipse.org/org/documents/edl-v10.php
[25]: http://sonarsource.github.io/sonar-scanner-maven/
[26]: http://www.gnu.org/licenses/lgpl.txt
[27]: https://maven.apache.org/plugins/maven-compiler-plugin/
[28]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[29]: https://www.mojohaus.org/flatten-maven-plugin/
[30]: http://www.apache.org/licenses/LICENSE-2.0.txt
[31]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[32]: https://maven.apache.org/surefire/maven-surefire-plugin/
[33]: http://www.mojohaus.org/versions-maven-plugin/
[34]: https://maven.apache.org/plugins/maven-deploy-plugin/
[35]: https://maven.apache.org/plugins/maven-gpg-plugin/
[36]: https://maven.apache.org/plugins/maven-source-plugin/
[37]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[38]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[39]: http://www.eclipse.org/legal/epl-v10.html
[40]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[41]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[42]: https://www.eclipse.org/legal/epl-2.0/
[43]: https://github.com/exasol/error-code-crawler-maven-plugin/
[44]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[45]: http://zlika.github.io/reproducible-build-maven-plugin
[46]: https://github.com/exasol/project-keeper/
[47]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[48]: http://maven.apache.org/plugins/maven-clean-plugin/
[49]: http://maven.apache.org/plugins/maven-resources-plugin/
[50]: http://maven.apache.org/plugins/maven-jar-plugin/
[51]: http://maven.apache.org/plugins/maven-install-plugin/
[52]: http://maven.apache.org/plugins/maven-site-plugin/
