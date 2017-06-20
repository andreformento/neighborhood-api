package com.formento.neighborhood.component.impl;

import com.formento.neighborhood.component.NodeFactory;
import com.formento.neighborhood.infra.KdtreeDuplicationPointException;
import com.formento.neighborhood.model.Node;
import com.formento.neighborhood.model.Point;
import com.formento.neighborhood.model.PointComparator;
import com.formento.neighborhood.model.PointComparatorX;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class NodeFactoryDefault implements NodeFactory {

    @Override
    public Node createRoot(final Collection<Point> points) {
        if (points.isEmpty()) {
            throw new KdtreeDuplicationPointException("List of points cannot be empty");
        }

        return createRoot(points, PointComparatorX.getInstance());
    }

    private Node createRoot(final Collection<Point> points, final PointComparator pointComparator) {
        final List<Point> sortedPoints = ImmutableList.sortedCopyOf(pointComparator, points);
        final Integer middle = sortedPoints.size() / 2;

        final PointComparator nextPointComparator = pointComparator.getNextPointComparator();
        final Optional<Node> left = getLeftNode(sortedPoints, nextPointComparator, middle);
        final Optional<Node> right = getRightNode(sortedPoints, nextPointComparator, middle);

        final Point value = sortedPoints.get(middle);

        return new Node(value, left, right, pointComparator);
    }

    private Optional<Node> getLeftNode(final List<Point> allPoints, final PointComparator nextPointComparator, final Integer middle) {
        if (middle >= 1) {
            final int fromIndex = 0;
            return Optional.of(createRoot(allPoints.subList(fromIndex, middle), nextPointComparator));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Node> getRightNode(final List<Point> allPoints, final PointComparator nextPointComparator, final Integer middle) {
        final int fromIndex = middle + 1;
        final int toIndex = allPoints.size();
        if (fromIndex < toIndex) {
            return Optional.of(createRoot(allPoints.subList(fromIndex, toIndex), nextPointComparator));
        } else {
            return Optional.empty();
        }
    }

}
