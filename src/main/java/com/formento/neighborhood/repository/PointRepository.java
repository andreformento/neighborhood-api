package com.formento.neighborhood.repository;

import com.formento.neighborhood.model.Point;

import java.util.Collection;

public interface PointRepository {

    Collection<Point> getAll();

}
