package com.formento.neighborhood.validation;

import com.formento.neighborhood.model.Property;

public interface PropertyValidation {

    void validateBeforeInsert(final Property property);

}
