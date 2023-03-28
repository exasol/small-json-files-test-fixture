<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                                                  | License                                                                                                      |
| ----------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------ |
| [AWS Java SDK :: Services :: Amazon S3][0]                  | [Apache License, Version 2.0][1]                                                                             |
| [Apache Commons Codec][2]                                   | [Apache License, Version 2.0][3]                                                                             |
| [error-reporting-java][4]                                   | [MIT License][5]                                                                                             |
| [AWS Java SDK :: Services :: AWS Lambda][0]                 | [Apache License, Version 2.0][1]                                                                             |
| [AWS Java SDK :: Services :: AWS IAM][0]                    | [Apache License, Version 2.0][1]                                                                             |
| [AWS Java SDK :: Services :: AWS STS][0]                    | [Apache License, Version 2.0][1]                                                                             |
| [AWS Java SDK :: HTTP Clients :: Netty Non-Blocking I/O][6] | [Apache License, Version 2.0][1]                                                                             |
| [Jakarta JSON Processing API][7]                            | [Eclipse Public License 2.0][8]; [GNU General Public License, version 2 with the GNU Classpath Exception][9] |
| [JSON-B API][10]                                            | [Eclipse Public License 2.0][8]; [GNU General Public License, version 2 with the GNU Classpath Exception][9] |
| [mockito-core][11]                                          | [The MIT License][12]                                                                                        |

## Test Dependencies

| Dependency                                | License                           |
| ----------------------------------------- | --------------------------------- |
| [Hamcrest][13]                            | [BSD License 3][14]               |
| [JUnit Jupiter Engine][15]                | [Eclipse Public License v2.0][16] |
| [JUnit Jupiter Params][15]                | [Eclipse Public License v2.0][16] |
| [EqualsVerifier | release normal jar][17] | [Apache License, Version 2.0][3]  |
| [SLF4J JDK14 Binding][18]                 | [MIT License][19]                 |

## Runtime Dependencies

| Dependency   | License                                                                        |
| ------------ | ------------------------------------------------------------------------------ |
| [Yasson][20] | [Eclipse Public License v. 2.0][21]; [Eclipse Distribution License v. 1.0][22] |

## Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][23]                       | [GNU LGPL 3][24]                               |
| [Apache Maven Compiler Plugin][25]                      | [Apache License, Version 2.0][3]               |
| [Apache Maven Enforcer Plugin][26]                      | [Apache-2.0][3]                                |
| [Maven Flatten Plugin][27]                              | [Apache Software Licenese][3]                  |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][28] | [ASL2][29]                                     |
| [Maven Surefire Plugin][30]                             | [Apache License, Version 2.0][3]               |
| [Versions Maven Plugin][31]                             | [Apache License, Version 2.0][3]               |
| [Apache Maven Deploy Plugin][32]                        | [Apache-2.0][3]                                |
| [Apache Maven GPG Plugin][33]                           | [Apache License, Version 2.0][3]               |
| [Apache Maven Source Plugin][34]                        | [Apache License, Version 2.0][3]               |
| [Apache Maven Javadoc Plugin][35]                       | [Apache License, Version 2.0][3]               |
| [Nexus Staging Maven Plugin][36]                        | [Eclipse Public License][37]                   |
| [Maven Failsafe Plugin][38]                             | [Apache License, Version 2.0][3]               |
| [JaCoCo :: Maven Plugin][39]                            | [Eclipse Public License 2.0][40]               |
| [error-code-crawler-maven-plugin][41]                   | [MIT License][42]                              |
| [Reproducible Build Maven Plugin][43]                   | [Apache 2.0][29]                               |
| [Project keeper maven plugin][44]                       | [The MIT License][45]                          |
| [Maven Clean Plugin][46]                                | [The Apache Software License, Version 2.0][29] |
| [Maven Resources Plugin][47]                            | [The Apache Software License, Version 2.0][29] |
| [Maven JAR Plugin][48]                                  | [The Apache Software License, Version 2.0][29] |
| [Maven Install Plugin][49]                              | [The Apache Software License, Version 2.0][29] |
| [Maven Site Plugin 3][50]                               | [The Apache Software License, Version 2.0][29] |

[0]: https://aws.amazon.com/sdkforjava
[1]: https://aws.amazon.com/apache2.0
[2]: https://commons.apache.org/proper/commons-codec/
[3]: https://www.apache.org/licenses/LICENSE-2.0.txt
[4]: https://github.com/exasol/error-reporting-java/
[5]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[6]: https://sdk.amazonaws.com/java/api/latest/software/amazon/awssdk/http/nio/netty/NettyNioAsyncHttpClient.html
[7]: https://github.com/eclipse-ee4j/jsonp
[8]: https://projects.eclipse.org/license/epl-2.0
[9]: https://projects.eclipse.org/license/secondary-gpl-2.0-cp
[10]: https://github.com/eclipse-ee4j/jsonb-api
[11]: https://github.com/mockito/mockito
[12]: https://github.com/mockito/mockito/blob/main/LICENSE
[13]: http://hamcrest.org/JavaHamcrest/
[14]: http://opensource.org/licenses/BSD-3-Clause
[15]: https://junit.org/junit5/
[16]: https://www.eclipse.org/legal/epl-v20.html
[17]: https://www.jqno.nl/equalsverifier
[18]: http://www.slf4j.org
[19]: http://www.opensource.org/licenses/mit-license.php
[20]: https://projects.eclipse.org/projects/ee4j.yasson
[21]: http://www.eclipse.org/legal/epl-v20.html
[22]: http://www.eclipse.org/org/documents/edl-v10.php
[23]: http://sonarsource.github.io/sonar-scanner-maven/
[24]: http://www.gnu.org/licenses/lgpl.txt
[25]: https://maven.apache.org/plugins/maven-compiler-plugin/
[26]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[27]: https://www.mojohaus.org/flatten-maven-plugin/
[28]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[29]: http://www.apache.org/licenses/LICENSE-2.0.txt
[30]: https://maven.apache.org/surefire/maven-surefire-plugin/
[31]: https://www.mojohaus.org/versions/versions-maven-plugin/
[32]: https://maven.apache.org/plugins/maven-deploy-plugin/
[33]: https://maven.apache.org/plugins/maven-gpg-plugin/
[34]: https://maven.apache.org/plugins/maven-source-plugin/
[35]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[36]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[37]: http://www.eclipse.org/legal/epl-v10.html
[38]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[39]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[40]: https://www.eclipse.org/legal/epl-2.0/
[41]: https://github.com/exasol/error-code-crawler-maven-plugin/
[42]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[43]: http://zlika.github.io/reproducible-build-maven-plugin
[44]: https://github.com/exasol/project-keeper/
[45]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[46]: http://maven.apache.org/plugins/maven-clean-plugin/
[47]: http://maven.apache.org/plugins/maven-resources-plugin/
[48]: http://maven.apache.org/plugins/maven-jar-plugin/
[49]: http://maven.apache.org/plugins/maven-install-plugin/
[50]: http://maven.apache.org/plugins/maven-site-plugin/
