package com.exasol.smalljsonfilesfixture;

import java.io.*;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import software.amazon.awssdk.auth.credentials.*;

public class TestConfig {
    private static final TestConfig CONFIG = new Reader().readTestConfig();
    private String awsProfile;

    private String owner;

    public static TestConfig instance() {
        return CONFIG;
    }

    public AwsCredentialsProvider getAwsCredentialsProvider() {
        if (this.awsProfile != null && !this.awsProfile.isBlank()) {
            return ProfileCredentialsProvider.create(this.getAwsProfile());
        } else {
            return DefaultCredentialsProvider.builder().build();
        }
    }

    /**
     * The AWS profile used for connecting to AWS.
     * 
     * @return the AWS profile
     */
    public String getAwsProfile() {
        return awsProfile;
    }

    /**
     * E-mail address of the contact-person for this project. Will be used in exa:owner tag for AWS resources.
     * 
     * @return E-mail address of the contact-person
     */
    public String getOwner() {
        return owner;
    }

    private static class Reader {
        public TestConfig readTestConfig() {
            final Yaml yaml = new Yaml(new Constructor(TestConfig.class));
            try (final FileReader fileReader = new FileReader("test_config.yml")) {
                return yaml.load(fileReader);
            } catch (final FileNotFoundException exception) {
                throw new IllegalArgumentException(
                        "Could not find test_config.yml. Please create that file in the root of this project.");
            } catch (final IOException exception) {
                throw new UncheckedIOException("Failed to load test_config.yml", exception);
            }
        }
    }
}
