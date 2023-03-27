package com.exasol.smalljsonfilesfixture;

/**
 * This class does the book keeping for dividing an integer number of items into a number of packages.
 * <ul>
 * <li>Each package with identical predefined maximum capacity of items called "package size".
 * <li>Each of the packages containing the same number of items, with max. 1 exception
 * <li>Total number of items summarized for all packages identical to initial number of items
 * </ul>
 */
public class Packager {

    private final int itemsTotal;
    private final int packageSize;
    private int itemsDelivered = 0;
    private int packagesDelivered = 0;

    public Packager(final int items, final int packageSize) {
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

    /**
     * @return whether there are more packages waiting to be delivered.
     */
    public boolean hasNext() {
        return this.itemsDelivered < this.itemsTotal;
    }

    /**
     * @return next {@link Package}.
     * @throws IllegalStateException if there are no more packages waiting to be delivered.
     */
    public Package next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more packages.");
        }
        final int size = Math.min(remaining(), this.packageSize);
        final Package packge = new Package(this.packagesDelivered, size);
        this.packagesDelivered++;
        this.itemsDelivered += size;
        return packge;
    }

    /**
     * Reset packages in order to deliver again.
     *
     * @return this for fluent programming
     */
    public Packager reset() {
        this.packagesDelivered = 0;
        this.itemsDelivered = 0;
        return this;
    }

    private int remaining() {
        return this.itemsTotal - this.itemsDelivered;
    }

    /**
     * Each instance of this class represents a single package delivered by {@link Packager}. Each package has a running
     * number, starting at 0, and a size. Total size of all packages is identical to the total number of items specified
     * initially to the constructor of {@link Packager}.
     */
    public static class Package {
        private final int number;
        private final int size;

        Package(final int number, final int size) {
            this.number = number;
            this.size = size;
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
    }
}
