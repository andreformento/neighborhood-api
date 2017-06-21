package com.formento.neighborhood.model;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.Objects;

public class Boundary implements Serializable, Comparable<Boundary> {

    private final Point upperLeft;
    private final Point rightBottom;

    @ConstructorProperties({"upperLeft", "rightBottom"})
    public Boundary(Point upperLeft, Point rightBottom) {
        this.upperLeft = upperLeft;
        this.rightBottom = rightBottom;
    }

    public Boundary(final Integer upperLeftX, final Integer upperLeftY, final Integer rightBottomX, final Integer rightBottomY) {
        this(new Point(upperLeftX, upperLeftY), new Point(rightBottomX, rightBottomY));
    }

    public Point getUpperLeft() {
        return upperLeft;
    }

    public Point getRightBottom() {
        return rightBottom;
    }

    public Boolean containsPoint(final Point point) {
        return upperLeft.lessThanOrEqualTo(point) && rightBottom.greaterThanOrEqualTo(point);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Boundary)) {
            return false;
        }
        Boundary boundary = (Boundary) o;
        return Objects.equals(upperLeft, boundary.upperLeft) &&
            Objects.equals(rightBottom, boundary.rightBottom);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(upperLeft, rightBottom);
    }

    @Override
    public String toString() {
        return "Boundary(" +
            "upperLeft=" + getUpperLeft() +
            ", rightBottom=" + getRightBottom() +
            ')';
    }

    @Override
    public int compareTo(Boundary o) {
        final int compare = upperLeft.compareTo(o.upperLeft);
        if (compare == 0) {
            return rightBottom.compareTo(o.rightBottom);
        }
        return compare;
    }

}
