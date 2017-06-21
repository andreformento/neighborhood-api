package com.formento.neighborhood.repository.impl;

import com.formento.neighborhood.model.Point;
import com.formento.neighborhood.repository.PointRepository;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import org.springframework.stereotype.Repository;

@Repository
public class PointRepositoryDefault implements PointRepository {

    @Override
    public Collection<Point> getAll() {
        return ImmutableList.<Point>builder().add(new Point(1, 2)).build();
    }

}
