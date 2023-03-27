package com.exasol.smalljsonfilesfixture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.exasol.smalljsonfilesfixture.Packager.Package;

class PackagesTest {

    @ParameterizedTest
    @CsvSource(value = { // "1,1,1", //
            "2,1,2", //
            "2,2,1", //
            "3,2,2", //
    })
    void test(final int items, final int packageSize, final int expectedNumberOfPackages) {
        final Packager testee = new Packager(items, packageSize);
        assertThat(testee.getNumberOfPackages(), equalTo(expectedNumberOfPackages));
        for (int i = 0; i < expectedNumberOfPackages; i++) {
            assertThat(testee.hasNext(), is(true));
            final Package p = testee.next();
            assertThat(i, equalTo(p.getNumber()));
            if (i < (expectedNumberOfPackages - 1)) {
                assertThat(p.getSize(), equalTo(packageSize));
            } else {
                assertThat((i * packageSize) + p.getSize(), equalTo(items));
            }
        }
        assertThat(testee.hasNext(), is(false));
    }

    @Test
    void testReset() {
        final Packager testee = new Packager(1, 1);
        testee.next();
        assertThat(testee.hasNext(), is(false));
        assertThrows(IllegalStateException.class, () -> testee.next());
        testee.reset();
        assertThat(testee.hasNext(), is(true));
        final Package p = testee.next();
        assertThat(p.getSize(), equalTo(1));
    }
}
