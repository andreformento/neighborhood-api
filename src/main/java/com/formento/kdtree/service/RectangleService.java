package com.formento.kdtree.service;

import com.formento.kdtree.model.Point;
import com.formento.kdtree.model.Rectangle;
import java.util.Collection;

public interface RectangleService {

    Collection<Rectangle> findRectanglesByPoint(final Point point);

}
