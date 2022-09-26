package com.exasol.smalljsonfilesfixture;

import java.io.*;
import java.nio.file.*;
import java.util.Properties;

import software.amazon.awssdk.auth.credentials.*;

public class TestConfig {
    private static final TestConfig CONFIG = new Reader().readTestConfig();
    private final String awsProfile;
    private final String owner;

    public static TestConfig instance() {
        return CONFIG;
    }

    private TestConfig(final String awsProfile, final String owner) {
        this.awsProfile = awsProfile;
        this.owner = owner;
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
            return readTestConfig(Paths.get("test_config.properties"));
        }

        private TestConfig readTestConfig(final Path path) {
            final Properties properties = loadProperties(path);
            return new TestConfig(properties.getProperty("awsProfile"), properties.getProperty("owner"));
        }

        private Properties loadProperties(final Path path) {
            final Properties properties = new Properties();
            try (InputStream stream = Files.newInputStream(path)) {
                properties.load(stream);
            } catch (final NoSuchFileException exception) {
                throw new IllegalArgumentException(
                        "Could not find " + path + ". Please create that file in the root of this project.");
            } catch (final IOException exception) {
                throw new UncheckedIOException("Failed to load " + path, exception);
            }
            return properties;
        }
    }
}
