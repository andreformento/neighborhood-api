package com.formento.neighborhood.service.impl;

import com.formento.neighborhood.model.Point;
import com.formento.neighborhood.model.Rectangle;
import com.formento.neighborhood.repository.RectangleRepository;
import com.formento.neighborhood.service.RectangleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

@Service
public class RectangleServiceDefault implements RectangleService {

    private final RectangleRepository rectangleRepository;

    @Autowired
    public RectangleServiceDefault(final RectangleRepository rectangleRepository) {
        this.rectangleRepository = rectangleRepository;
    }

    @Override
    public Collection<Rectangle> findRectanglesByPoint(final Point point) {
        return rectangleRepository.
                getAll().
                stream().
                filter(rectangle -> rectangle.containsPoint(point)).
                collect(toList());
    }

}
