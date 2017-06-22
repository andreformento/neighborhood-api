package com.formento.neighborhood.model;

import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.Optional;

public class Node {

    private final Property value;
    private final PropertyComparator propertyComparator;
    private Optional<Node> left;
    private Optional<Node> right;

    public Node(final Property value, final Optional<Node> left, final Optional<Node> right, final PropertyComparator propertyComparator) {
        this.value = value;
        this.propertyComparator = propertyComparator;
        this.left = left;
        this.right = right;
    }

    public Property getValue() {
        return value;
    }

    public Optional<Node> getLeft() {
        return left;
    }

    public Optional<Node> getRight() {
        return right;
    }

    public PropertyComparator getPropertyComparator() {
        return propertyComparator;
    }

    public Node add(final Property point) {
        final Boolean isLeftWay = propertyComparator.compare(point, value) < 0;
        final Optional<Node> nextNode = isLeftWay ? left : right;

        if (nextNode.isPresent()) {
            return nextNode.map(n -> n.add(point)).get();
        } else {
            return generateProperty(point, isLeftWay);
        }
    }

    private Node generateProperty(final Property point, final Boolean isLeftWay) {
        final Node node = new Node(point, left, right, propertyComparator.getNextPropertyComparator());

        if (isLeftWay) {
            this.left = Optional.of(node);
        } else {
            this.right = Optional.of(node);
        }

        return node;
    }

    public Collection<Property> findPropertiesInsideBoundary(final Boundary boundary) {
        final ImmutableList.Builder<Property> builder = ImmutableList.builder();

        findPropertiesInsideBoundary(boundary, builder, true);

        return builder.build();
    }

    private void findPropertiesInsideBoundary(final Boundary boundary, final ImmutableList.Builder<Property> builder,
        final boolean continueIfNotFound) {
        final boolean goToTheUpperLeft;
        final boolean goToTheRightBottom;
        final boolean nextContinueIfNotFound;
        if (boundary.containsPoint(value.getPoint())) {
            builder.add(value);
            goToTheUpperLeft = true;
            goToTheRightBottom = true;
            nextContinueIfNotFound = false;
        } else {
            nextContinueIfNotFound = continueIfNotFound;
            goToTheUpperLeft = nextContinueIfNotFound && propertyComparator.compare(boundary.getUpperLeft(), value.getPoint()) < 0;
            goToTheRightBottom = nextContinueIfNotFound && propertyComparator.compare(boundary.getRightBottom(), value.getPoint()) > 0;
        }

        left.
            filter(node -> goToTheUpperLeft).
            ifPresent(node -> node.findPropertiesInsideBoundary(boundary, builder, nextContinueIfNotFound));
        right.
            filter(node -> goToTheRightBottom).
            ifPresent(node -> node.findPropertiesInsideBoundary(boundary, builder, nextContinueIfNotFound));
    }

}
