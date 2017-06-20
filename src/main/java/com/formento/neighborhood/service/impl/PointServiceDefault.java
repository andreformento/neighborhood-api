package com.formento.neighborhood.service.impl;

import com.formento.neighborhood.model.Node;
import com.formento.neighborhood.model.Point;
import com.formento.neighborhood.model.Rectangle;
import com.formento.neighborhood.service.PointService;
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
