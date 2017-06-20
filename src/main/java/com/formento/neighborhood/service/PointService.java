package com.formento.neighborhood.service;

import com.formento.neighborhood.model.Point;
import com.formento.neighborhood.model.Rectangle;
import java.util.Collection;

public interface PointService {

    void addPoint(final Point point);

    Collection<Point> findPointsInsideRectangle(final Rectangle rectangle);

}
