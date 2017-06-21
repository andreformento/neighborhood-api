package com.formento.neighborhood.repository.impl;

import com.formento.neighborhood.model.Point;
import com.formento.neighborhood.repository.PointRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class PointRepositoryDefault implements PointRepository {

    @Override
    public Collection<Point> getAll() {
        return ImmutableList.<Point>builder().add(new Point(1, 2)).build();
    }

}
