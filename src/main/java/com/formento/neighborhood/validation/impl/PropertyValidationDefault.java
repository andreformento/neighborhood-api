package com.formento.neighborhood.validation.impl;

import com.formento.neighborhood.infra.NeighborhoodValidationException;
import com.formento.neighborhood.model.Property;
import com.formento.neighborhood.validation.PropertyValidation;
import org.springframework.stereotype.Component;

@Component
class PropertyValidationDefault implements PropertyValidation {

    @Override
    public void validateBeforeInsert(final Property property) {
        if (property.getProvinces() == null) {
            throw new NeighborhoodValidationException("Provinces of property cannot be null: " + property);
        }
        if (property.getProvinces().isEmpty()) {
            throw new NeighborhoodValidationException("Provinces of property cannot be empty: " + property);
        }
    }
}
