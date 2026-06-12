<!-- @formatter:off -->
# Dependencies

## Small Json Files Test Fixture

### Compile Dependencies

| Dependency                                  | License                                                                                                      |
| ------------------------------------------- | ------------------------------------------------------------------------------------------------------------ |
| [AWS Java SDK :: Services :: Amazon S3][0]  | [Apache License, Version 2.0][1]                                                                             |
| [AWS Java SDK :: Services :: Signin][0]     | [Apache License, Version 2.0][1]                                                                             |
| [error-reporting-java][2]                   | [MIT License][3]                                                                                             |
| [AWS Java SDK :: Services :: AWS Lambda][0] | [Apache License, Version 2.0][1]                                                                             |
| [AWS Java SDK :: Services :: AWS IAM][0]    | [Apache License, Version 2.0][1]                                                                             |
| [AWS Java SDK :: Services :: AWS STS][0]    | [Apache License, Version 2.0][1]                                                                             |
| [AWS Java SDK :: HTTP Clients :: Apache][4] | [Apache License, Version 2.0][1]                                                                             |
| [Jakarta JSON Processing API][5]            | [Eclipse Public License 2.0][6]; [GNU General Public License, version 2 with the GNU Classpath Exception][7] |
| [Jakarta JSON Binding API][8]               | [Eclipse Public License 2.0][6]; [GNU General Public License, version 2 with the GNU Classpath Exception][7] |
| [mockito-core][9]                           | [MIT][10]                                                                                                    |

### Test Dependencies

| Dependency                                 | License                           |
| ------------------------------------------ | --------------------------------- |
| [Hamcrest][11]                             | [BSD-3-Clause][12]                |
| [JUnit Jupiter Params][13]                 | [Eclipse Public License v2.0][14] |
| [EqualsVerifier \| release normal jar][15] | [Apache License, Version 2.0][16] |
| [SLF4J JDK14 Provider][17]                 | [MIT][18]                         |

### Runtime Dependencies

| Dependency   | License                                                                        |
| ------------ | ------------------------------------------------------------------------------ |
| [Yasson][19] | [Eclipse Public License v. 2.0][20]; [Eclipse Distribution License v. 1.0][21] |

### Plugin Dependencies

| Dependency                                              | License                                     |
| ------------------------------------------------------- | ------------------------------------------- |
| [SonarQube Scanner for Maven][22]                       | [GNU LGPL 3][23]                            |
| [Apache Maven Toolchains Plugin][24]                    | [Apache-2.0][16]                            |
| [Apache Maven Compiler Plugin][25]                      | [Apache-2.0][16]                            |
| [Apache Maven Enforcer Plugin][26]                      | [Apache-2.0][16]                            |
| [Maven Flatten Plugin][27]                              | [Apache Software License][16]               |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][28] | [ASL2][29]                                  |
| [Maven Surefire Plugin][30]                             | [Apache-2.0][16]                            |
| [Versions Maven Plugin][31]                             | [Apache License, Version 2.0][16]           |
| [duplicate-finder-maven-plugin Maven Mojo][32]          | [Apache License 2.0][33]                    |
| [Apache Maven Artifact Plugin][34]                      | [Apache-2.0][16]                            |
| [Apache Maven Deploy Plugin][35]                        | [Apache-2.0][16]                            |
| [Apache Maven GPG Plugin][36]                           | [Apache-2.0][16]                            |
| [Apache Maven Source Plugin][37]                        | [Apache-2.0][16]                            |
| [Apache Maven Javadoc Plugin][38]                       | [Apache-2.0][16]                            |
| [Central Publishing Maven Plugin][39]                   | [The Apache License, Version 2.0][16]       |
| [Maven Failsafe Plugin][40]                             | [Apache-2.0][16]                            |
| [JaCoCo :: Maven Plugin][41]                            | [EPL-2.0][42]                               |
| [Quality Summarizer Maven Plugin][43]                   | [MIT License][44]                           |
| [error-code-crawler-maven-plugin][45]                   | [MIT License][46]                           |
| [Git Commit Id Maven Plugin][47]                        | [GNU Lesser General Public License 3.0][48] |
| [Project Keeper Maven plugin][49]                       | [The MIT License][50]                       |
| [Apache Maven Clean Plugin][51]                         | [Apache-2.0][16]                            |
| [Exec Maven Plugin][52]                                 | [Apache License 2][16]                      |
| [Apache Maven Resources Plugin][53]                     | [Apache-2.0][16]                            |
| [Apache Maven Install Plugin][54]                       | [Apache-2.0][16]                            |
| [Apache Maven Site Plugin][55]                          | [Apache-2.0][16]                            |

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
[8]: https://projects.eclipse.org/projects/ee4j.jsonb/jakarta.json.bind-api
[9]: https://github.com/mockito/mockito
[10]: https://opensource.org/licenses/MIT
[11]: http://hamcrest.org/JavaHamcrest/
[12]: https://raw.githubusercontent.com/hamcrest/JavaHamcrest/master/LICENSE
[13]: https://junit.org/
[14]: https://www.eclipse.org/legal/epl-v20.html
[15]: https://www.jqno.nl/equalsverifier
[16]: https://www.apache.org/licenses/LICENSE-2.0.txt
[17]: http://www.slf4j.org
[18]: https://opensource.org/license/mit
[19]: https://projects.eclipse.org/projects/ee4j.yasson
[20]: http://www.eclipse.org/legal/epl-v20.html
[21]: http://www.eclipse.org/org/documents/edl-v10.php
[22]: https://docs.sonarsource.com/sonarqube-server/latest/extension-guide/developing-a-plugin/plugin-basics/sonar-scanner-maven/sonar-maven-plugin/
[23]: http://www.gnu.org/licenses/lgpl.txt
[24]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[25]: https://maven.apache.org/plugins/maven-compiler-plugin/
[26]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[27]: https://www.mojohaus.org/flatten-maven-plugin/
[28]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[29]: http://www.apache.org/licenses/LICENSE-2.0.txt
[30]: https://maven.apache.org/surefire/maven-surefire-plugin/
[31]: https://www.mojohaus.org/versions/versions-maven-plugin/
[32]: https://basepom.github.io/duplicate-finder-maven-plugin
[33]: http://www.apache.org/licenses/LICENSE-2.0.html
[34]: https://maven.apache.org/plugins/maven-artifact-plugin/
[35]: https://maven.apache.org/plugins/maven-deploy-plugin/
[36]: https://maven.apache.org/plugins/maven-gpg-plugin/
[37]: https://maven.apache.org/plugins/maven-source-plugin/
[38]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[39]: https://central.sonatype.org
[40]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[41]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[42]: https://www.eclipse.org/legal/epl-2.0/
[43]: https://github.com/exasol/quality-summarizer-maven-plugin/
[44]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[45]: https://github.com/exasol/error-code-crawler-maven-plugin/
[46]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[47]: https://github.com/git-commit-id/git-commit-id-maven-plugin
[48]: http://www.gnu.org/licenses/lgpl-3.0.txt
[49]: https://github.com/exasol/project-keeper/
[50]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[51]: https://maven.apache.org/plugins/maven-clean-plugin/
[52]: https://www.mojohaus.org/exec-maven-plugin
[53]: https://maven.apache.org/plugins/maven-resources-plugin/
[54]: https://maven.apache.org/plugins/maven-install-plugin/
[55]: https://maven.apache.org/plugins/maven-site-plugin/
[56]: https://registry.npmjs.org/@aws-sdk/client-lambda/-/client-lambda-3.1067.0.tgz
[57]: https://github.com/aws/aws-sdk-js-v3
[58]: https://registry.npmjs.org/@aws-sdk/client-s3/-/client-s3-3.1067.0.tgz
