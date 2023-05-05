package com.exasol.smalljsonfilesfixture;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class does the book keeping for dividing an integer number of items into a number of packages.
 * <ul>
 * <li>Each package with identical predefined maximum capacity of items called "package size".
 * <li>Each of the packages containing the same number of items, with max. 1 exception
 * <li>Total number of items summarized for all packages identical to initial number of items
 * </ul>
 */
class Packager implements Iterable<Packager.Package> {

    private final int itemsTotal;
    private final int packageSize;

    /**
     * Create a new instance.
     *
     * @param items       the total number of items
     * @param packageSize the maximum size of each package
     */
    Packager(final int items, final int packageSize) {
        this.itemsTotal = items;
        this.packageSize = packageSize;
    }

    // https://stackoverflow.com/questions/2745074/
    /**
     * @return number of packages required to contain all items without exceeding the defined maximum package size.
     */
    public int getNumberOfPackages() {
        return 1 + ((this.itemsTotal - 1) / this.packageSize);
    }

    @Override
    public Iterator<Packager.Package> iterator() {
        return new PackageIterator();
    }

    private class PackageIterator implements Iterator<Package> {
        private int itemsDelivered = 0;
        private int packagesDelivered = 0;

        @Override
        public boolean hasNext() {
            return this.itemsDelivered < Packager.this.itemsTotal;
        }

        @Override
        public Package next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more packages.");
            }
            final int size = Math.min(remaining(), Packager.this.packageSize);
            final Package pkg = new Package(this.packagesDelivered, size, getNumberOfPackages());
            this.packagesDelivered++;
            this.itemsDelivered += size;
            return pkg;
        }

        private int remaining() {
            return Packager.this.itemsTotal - this.itemsDelivered;
        }
    }

    /**
     * Each instance of this class represents a single package delivered by {@link Packager}. Each package has a running
     * number, starting at 0, and a size. Total size of all packages is identical to the total number of items specified
     * initially to the constructor of {@link Packager}.
     */
    public static class Package {
        private final int number;
        private final int size;
        private final int totalCount;

        Package(final int number, final int size, final int totalCount) {
            this.number = number;
            this.size = size;
            this.totalCount = totalCount;
        }

        /**
         * @return number of the package
         */
        public int getNumber() {
            return this.number;
        }

        /**
         * @return size of the package in terms of items
         */
        public int getSize() {
            return this.size;
        }

        /**
         * @return total number of packages.
         */
        public int getTotalCount() {
            return this.totalCount;
        }
    }
}
