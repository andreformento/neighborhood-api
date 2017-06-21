package com.formento.neighborhood.repository.impl;

import com.formento.neighborhood.model.Boundary;
import com.formento.neighborhood.repository.BoundaryRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

@Repository
public class BoundaryRepositoryDefault implements BoundaryRepository {

    private final Collection<Boundary> boundaries;

    public BoundaryRepositoryDefault() {
        this.boundaries = new ArrayList<>();
    }

    @Override
    public Collection<Boundary> getAll() {
        return boundaries;
    }
}
