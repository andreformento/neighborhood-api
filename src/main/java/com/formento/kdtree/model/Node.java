package com.formento.kdtree.model;

import com.formento.kdtree.infra.KdtreeDuplicationPointException;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public class Node {

    private final Point value;
    private final PointComparator pointComparator;
    private Optional<Node> left;
    private Optional<Node> right;

    public Node(final Point value, final Optional<Node> left, final Optional<Node> right, final PointComparator pointComparator) {
        this.value = value;
        this.pointComparator = pointComparator;
        this.left = left;
        this.right = right;
    }

    public Point getValue() {
        return value;
    }

    public Optional<Node> getLeft() {
        return left;
    }

    public Optional<Node> getRight() {
        return right;
    }

    public PointComparator getPointComparator() {
        return pointComparator;
    }

    public Node add(final Point point) {
        final Boolean isLeftWay = pointComparator.compare(point, value) < 0;
        final Optional<Node> nextNode = isLeftWay ? left : right;

        if (nextNode.isPresent()) {
            return nextNode.map(n -> n.add(point)).get();
        } else {
            return generatePoint(point, isLeftWay);
        }
    }

    private Node generatePoint(final Point point, final Boolean isLeftWay) {
        if (value.equals(point)) {
            throw new KdtreeDuplicationPointException("It is not possible insert duplicate points: " + point.toString());
        }

        final Node node = new Node(point, left, right, pointComparator.getNextPointComparator());

        if (isLeftWay) {
            this.left = Optional.of(node);
        } else {
            this.right = Optional.of(node);
        }

        return node;
    }

    public Collection<Point> findPointsInsideRectangle(final Rectangle rectangle) {
        final ImmutableList.Builder<Point> builder = ImmutableList.builder();

        findPointsInsideRectangle(rectangle, builder, true);

        return builder.build();
    }

    private void findPointsInsideRectangle(final Rectangle rectangle, final ImmutableList.Builder<Point> builder, final boolean continueIfNotFound) {
        final boolean goToTheLeftBottom;
        final boolean goToTheRightTop;
        final boolean nextContinueIfNotFound;
        if (rectangle.containsPoint(value)) {
            builder.add(value);
            goToTheLeftBottom = true;
            goToTheRightTop = true;
            nextContinueIfNotFound = false;
        } else {
            nextContinueIfNotFound = continueIfNotFound;
            goToTheLeftBottom = nextContinueIfNotFound && pointComparator.compare(rectangle.getLeftBottom(), value) < 0;
            goToTheRightTop = nextContinueIfNotFound && pointComparator.compare(rectangle.getRightTop(), value) > 0;
        }

        left.
            filter(node -> goToTheLeftBottom).
            ifPresent(node -> node.findPointsInsideRectangle(rectangle, builder, nextContinueIfNotFound));
        right.
            filter(node -> goToTheRightTop).
            ifPresent(node -> node.findPointsInsideRectangle(rectangle, builder, nextContinueIfNotFound));
    }

}
