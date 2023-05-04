<!-- @formatter:off -->
# Dependencies

## Small Json Files Test Fixture

### Compile Dependencies

| Dependency                                                  | License                                                                                                      |
| ----------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------ |
| [AWS Java SDK :: Services :: Amazon S3][0]                  | [Apache License, Version 2.0][1]                                                                             |
| [error-reporting-java][2]                                   | [MIT License][3]                                                                                             |
| [AWS Java SDK :: Services :: AWS Lambda][0]                 | [Apache License, Version 2.0][1]                                                                             |
| [AWS Java SDK :: Services :: AWS IAM][0]                    | [Apache License, Version 2.0][1]                                                                             |
| [AWS Java SDK :: Services :: AWS STS][0]                    | [Apache License, Version 2.0][1]                                                                             |
| [AWS Java SDK :: HTTP Clients :: Netty Non-Blocking I/O][4] | [Apache License, Version 2.0][1]                                                                             |
| [Jakarta JSON Processing API][5]                            | [Eclipse Public License 2.0][6]; [GNU General Public License, version 2 with the GNU Classpath Exception][7] |
| [JSON-B API][8]                                             | [Eclipse Public License 2.0][6]; [GNU General Public License, version 2 with the GNU Classpath Exception][7] |
| [mockito-core][9]                                           | [The MIT License][10]                                                                                        |

### Test Dependencies

| Dependency                                | License                           |
| ----------------------------------------- | --------------------------------- |
| [Hamcrest][11]                            | [BSD License 3][12]               |
| [JUnit Jupiter Params][13]                | [Eclipse Public License v2.0][14] |
| [EqualsVerifier | release normal jar][15] | [Apache License, Version 2.0][16] |
| [SLF4J JDK14 Binding][17]                 | [MIT License][18]                 |

### Runtime Dependencies

| Dependency   | License                                                                        |
| ------------ | ------------------------------------------------------------------------------ |
| [Yasson][19] | [Eclipse Public License v. 2.0][20]; [Eclipse Distribution License v. 1.0][21] |

### Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][22]                       | [GNU LGPL 3][23]                               |
| [Apache Maven Compiler Plugin][24]                      | [Apache-2.0][16]                               |
| [Apache Maven Enforcer Plugin][25]                      | [Apache-2.0][16]                               |
| [Maven Flatten Plugin][26]                              | [Apache Software Licenese][16]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][27] | [ASL2][28]                                     |
| [Maven Surefire Plugin][29]                             | [Apache-2.0][16]                               |
| [Versions Maven Plugin][30]                             | [Apache License, Version 2.0][16]              |
| [duplicate-finder-maven-plugin Maven Mojo][31]          | [Apache License 2.0][32]                       |
| [Apache Maven Deploy Plugin][33]                        | [Apache-2.0][16]                               |
| [Apache Maven GPG Plugin][34]                           | [Apache License, Version 2.0][16]              |
| [Apache Maven Source Plugin][35]                        | [Apache License, Version 2.0][16]              |
| [Apache Maven Javadoc Plugin][36]                       | [Apache-2.0][16]                               |
| [Nexus Staging Maven Plugin][37]                        | [Eclipse Public License][38]                   |
| [Maven Failsafe Plugin][39]                             | [Apache-2.0][16]                               |
| [JaCoCo :: Maven Plugin][40]                            | [Eclipse Public License 2.0][41]               |
| [error-code-crawler-maven-plugin][42]                   | [MIT License][43]                              |
| [Reproducible Build Maven Plugin][44]                   | [Apache 2.0][28]                               |
| [Project keeper maven plugin][45]                       | [The MIT License][46]                          |
| [Maven Clean Plugin][47]                                | [The Apache Software License, Version 2.0][28] |
| [Maven Resources Plugin][48]                            | [The Apache Software License, Version 2.0][28] |
| [Maven JAR Plugin][49]                                  | [The Apache Software License, Version 2.0][28] |
| [Maven Install Plugin][50]                              | [The Apache Software License, Version 2.0][28] |
| [Maven Site Plugin 3][51]                               | [The Apache Software License, Version 2.0][28] |

## Createjsonfileslambda

### Compile Dependencies

| Dependency                   | License          |
| ---------------------------- | ---------------- |
| [@aws-sdk/client-lambda][52] | [Apache-2.0][53] |
| [@aws-sdk/client-s3][54]     | [Apache-2.0][53] |

[0]: https://aws.amazon.com/sdkforjava
[1]: https://aws.amazon.com/apache2.0
[2]: https://github.com/exasol/error-reporting-java/
[3]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[4]: https://sdk.amazonaws.com/java/api/latest/software/amazon/awssdk/http/nio/netty/NettyNioAsyncHttpClient.html
[5]: https://github.com/eclipse-ee4j/jsonp
[6]: https://projects.eclipse.org/license/epl-2.0
[7]: https://projects.eclipse.org/license/secondary-gpl-2.0-cp
[8]: https://github.com/eclipse-ee4j/jsonb-api
[9]: https://github.com/mockito/mockito
[10]: https://github.com/mockito/mockito/blob/main/LICENSE
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
[22]: http://sonarsource.github.io/sonar-scanner-maven/
[23]: http://www.gnu.org/licenses/lgpl.txt
[24]: https://maven.apache.org/plugins/maven-compiler-plugin/
[25]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[26]: https://www.mojohaus.org/flatten-maven-plugin/
[27]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[28]: http://www.apache.org/licenses/LICENSE-2.0.txt
[29]: https://maven.apache.org/surefire/maven-surefire-plugin/
[30]: https://www.mojohaus.org/versions/versions-maven-plugin/
[31]: https://github.com/basepom/duplicate-finder-maven-plugin
[32]: http://www.apache.org/licenses/LICENSE-2.0.html
[33]: https://maven.apache.org/plugins/maven-deploy-plugin/
[34]: https://maven.apache.org/plugins/maven-gpg-plugin/
[35]: https://maven.apache.org/plugins/maven-source-plugin/
[36]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[37]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[38]: http://www.eclipse.org/legal/epl-v10.html
[39]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[40]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[41]: https://www.eclipse.org/legal/epl-2.0/
[42]: https://github.com/exasol/error-code-crawler-maven-plugin/
[43]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[44]: http://zlika.github.io/reproducible-build-maven-plugin
[45]: https://github.com/exasol/project-keeper/
[46]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[47]: http://maven.apache.org/plugins/maven-clean-plugin/
[48]: http://maven.apache.org/plugins/maven-resources-plugin/
[49]: http://maven.apache.org/plugins/maven-jar-plugin/
[50]: http://maven.apache.org/plugins/maven-install-plugin/
[51]: http://maven.apache.org/plugins/maven-site-plugin/
[52]: https://registry.npmjs.org/@aws-sdk/client-lambda/-/client-lambda-3.326.0.tgz
[53]: https://github.com/aws/aws-sdk-js-v3
[54]: https://registry.npmjs.org/@aws-sdk/client-s3/-/client-s3-3.326.0.tgz
