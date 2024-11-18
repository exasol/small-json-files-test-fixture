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
| [Project Keeper Maven plugin][23]                       | [The MIT License][24]             |
| [Apache Maven Clean Plugin][25]                         | [Apache-2.0][16]                  |
| [Apache Maven Install Plugin][26]                       | [Apache-2.0][16]                  |
| [Apache Maven Resources Plugin][27]                     | [Apache-2.0][16]                  |
| [Apache Maven Site Plugin][28]                          | [Apache License, Version 2.0][16] |
| [SonarQube Scanner for Maven][29]                       | [GNU LGPL 3][30]                  |
| [Apache Maven Toolchains Plugin][31]                    | [Apache-2.0][16]                  |
| [Apache Maven Compiler Plugin][32]                      | [Apache-2.0][16]                  |
| [Apache Maven Enforcer Plugin][33]                      | [Apache-2.0][16]                  |
| [Maven Flatten Plugin][34]                              | [Apache Software Licenese][16]    |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][35] | [ASL2][36]                        |
| [Maven Surefire Plugin][37]                             | [Apache-2.0][16]                  |
| [Versions Maven Plugin][38]                             | [Apache License, Version 2.0][16] |
| [duplicate-finder-maven-plugin Maven Mojo][39]          | [Apache License 2.0][40]          |
| [Apache Maven Deploy Plugin][41]                        | [Apache-2.0][16]                  |
| [Apache Maven GPG Plugin][42]                           | [Apache-2.0][16]                  |
| [Apache Maven Source Plugin][43]                        | [Apache License, Version 2.0][16] |
| [Apache Maven Javadoc Plugin][44]                       | [Apache-2.0][16]                  |
| [Nexus Staging Maven Plugin][45]                        | [Eclipse Public License][46]      |
| [Maven Failsafe Plugin][47]                             | [Apache-2.0][16]                  |
| [JaCoCo :: Maven Plugin][48]                            | [EPL-2.0][49]                     |
| [Quality Summarizer Maven Plugin][50]                   | [MIT License][51]                 |
| [error-code-crawler-maven-plugin][52]                   | [MIT License][53]                 |
| [Reproducible Build Maven Plugin][54]                   | [Apache 2.0][36]                  |
| [Exec Maven Plugin][55]                                 | [Apache License 2][16]            |

## Createjsonfileslambda

### Compile Dependencies

| Dependency                   | License          |
| ---------------------------- | ---------------- |
| [@aws-sdk/client-lambda][56] | [Apache-2.0][57] |
| [@aws-sdk/client-s3][58]     | [Apache-2.0][57] |

[0]: https://aws.amazon.com/sdkforjava
[1]: https://aws.amazon.com/apache2.0
[2]: https://github.com/exasol/error-reporting-java/
[3]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[4]: https://sdk.amazonaws.com/java/api/latest/software/amazon/awssdk/http/apache/ApacheHttpClient.html
[5]: https://github.com/eclipse-ee4j/jsonp
[6]: https://projects.eclipse.org/license/epl-2.0
[7]: https://projects.eclipse.org/license/secondary-gpl-2.0-cp
[8]: https://jakartaee.github.io/jsonb-api
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
[23]: https://github.com/exasol/project-keeper/
[24]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[25]: https://maven.apache.org/plugins/maven-clean-plugin/
[26]: https://maven.apache.org/plugins/maven-install-plugin/
[27]: https://maven.apache.org/plugins/maven-resources-plugin/
[28]: https://maven.apache.org/plugins/maven-site-plugin/
[29]: http://sonarsource.github.io/sonar-scanner-maven/
[30]: http://www.gnu.org/licenses/lgpl.txt
[31]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[32]: https://maven.apache.org/plugins/maven-compiler-plugin/
[33]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[34]: https://www.mojohaus.org/flatten-maven-plugin/
[35]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[36]: http://www.apache.org/licenses/LICENSE-2.0.txt
[37]: https://maven.apache.org/surefire/maven-surefire-plugin/
[38]: https://www.mojohaus.org/versions/versions-maven-plugin/
[39]: https://basepom.github.io/duplicate-finder-maven-plugin
[40]: http://www.apache.org/licenses/LICENSE-2.0.html
[41]: https://maven.apache.org/plugins/maven-deploy-plugin/
[42]: https://maven.apache.org/plugins/maven-gpg-plugin/
[43]: https://maven.apache.org/plugins/maven-source-plugin/
[44]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[45]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[46]: http://www.eclipse.org/legal/epl-v10.html
[47]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[48]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[49]: https://www.eclipse.org/legal/epl-2.0/
[50]: https://github.com/exasol/quality-summarizer-maven-plugin/
[51]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[52]: https://github.com/exasol/error-code-crawler-maven-plugin/
[53]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[54]: http://zlika.github.io/reproducible-build-maven-plugin
[55]: https://www.mojohaus.org/exec-maven-plugin
[56]: https://registry.npmjs.org/@aws-sdk/client-lambda/-/client-lambda-3.552.0.tgz
[57]: https://github.com/aws/aws-sdk-js-v3
[58]: https://registry.npmjs.org/@aws-sdk/client-s3/-/client-s3-3.552.0.tgz
