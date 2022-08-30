<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                                                  | License                                                                                                      |
| ----------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------ |
| [AWS Java SDK :: Services :: Amazon S3][0]                  | [Apache License, Version 2.0][1]                                                                             |
| [error-reporting-java][2]                                   | [MIT][3]                                                                                                     |
| [AWS Java SDK :: Services :: AWS Lambda][0]                 | [Apache License, Version 2.0][1]                                                                             |
| [AWS Java SDK :: Services :: AWS IAM][0]                    | [Apache License, Version 2.0][1]                                                                             |
| [AWS Java SDK :: Services :: AWS STS][0]                    | [Apache License, Version 2.0][1]                                                                             |
| [AWS Java SDK :: HTTP Clients :: Netty Non-Blocking I/O][4] | [Apache License, Version 2.0][1]                                                                             |
| [Jakarta JSON Processing API][5]                            | [Eclipse Public License 2.0][6]; [GNU General Public License, version 2 with the GNU Classpath Exception][7] |
| [JSON-B API][8]                                             | [Eclipse Public License 2.0][6]; [GNU General Public License, version 2 with the GNU Classpath Exception][7] |
| [mockito-core][9]                                           | [The MIT License][10]                                                                                        |
| [Project Lombok][11]                                        | [The MIT License][12]                                                                                        |

## Test Dependencies

| Dependency                 | License                           |
| -------------------------- | --------------------------------- |
| [Hamcrest][13]             | [BSD License 3][14]               |
| [JUnit Jupiter Engine][15] | [Eclipse Public License v2.0][16] |
| [JUnit Jupiter Params][15] | [Eclipse Public License v2.0][16] |
| [SnakeYAML][17]            | [Apache License, Version 2.0][18] |
| [EqualsVerifier][19]       | [Apache License, Version 2.0][18] |

## Runtime Dependencies

| Dependency                   | License                                                                                                      |
| ---------------------------- | ------------------------------------------------------------------------------------------------------------ |
| [JSON-P Default Provider][5] | [Eclipse Public License 2.0][6]; [GNU General Public License, version 2 with the GNU Classpath Exception][7] |
| [org.eclipse.yasson][20]     | [Eclipse Public License v. 2.0][21]; [Eclipse Distribution License v. 1.0][22]                               |

## Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][23]                       | [GNU LGPL 3][24]                               |
| [Apache Maven Compiler Plugin][25]                      | [Apache License, Version 2.0][26]              |
| [Apache Maven Enforcer Plugin][27]                      | [Apache License, Version 2.0][26]              |
| [Maven Flatten Plugin][28]                              | [Apache Software Licenese][18]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][29] | [ASL2][18]                                     |
| [Maven Surefire Plugin][30]                             | [Apache License, Version 2.0][26]              |
| [Versions Maven Plugin][31]                             | [Apache License, Version 2.0][26]              |
| [Apache Maven Deploy Plugin][32]                        | [Apache License, Version 2.0][26]              |
| [Apache Maven GPG Plugin][33]                           | [Apache License, Version 2.0][18]              |
| [Apache Maven Source Plugin][34]                        | [Apache License, Version 2.0][26]              |
| [Apache Maven Javadoc Plugin][35]                       | [Apache License, Version 2.0][26]              |
| [Nexus Staging Maven Plugin][36]                        | [Eclipse Public License][37]                   |
| [Lombok Maven Plugin][38]                               | [The MIT License][3]                           |
| [Maven Failsafe Plugin][39]                             | [Apache License, Version 2.0][26]              |
| [JaCoCo :: Maven Plugin][40]                            | [Eclipse Public License 2.0][41]               |
| [Project keeper maven plugin][42]                       | [The MIT License][43]                          |
| [error-code-crawler-maven-plugin][44]                   | [MIT][3]                                       |
| [Reproducible Build Maven Plugin][45]                   | [Apache 2.0][18]                               |
| [Maven Clean Plugin][46]                                | [The Apache Software License, Version 2.0][18] |
| [Maven Resources Plugin][47]                            | [The Apache Software License, Version 2.0][18] |
| [Maven JAR Plugin][48]                                  | [The Apache Software License, Version 2.0][18] |
| [Maven Install Plugin][49]                              | [The Apache Software License, Version 2.0][18] |
| [Maven Site Plugin 3][50]                               | [The Apache Software License, Version 2.0][18] |

[0]: https://aws.amazon.com/sdkforjava
[1]: https://aws.amazon.com/apache2.0
[2]: https://github.com/exasol/error-reporting-java
[3]: https://opensource.org/licenses/MIT
[4]: https://aws.amazon.com/sdkforjava/http-clients/netty-nio-client
[5]: https://github.com/eclipse-ee4j/jsonp
[6]: https://projects.eclipse.org/license/epl-2.0
[7]: https://projects.eclipse.org/license/secondary-gpl-2.0-cp
[8]: https://eclipse-ee4j.github.io/jsonb-api
[9]: https://github.com/mockito/mockito
[10]: https://github.com/mockito/mockito/blob/main/LICENSE
[11]: https://projectlombok.org
[12]: https://projectlombok.org/LICENSE
[13]: http://hamcrest.org/JavaHamcrest/
[14]: http://opensource.org/licenses/BSD-3-Clause
[15]: https://junit.org/junit5/
[16]: https://www.eclipse.org/legal/epl-v20.html
[17]: http://www.snakeyaml.org
[18]: http://www.apache.org/licenses/LICENSE-2.0.txt
[19]: http://www.jqno.nl/equalsverifier
[20]: https://projects.eclipse.org/projects/ee4j.yasson
[21]: http://www.eclipse.org/legal/epl-v20.html
[22]: http://www.eclipse.org/org/documents/edl-v10.php
[23]: http://sonarsource.github.io/sonar-scanner-maven/
[24]: http://www.gnu.org/licenses/lgpl.txt
[25]: https://maven.apache.org/plugins/maven-compiler-plugin/
[26]: https://www.apache.org/licenses/LICENSE-2.0.txt
[27]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[28]: https://www.mojohaus.org/flatten-maven-plugin/
[29]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[30]: https://maven.apache.org/surefire/maven-surefire-plugin/
[31]: http://www.mojohaus.org/versions-maven-plugin/
[32]: https://maven.apache.org/plugins/maven-deploy-plugin/
[33]: http://maven.apache.org/plugins/maven-gpg-plugin/
[34]: https://maven.apache.org/plugins/maven-source-plugin/
[35]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[36]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[37]: http://www.eclipse.org/legal/epl-v10.html
[38]: https://anthonywhitford.com/lombok.maven/lombok-maven-plugin/
[39]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[40]: https://www.eclemma.org/jacoco/index.html
[41]: https://www.eclipse.org/legal/epl-2.0/
[42]: https://github.com/exasol/project-keeper/
[43]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[44]: https://github.com/exasol/error-code-crawler-maven-plugin
[45]: http://zlika.github.io/reproducible-build-maven-plugin
[46]: http://maven.apache.org/plugins/maven-clean-plugin/
[47]: http://maven.apache.org/plugins/maven-resources-plugin/
[48]: http://maven.apache.org/plugins/maven-jar-plugin/
[49]: http://maven.apache.org/plugins/maven-install-plugin/
[50]: http://maven.apache.org/plugins/maven-site-plugin/
