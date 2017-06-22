package com.formento.neighborhood.model;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.NotNull;

public class Boundary implements Serializable, Comparable<Boundary> {

    private final Point upperLeft;
    private final Point bottomRight;

    @ConstructorProperties({"upperLeft", "bottomRight"})
    public Boundary(@NotNull Point upperLeft, @NotNull Point bottomRight) {
        this.upperLeft = upperLeft;
        this.bottomRight = bottomRight;
    }

    public Boundary(final Integer upperLeftX, final Integer upperLeftY, final Integer bottomRightX, final Integer bottomRightY) {
        this(new Point(upperLeftX, upperLeftY), new Point(bottomRightX, bottomRightY));
    }

    public Point getUpperLeft() {
        return upperLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public Boolean containsPoint(final Point point) {
        return upperLeft.lessThanOrEqualTo(point) && bottomRight.greaterThanOrEqualTo(point);
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
            Objects.equals(bottomRight, boundary.bottomRight);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(upperLeft, bottomRight);
    }

    @Override
    public String toString() {
        return "Boundary(" +
            "upperLeft=" + getUpperLeft() +
            ", bottomRight=" + getBottomRight() +
            ')';
    }

    @Override
    public int compareTo(Boundary o) {
        final int compare = upperLeft.compareTo(o.upperLeft);
        if (compare == 0) {
            return bottomRight.compareTo(o.bottomRight);
        }
        return compare;
    }

}
