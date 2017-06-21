package com.formento.neighborhood.model;

public class PropertyComparatorX implements PropertyComparator {

    private static final PropertyComparator instance = new PropertyComparatorX();

    public static PropertyComparator getInstance() {
        return instance;
    }

    private PropertyComparatorX() {
    }

    @Override
    public int compare(final Point left, final Point right) {
        return left.compareToX(right);
    }

    @Override
    public PropertyComparator getNextPropertyComparator() {
        return PropertyComparatorY.getInstance();
    }

}
