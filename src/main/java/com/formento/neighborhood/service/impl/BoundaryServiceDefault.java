package com.formento.neighborhood.service.impl;

import com.formento.neighborhood.model.Boundary;
import com.formento.neighborhood.model.Point;
import com.formento.neighborhood.repository.BoundaryRepository;
import com.formento.neighborhood.service.BoundaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

@Service
public class BoundaryServiceDefault implements BoundaryService {

    private final BoundaryRepository boundaryRepository;

    @Autowired
    public BoundaryServiceDefault(final BoundaryRepository boundaryRepository) {
        this.boundaryRepository = boundaryRepository;
    }

    @Override
    public Collection<Boundary> findByPoint(final Point point) {
        return boundaryRepository.
                findAll().
                stream().
                sorted().
                filter(boundary -> boundary.containsPoint(point)).
                collect(toList());
    }

}
