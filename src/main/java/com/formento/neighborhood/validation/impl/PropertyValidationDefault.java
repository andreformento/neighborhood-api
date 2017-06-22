package com.formento.neighborhood.validation.impl;

import com.formento.neighborhood.model.Property;
import com.formento.neighborhood.validation.PropertyValidation;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class PropertyValidationDefault implements PropertyValidation {

    private final Validator validator;

    @Autowired
    PropertyValidationDefault(Validator validator) {
        this.validator = validator;
    }

    @Override
    public void validateBeforeInsert(final Property property) {
        final Set<ConstraintViolation<Object>> violations = validator.validate(property);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
