package com.formento.neighborhood.repository;

import com.formento.neighborhood.model.Property;

import java.util.Collection;
import java.util.Optional;

public interface PropertyRepository {

    Iterable<Property> findAll();

    Long count();

    Optional<Property> findOne(Long id);

    Property insert(Property entity);

    Collection<Property> insert(Collection<Property> properties);

}
