package com.formento.neighborhood.service;

import com.formento.neighborhood.model.Boundary;
import com.formento.neighborhood.model.Property;
import java.util.Collection;

public interface PropertyService {

    Property addProperty(final Property property);

    Property findById(final Long id);

    Collection<Property> findPropertiesInsideBoundary(final Boundary boundary);

}
