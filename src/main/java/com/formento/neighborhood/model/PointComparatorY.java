package com.formento.neighborhood.model;

public class PointComparatorY implements PointComparator {

    private static final PointComparator instance = new PointComparatorY();

    public static PointComparator getInstance() {
        return instance;
    }

    private PointComparatorY() {
    }

    @Override
    public int compare(final Point left, final Point right) {
        return left.compareToY(right);
    }

    @Override
    public PointComparator getNextPointComparator() {
        return PointComparatorX.getInstance();
    }

}
