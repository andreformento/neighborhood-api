package com.formento.neighborhood.service.impl;

import static java.util.stream.Collectors.toList;

import com.formento.neighborhood.model.Point;
import com.formento.neighborhood.model.Rectangle;
import com.formento.neighborhood.service.RectangleService;
import java.util.Collection;

public class RectangleServiceDefault implements RectangleService {

    private final Collection<Rectangle> rectangles;

    public RectangleServiceDefault(final Collection<Rectangle> rectangles) {
        this.rectangles = rectangles;
    }

    @Override
    public Collection<Rectangle> findRectanglesByPoint(final Point point) {
        return rectangles.
            stream().
            filter(rectangle -> rectangle.containsPoint(point)).
            collect(toList());
    }

}
