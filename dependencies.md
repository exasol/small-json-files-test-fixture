<!-- @formatter:off -->
# Dependencies

## Small Json Files Test Fixture

### Compile Dependencies

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

### Test Dependencies

| Dependency                                | License                           |
| ----------------------------------------- | --------------------------------- |
| [Hamcrest][13]                            | [BSD License 3][14]               |
| [JUnit Jupiter Engine][15]                | [Eclipse Public License v2.0][16] |
| [JUnit Jupiter Params][15]                | [Eclipse Public License v2.0][16] |
| [EqualsVerifier | release normal jar][17] | [Apache License, Version 2.0][3]  |
| [SLF4J JDK14 Binding][18]                 | [MIT License][19]                 |

### Runtime Dependencies

| Dependency   | License                                                                        |
| ------------ | ------------------------------------------------------------------------------ |
| [Yasson][20] | [Eclipse Public License v. 2.0][21]; [Eclipse Distribution License v. 1.0][22] |

### Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][23]                       | [GNU LGPL 3][24]                               |
| [Apache Maven Compiler Plugin][25]                      | [Apache-2.0][3]                                |
| [Apache Maven Enforcer Plugin][26]                      | [Apache-2.0][3]                                |
| [Maven Flatten Plugin][27]                              | [Apache Software Licenese][3]                  |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][28] | [ASL2][29]                                     |
| [Maven Surefire Plugin][30]                             | [Apache-2.0][3]                                |
| [Versions Maven Plugin][31]                             | [Apache License, Version 2.0][3]               |
| [duplicate-finder-maven-plugin Maven Mojo][32]          | [Apache License 2.0][33]                       |
| [Apache Maven Deploy Plugin][34]                        | [Apache-2.0][3]                                |
| [Apache Maven GPG Plugin][35]                           | [Apache License, Version 2.0][3]               |
| [Apache Maven Source Plugin][36]                        | [Apache License, Version 2.0][3]               |
| [Apache Maven Javadoc Plugin][37]                       | [Apache-2.0][3]                                |
| [Nexus Staging Maven Plugin][38]                        | [Eclipse Public License][39]                   |
| [Maven Failsafe Plugin][40]                             | [Apache-2.0][3]                                |
| [JaCoCo :: Maven Plugin][41]                            | [Eclipse Public License 2.0][42]               |
| [error-code-crawler-maven-plugin][43]                   | [MIT License][44]                              |
| [Reproducible Build Maven Plugin][45]                   | [Apache 2.0][29]                               |
| [Project keeper maven plugin][46]                       | [The MIT License][47]                          |
| [Maven Clean Plugin][48]                                | [The Apache Software License, Version 2.0][29] |
| [Maven Resources Plugin][49]                            | [The Apache Software License, Version 2.0][29] |
| [Maven JAR Plugin][50]                                  | [The Apache Software License, Version 2.0][29] |
| [Maven Install Plugin][51]                              | [The Apache Software License, Version 2.0][29] |
| [Maven Site Plugin 3][52]                               | [The Apache Software License, Version 2.0][29] |

## Createjsonfileslambda

### Compile Dependencies

| Dependency    | License          |
| ------------- | ---------------- |
| [aws-sdk][53] | [Apache-2.0][54] |

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
[32]: https://github.com/basepom/duplicate-finder-maven-plugin
[33]: http://www.apache.org/licenses/LICENSE-2.0.html
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
[53]: https://registry.npmjs.org/aws-sdk/-/aws-sdk-2.1342.0.tgz
[54]: https://github.com/aws/aws-sdk-js
