package com.formento.neighborhood.service;

import com.formento.neighborhood.model.Point;
import com.formento.neighborhood.model.Boundary;
import java.util.Collection;

public interface BoundaryService {

    Collection<Boundary> findByPoint(final Point point);

}
