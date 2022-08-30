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
| [SnakeYAML][19]                           | [Apache License, Version 2.0][20] |
| [EqualsVerifier | release normal jar][21] | [Apache License, Version 2.0][3]  |
| [SLF4J JDK14 Binding][22]                 | [MIT License][23]                 |

## Runtime Dependencies

| Dependency                   | License                                                                                                      |
| ---------------------------- | ------------------------------------------------------------------------------------------------------------ |
| [JSON-P Default Provider][7] | [Eclipse Public License 2.0][8]; [GNU General Public License, version 2 with the GNU Classpath Exception][9] |
| [Yasson][24]                 | [Eclipse Public License v. 2.0][25]; [Eclipse Distribution License v. 1.0][26]                               |

## Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][27]                       | [GNU LGPL 3][28]                               |
| [Apache Maven Compiler Plugin][29]                      | [Apache License, Version 2.0][3]               |
| [Apache Maven Enforcer Plugin][30]                      | [Apache License, Version 2.0][3]               |
| [Maven Flatten Plugin][31]                              | [Apache Software Licenese][20]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][32] | [ASL2][20]                                     |
| [Maven Surefire Plugin][33]                             | [Apache License, Version 2.0][3]               |
| [Versions Maven Plugin][34]                             | [Apache License, Version 2.0][3]               |
| [Apache Maven Deploy Plugin][35]                        | [Apache License, Version 2.0][3]               |
| [Apache Maven GPG Plugin][36]                           | [Apache License, Version 2.0][3]               |
| [Apache Maven Source Plugin][37]                        | [Apache License, Version 2.0][3]               |
| [Apache Maven Javadoc Plugin][38]                       | [Apache License, Version 2.0][3]               |
| [Nexus Staging Maven Plugin][39]                        | [Eclipse Public License][40]                   |
| [Lombok Maven Plugin][41]                               | [The MIT License][5]                           |
| [Maven Failsafe Plugin][42]                             | [Apache License, Version 2.0][3]               |
| [JaCoCo :: Maven Plugin][43]                            | [Eclipse Public License 2.0][44]               |
| [error-code-crawler-maven-plugin][45]                   | [MIT License][46]                              |
| [Reproducible Build Maven Plugin][47]                   | [Apache 2.0][20]                               |
| [Project keeper maven plugin][48]                       | [The MIT License][49]                          |
| [Maven Clean Plugin][50]                                | [The Apache Software License, Version 2.0][20] |
| [Maven Resources Plugin][51]                            | [The Apache Software License, Version 2.0][20] |
| [Maven JAR Plugin][52]                                  | [The Apache Software License, Version 2.0][20] |
| [Maven Install Plugin][53]                              | [The Apache Software License, Version 2.0][20] |
| [Maven Site Plugin 3][54]                               | [The Apache Software License, Version 2.0][20] |

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
[19]: https://bitbucket.org/snakeyaml/snakeyaml
[20]: http://www.apache.org/licenses/LICENSE-2.0.txt
[21]: https://www.jqno.nl/equalsverifier
[22]: http://www.slf4j.org
[23]: http://www.opensource.org/licenses/mit-license.php
[24]: https://projects.eclipse.org/projects/ee4j.yasson
[25]: http://www.eclipse.org/legal/epl-v20.html
[26]: http://www.eclipse.org/org/documents/edl-v10.php
[27]: http://sonarsource.github.io/sonar-scanner-maven/
[28]: http://www.gnu.org/licenses/lgpl.txt
[29]: https://maven.apache.org/plugins/maven-compiler-plugin/
[30]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[31]: https://www.mojohaus.org/flatten-maven-plugin/
[32]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[33]: https://maven.apache.org/surefire/maven-surefire-plugin/
[34]: http://www.mojohaus.org/versions-maven-plugin/
[35]: https://maven.apache.org/plugins/maven-deploy-plugin/
[36]: https://maven.apache.org/plugins/maven-gpg-plugin/
[37]: https://maven.apache.org/plugins/maven-source-plugin/
[38]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[39]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[40]: http://www.eclipse.org/legal/epl-v10.html
[41]: http://anthonywhitford.com/lombok.maven/lombok-maven-plugin
[42]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[43]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[44]: https://www.eclipse.org/legal/epl-2.0/
[45]: https://github.com/exasol/error-code-crawler-maven-plugin/
[46]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[47]: http://zlika.github.io/reproducible-build-maven-plugin
[48]: https://github.com/exasol/project-keeper/
[49]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[50]: http://maven.apache.org/plugins/maven-clean-plugin/
[51]: http://maven.apache.org/plugins/maven-resources-plugin/
[52]: http://maven.apache.org/plugins/maven-jar-plugin/
[53]: http://maven.apache.org/plugins/maven-install-plugin/
[54]: http://maven.apache.org/plugins/maven-site-plugin/
