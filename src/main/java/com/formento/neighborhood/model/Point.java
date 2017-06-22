package com.formento.neighborhood.model;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.NotNull;

public class Point implements Serializable, Comparable<Point> {

    private final Integer x;
    private final Integer y;

    @ConstructorProperties({"x", "y"})
    public Point(@NotNull Integer x, @NotNull Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Point(x=" + getX() + ", y=" + getY() + ")";
    }

    public Integer compareToX(final Point point) {
        return Integer.compare(this.getX(), point.getX());
    }

    public Integer compareToY(final Point point) {
        return Integer.compare(this.getY(), point.getY());
    }

    public Boolean lessThanOrEqualTo(final Point other) {
        return compareToX(other) <= 0 && compareToY(other) >= 0;
    }

    public Boolean greaterThanOrEqualTo(final Point other) {
        return compareToX(other) >= 0 && compareToY(other) <= 0;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Point)) {
            return false;
        }
        Point point = (Point) o;
        return Objects.equals(x, point.x) &&
            Objects.equals(y, point.y);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public int compareTo(Point other) {
        if (lessThanOrEqualTo(other)) {
            return -1;
        } else if (greaterThanOrEqualTo(other)) {
            return 1;
        } else {
            final int compare = compareToX(other);
            if (compare == 0) {
                return compareToY(other);
            } else {
                return compare;
            }
        }
    }

}
