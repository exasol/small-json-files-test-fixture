<!-- @formatter:off -->
# Dependencies

## Small Json Files Test Fixture

### Compile Dependencies

| Dependency                                  | License                                                                                                      |
| ------------------------------------------- | ------------------------------------------------------------------------------------------------------------ |
| [AWS Java SDK :: Services :: Amazon S3][0]  | [Apache License, Version 2.0][1]                                                                             |
| [error-reporting-java][2]                   | [MIT License][3]                                                                                             |
| [AWS Java SDK :: Services :: AWS Lambda][0] | [Apache License, Version 2.0][1]                                                                             |
| [AWS Java SDK :: Services :: AWS IAM][0]    | [Apache License, Version 2.0][1]                                                                             |
| [AWS Java SDK :: Services :: AWS STS][0]    | [Apache License, Version 2.0][1]                                                                             |
| [AWS Java SDK :: HTTP Clients :: Apache][4] | [Apache License, Version 2.0][1]                                                                             |
| [Jakarta JSON Processing API][5]            | [Eclipse Public License 2.0][6]; [GNU General Public License, version 2 with the GNU Classpath Exception][7] |
| [JSON-B API][8]                             | [Eclipse Public License 2.0][6]; [GNU General Public License, version 2 with the GNU Classpath Exception][7] |
| [mockito-core][9]                           | [MIT][10]                                                                                                    |

### Test Dependencies

| Dependency                                 | License                           |
| ------------------------------------------ | --------------------------------- |
| [Hamcrest][11]                             | [BSD License 3][12]               |
| [JUnit Jupiter Params][13]                 | [Eclipse Public License v2.0][14] |
| [EqualsVerifier \| release normal jar][15] | [Apache License, Version 2.0][16] |
| [SLF4J JDK14 Provider][17]                 | [MIT License][18]                 |

### Runtime Dependencies

| Dependency            | License                                                                                                      |
| --------------------- | ------------------------------------------------------------------------------------------------------------ |
| [Yasson][19]          | [Eclipse Public License v. 2.0][20]; [Eclipse Distribution License v. 1.0][21]                               |
| [Eclipse Parsson][22] | [Eclipse Public License 2.0][6]; [GNU General Public License, version 2 with the GNU Classpath Exception][7] |

### Plugin Dependencies

| Dependency                                              | License                           |
| ------------------------------------------------------- | --------------------------------- |
| [SonarQube Scanner for Maven][23]                       | [GNU LGPL 3][24]                  |
| [Apache Maven Compiler Plugin][25]                      | [Apache-2.0][16]                  |
| [Apache Maven Enforcer Plugin][26]                      | [Apache-2.0][16]                  |
| [Maven Flatten Plugin][27]                              | [Apache Software Licenese][16]    |
| [Project keeper maven plugin][28]                       | [The MIT License][29]             |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][30] | [ASL2][31]                        |
| [Maven Surefire Plugin][32]                             | [Apache-2.0][16]                  |
| [Versions Maven Plugin][33]                             | [Apache License, Version 2.0][16] |
| [duplicate-finder-maven-plugin Maven Mojo][34]          | [Apache License 2.0][35]          |
| [Apache Maven Deploy Plugin][36]                        | [Apache-2.0][16]                  |
| [Apache Maven GPG Plugin][37]                           | [Apache-2.0][16]                  |
| [Apache Maven Source Plugin][38]                        | [Apache License, Version 2.0][16] |
| [Apache Maven Javadoc Plugin][39]                       | [Apache-2.0][16]                  |
| [Nexus Staging Maven Plugin][40]                        | [Eclipse Public License][41]      |
| [Maven Failsafe Plugin][42]                             | [Apache-2.0][16]                  |
| [JaCoCo :: Maven Plugin][43]                            | [Eclipse Public License 2.0][44]  |
| [error-code-crawler-maven-plugin][45]                   | [MIT License][46]                 |
| [Reproducible Build Maven Plugin][47]                   | [Apache 2.0][31]                  |

## Createjsonfileslambda

### Compile Dependencies

| Dependency                   | License          |
| ---------------------------- | ---------------- |
| [@aws-sdk/client-lambda][48] | [Apache-2.0][49] |
| [@aws-sdk/client-s3][50]     | [Apache-2.0][49] |

[0]: https://aws.amazon.com/sdkforjava
[1]: https://aws.amazon.com/apache2.0
[2]: https://github.com/exasol/error-reporting-java/
[3]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[4]: https://sdk.amazonaws.com/java/api/latest/software/amazon/awssdk/http/apache/ApacheHttpClient.html
[5]: https://github.com/eclipse-ee4j/jsonp
[6]: https://projects.eclipse.org/license/epl-2.0
[7]: https://projects.eclipse.org/license/secondary-gpl-2.0-cp
[8]: https://github.com/eclipse-ee4j/jsonb-api
[9]: https://github.com/mockito/mockito
[10]: https://opensource.org/licenses/MIT
[11]: http://hamcrest.org/JavaHamcrest/
[12]: http://opensource.org/licenses/BSD-3-Clause
[13]: https://junit.org/junit5/
[14]: https://www.eclipse.org/legal/epl-v20.html
[15]: https://www.jqno.nl/equalsverifier
[16]: https://www.apache.org/licenses/LICENSE-2.0.txt
[17]: http://www.slf4j.org
[18]: http://www.opensource.org/licenses/mit-license.php
[19]: https://projects.eclipse.org/projects/ee4j.yasson
[20]: http://www.eclipse.org/legal/epl-v20.html
[21]: http://www.eclipse.org/org/documents/edl-v10.php
[22]: https://github.com/eclipse-ee4j/parsson
[23]: http://sonarsource.github.io/sonar-scanner-maven/
[24]: http://www.gnu.org/licenses/lgpl.txt
[25]: https://maven.apache.org/plugins/maven-compiler-plugin/
[26]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[27]: https://www.mojohaus.org/flatten-maven-plugin/
[28]: https://github.com/exasol/project-keeper/
[29]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[30]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[31]: http://www.apache.org/licenses/LICENSE-2.0.txt
[32]: https://maven.apache.org/surefire/maven-surefire-plugin/
[33]: https://www.mojohaus.org/versions/versions-maven-plugin/
[34]: https://basepom.github.io/duplicate-finder-maven-plugin
[35]: http://www.apache.org/licenses/LICENSE-2.0.html
[36]: https://maven.apache.org/plugins/maven-deploy-plugin/
[37]: https://maven.apache.org/plugins/maven-gpg-plugin/
[38]: https://maven.apache.org/plugins/maven-source-plugin/
[39]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[40]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[41]: http://www.eclipse.org/legal/epl-v10.html
[42]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[43]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[44]: https://www.eclipse.org/legal/epl-2.0/
[45]: https://github.com/exasol/error-code-crawler-maven-plugin/
[46]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[47]: http://zlika.github.io/reproducible-build-maven-plugin
[48]: https://registry.npmjs.org/@aws-sdk/client-lambda/-/client-lambda-3.363.0.tgz
[49]: https://github.com/aws/aws-sdk-js-v3
[50]: https://registry.npmjs.org/@aws-sdk/client-s3/-/client-s3-3.363.0.tgz
