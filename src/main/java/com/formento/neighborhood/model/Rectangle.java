package com.formento.neighborhood.model;

import java.util.Objects;

public class Rectangle {

    private final Point leftBottom;
    private final Point rightTop;

    public Rectangle(Point leftBottom, Point rightTop) {
        this.leftBottom = leftBottom;
        this.rightTop = rightTop;
    }

    public Rectangle(final Integer leftBottomX, final Integer leftBottomY, final Integer rightTopX, final Integer rightTopY) {
        this(new Point(leftBottomX, leftBottomY), new Point(rightTopX, rightTopY));
    }

    public Point getLeftBottom() {
        return leftBottom;
    }

    public Point getRightTop() {
        return rightTop;
    }

    public Boolean containsPoint(final Point point) {
        return leftBottom.lessThanOrEqualTo(point) && rightTop.greaterThanOrEqualTo(point);
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
        return Objects.equals(leftBottom, rectangle.leftBottom) &&
            Objects.equals(rightTop, rectangle.rightTop);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(leftBottom, rightTop);
    }

    @Override
    public String toString() {
        return "Rectangle(" +
            "leftBottom=" + getLeftBottom() +
            ", rightTop=" + getRightTop() +
            ')';
    }
}
