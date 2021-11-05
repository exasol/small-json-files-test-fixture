package com.exasol.smalljsonfilesfixture;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lombok.*;

/**
 * This class describes a test setup.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestSetupDescription {
    private int numberOfFiles;
    /**
     * The hash of the lambda function ensures the correct content in the files since the lambda function is the
     * generator.
     */
    private String hashOfLambdaFunction;

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
}
