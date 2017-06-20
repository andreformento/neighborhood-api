package com.formento.neighborhood.model;

public class PointComparatorX implements PointComparator {

    private static final PointComparator instance = new PointComparatorX();

    public static PointComparator getInstance() {
        return instance;
    }

    private PointComparatorX() {
    }

    @Override
    public int compare(final Point left, final Point right) {
        return left.compareToX(right);
    }

    @Override
    public PointComparator getNextPointComparator() {
        return PointComparatorY.getInstance();
    }

}
