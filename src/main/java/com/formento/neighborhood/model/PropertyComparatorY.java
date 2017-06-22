package com.formento.neighborhood.model;

public class PropertyComparatorY implements PropertyComparator {

    private static final PropertyComparator instance = new PropertyComparatorY();

    public static PropertyComparator getInstance() {
        return instance;
    }

    private PropertyComparatorY() {
    }

    @Override
    public int compare(final Property left, final Property right) {
        return compare(left.getPoint(), right.getPoint());
    }

    @Override
    public int compare(final Point left, final Point right) {
        return left.compareToY(right);
    }

    @Override
    public PropertyComparator getNextPropertyComparator() {
        return PropertyComparatorX.getInstance();
    }

}
