package com.formento.kdtree.service;

import com.formento.kdtree.model.Point;
import com.formento.kdtree.model.Rectangle;
import java.util.Collection;

public interface PointService {

    void addPoint(final Point point);

    Collection<Point> findPointsInsideRectangle(final Rectangle rectangle);

}
