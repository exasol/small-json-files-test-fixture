package com.exasol.smalljsonfilesfixture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class TestSetupDescriptionTest {

    @Test
    void testSerialization() {
        final TestSetupDescription setupDescription = new TestSetupDescription(10, "abc123");
        final TestSetupDescription deserialized = TestSetupDescription.fromJson(setupDescription.toJson());
        assertThat(deserialized, equalTo(setupDescription));
    }

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(TestSetupDescription.class).verify();
    }
}