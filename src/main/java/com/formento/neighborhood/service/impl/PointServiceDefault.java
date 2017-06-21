package com.formento.neighborhood.service.impl;

import com.formento.neighborhood.component.NodeFactory;
import com.formento.neighborhood.model.Node;
import com.formento.neighborhood.model.Point;
import com.formento.neighborhood.model.Boundary;
import com.formento.neighborhood.repository.PointRepository;
import com.formento.neighborhood.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PointServiceDefault implements PointService {

    private final Node root;

    @Autowired
    public PointServiceDefault(final NodeFactory nodeFactory, final PointRepository pointRepository) {
        this.root = nodeFactory.createRoot(pointRepository.getAll());
    }

    @Override
    public void addPoint(final Point point) {
        root.add(point);
    }

    @Override
    public Collection<Point> findPointsInsideBoundary(Boundary boundary) {
        return root.findPointsInsideBoundary(boundary);
    }

}
