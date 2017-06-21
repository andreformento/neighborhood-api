package com.formento.neighborhood.model;

import java.util.Objects;

public class Rectangle implements Comparable<Rectangle> {

    private final Point leftTop;
    private final Point rightBottom;

    public Rectangle(Point leftTop, Point rightBottom) {
        this.leftTop = leftTop;
        this.rightBottom = rightBottom;
    }

    public Rectangle(final Integer leftTopX, final Integer leftTopY, final Integer rightBottomX, final Integer rightBottomY) {
        this(new Point(leftTopX, leftTopY), new Point(rightBottomX, rightBottomY));
    }

    public Point getLeftTop() {
        return leftTop;
    }

    public Point getRightBottom() {
        return rightBottom;
    }

    public Boolean containsPoint(final Point point) {
        return leftTop.lessThanOrEqualTo(point) && rightBottom.greaterThanOrEqualTo(point);
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
        return Objects.equals(leftTop, rectangle.leftTop) &&
            Objects.equals(rightBottom, rectangle.rightBottom);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(leftTop, rightBottom);
    }

    @Override
    public String toString() {
        return "Rectangle(" +
            "leftTop=" + getLeftTop() +
            ", rightBottom=" + getRightBottom() +
            ')';
    }

    @Override
    public int compareTo(Rectangle o) {
        final int compare = leftTop.compareTo(o.leftTop);
        if (compare == 0) {
            return rightBottom.compareTo(o.rightBottom);
        }
        return compare;
    }

}
