package com.exasol.smalljsonfilesfixture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Iterator;

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
        final Iterator<Package> iterator = testee.iterator();
        for (int i = 0; i < expectedNumberOfPackages; i++) {
            assertThat(iterator.hasNext(), is(true));
            final Package p = iterator.next();
            assertThat(i, equalTo(p.getNumber()));
            if (i < (expectedNumberOfPackages - 1)) {
                assertThat(p.getSize(), equalTo(packageSize));
            } else {
                assertThat((i * packageSize) + p.getSize(), equalTo(items));
            }
        }
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    void testReturnsNewIterator() {
        final Packager testee = new Packager(1, 1);
        final Iterator<Package> iterator1 = testee.iterator();
        final Iterator<Package> iterator2 = testee.iterator();
        assertThat(iterator1, not(sameInstance(iterator2)));
    }
}
