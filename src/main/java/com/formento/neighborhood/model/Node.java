package com.formento.neighborhood.model;

import com.formento.neighborhood.validation.DuplicatedPointValidator;
import com.google.common.collect.ImmutableList;

import java.util.Collection;
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
        new DuplicatedPointValidator(value).validate(point);

        final Boolean isLeftWay = pointComparator.compare(point, value) < 0;
        final Optional<Node> nextNode = isLeftWay ? left : right;

        if (nextNode.isPresent()) {
            return nextNode.map(n -> n.add(point)).get();
        } else {
            return generatePoint(point, isLeftWay);
        }
    }

    private Node generatePoint(final Point point, final Boolean isLeftWay) {
        final Node node = new Node(point, left, right, pointComparator.getNextPointComparator());

        if (isLeftWay) {
            this.left = Optional.of(node);
        } else {
            this.right = Optional.of(node);
        }

        return node;
    }

    public Collection<Point> findPointsInsideBoundary(final Boundary boundary) {
        final ImmutableList.Builder<Point> builder = ImmutableList.builder();

        findPointsInsideBoundary(boundary, builder, true);

        return builder.build();
    }

    private void findPointsInsideBoundary(final Boundary boundary, final ImmutableList.Builder<Point> builder, final boolean continueIfNotFound) {
        final boolean goToTheUpperLeft;
        final boolean goToTheRightBottom;
        final boolean nextContinueIfNotFound;
        if (boundary.containsPoint(value)) {
            builder.add(value);
            goToTheUpperLeft = true;
            goToTheRightBottom = true;
            nextContinueIfNotFound = false;
        } else {
            nextContinueIfNotFound = continueIfNotFound;
            goToTheUpperLeft = nextContinueIfNotFound && pointComparator.compare(boundary.getUpperLeft(), value) < 0;
            goToTheRightBottom = nextContinueIfNotFound && pointComparator.compare(boundary.getRightBottom(), value) > 0;
        }

        left.
            filter(node -> goToTheUpperLeft).
            ifPresent(node -> node.findPointsInsideBoundary(boundary, builder, nextContinueIfNotFound));
        right.
            filter(node -> goToTheRightBottom).
            ifPresent(node -> node.findPointsInsideBoundary(boundary, builder, nextContinueIfNotFound));
    }

}
