package com.formento.neighborhood.model;

import java.util.Objects;

public class Rectangle implements Comparable<Rectangle> {

    private final Point upperLeft;
    private final Point rightBottom;

    public Rectangle(Point upperLeft, Point rightBottom) {
        this.upperLeft = upperLeft;
        this.rightBottom = rightBottom;
    }

    public Rectangle(final Integer upperLeftX, final Integer upperLeftY, final Integer rightBottomX, final Integer rightBottomY) {
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
        if (!(o instanceof Rectangle)) {
            return false;
        }
        Rectangle rectangle = (Rectangle) o;
        return Objects.equals(upperLeft, rectangle.upperLeft) &&
            Objects.equals(rightBottom, rectangle.rightBottom);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(upperLeft, rightBottom);
    }

    @Override
    public String toString() {
        return "Rectangle(" +
            "upperLeft=" + getUpperLeft() +
            ", rightBottom=" + getRightBottom() +
            ')';
    }

    @Override
    public int compareTo(Rectangle o) {
        final int compare = upperLeft.compareTo(o.upperLeft);
        if (compare == 0) {
            return rightBottom.compareTo(o.rightBottom);
        }
        return compare;
    }

}
