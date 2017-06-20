package com.formento.neighborhood.service;

import com.formento.neighborhood.model.Point;
import com.formento.neighborhood.model.Rectangle;
import java.util.Collection;

public interface RectangleService {

    Collection<Rectangle> findRectanglesByPoint(final Point point);

}
