package com.exasol.smalljsonfilesfixture;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

/**
 * This class describes a test setup.
 */
public final class TestSetupDescription {
    private int numberOfFiles;

    private String hashOfLambdaFunction;

    /**
     * Create a new {@link TestSetupDescription}.
     * 
     * @param numberOfFiles        the number of files to generate
     * @param hashOfLambdaFunction the hash sum of the lambda function
     */
    public TestSetupDescription(final int numberOfFiles, final String hashOfLambdaFunction) {
        this.numberOfFiles = numberOfFiles;
        this.hashOfLambdaFunction = hashOfLambdaFunction;
    }

    /**
     * Create a new {@link TestSetupDescription}.
     */
    public TestSetupDescription() {
        // Required for deserializing JSON
    }

    /**
     * The hash of the lambda function ensures the correct content in the files since the lambda function is the
     * generator.
     * 
     * @return the hash of the lambda function
     */
    public String getHashOfLambdaFunction() {
        return hashOfLambdaFunction;
    }

    /**
     * Set the hash of the lambda function.
     * 
     * @param hashOfLambdaFunction the hash of the lambda function
     */
    public void setHashOfLambdaFunction(final String hashOfLambdaFunction) {
        this.hashOfLambdaFunction = hashOfLambdaFunction;
    }

    /**
     * Get the number of files to generate.
     * 
     * @return the number of files to generate
     */
    public int getNumberOfFiles() {
        return numberOfFiles;
    }

    /**
     * Set the number of files to generate.
     * 
     * @param numberOfFiles the number of files to generate.
     */
    public void setNumberOfFiles(final int numberOfFiles) {
        this.numberOfFiles = numberOfFiles;
    }

    /**
     * Deserialize the test-setup from JSON.
     * 
     * @param json JSON string
     * @return deserialized {@link TestSetupDescription}
     */
    public static TestSetupDescription fromJson(final String json) {
        try (final Jsonb jsonb = JsonbBuilder.create()) {
            return jsonb.fromJson(json, TestSetupDescription.class);
        } catch (final Exception exception) {
            throw new IllegalStateException("Failed to deserialize " + TestSetupDescription.class.getSimpleName(),
                    exception);
        }
    }

    /**
     * Serialize as JSON.
     * 
     * @return JSON string
     */
    public String toJson() {
        try (final Jsonb jsonb = JsonbBuilder.create()) {
            return jsonb.toJson(this);
        } catch (final Exception exception) {
            throw new IllegalStateException("Failed to serialize " + getClass().getSimpleName(), exception);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((hashOfLambdaFunction == null) ? 0 : hashOfLambdaFunction.hashCode());
        result = prime * result + numberOfFiles;
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final TestSetupDescription other = (TestSetupDescription) obj;
        if (hashOfLambdaFunction == null) {
            if (other.hashOfLambdaFunction != null)
                return false;
        } else if (!hashOfLambdaFunction.equals(other.hashOfLambdaFunction))
            return false;
        if (numberOfFiles != other.numberOfFiles)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TestSetupDescription [hashOfLambdaFunction=" + hashOfLambdaFunction + ", numberOfFiles=" + numberOfFiles
                + "]";
    }
}
