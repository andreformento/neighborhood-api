package com.formento.kdtree.service.impl;

import com.formento.kdtree.model.Node;
import com.formento.kdtree.model.Point;
import com.formento.kdtree.model.PointComparatorX;
import com.formento.kdtree.model.Rectangle;
import com.formento.kdtree.service.PointService;
import java.util.Collection;

public class PointServiceDefault implements PointService {

    private final Node root;

    public PointServiceDefault(final Node root) {
        this.root = root;
    }

    @Override
    public void addPoint(final Point point) {
        root.add(point);
    }

    @Override
    public Collection<Point> findPointsInsideRectangle(Rectangle rectangle) {
        return root.findPointsInsideRectangle(rectangle);
    }

}
