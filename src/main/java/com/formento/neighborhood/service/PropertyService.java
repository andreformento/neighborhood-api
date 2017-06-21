package com.formento.neighborhood.service;

import com.formento.neighborhood.model.Boundary;
import com.formento.neighborhood.model.Property;

import java.util.Collection;

public interface PropertyService {

    void addProperty(final Property property);

    Collection<Property> findPropertiesInsideBoundary(final Boundary boundary);

}
