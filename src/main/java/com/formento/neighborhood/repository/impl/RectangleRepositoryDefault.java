package com.formento.neighborhood.repository.impl;

import com.formento.neighborhood.model.Rectangle;
import com.formento.neighborhood.repository.RectangleRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

@Repository
public class RectangleRepositoryDefault implements RectangleRepository {

    private final Collection<Rectangle> rectangles;

    public RectangleRepositoryDefault() {
        this.rectangles = new ArrayList<>();
    }

    @Override
    public Collection<Rectangle> getAll() {
        return rectangles;
    }
}
