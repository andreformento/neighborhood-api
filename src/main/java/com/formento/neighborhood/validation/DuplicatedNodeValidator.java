package com.formento.neighborhood.validation;

import com.formento.neighborhood.model.Point;

public class DuplicatedNodeValidator {
    private final Point value;

    public DuplicatedNodeValidator(final Point value) {
        this.value = value;
    }

    public void validate(final Point point) {
        if (value.equals(point)) {
            throw new KdtreeDuplicationPointException("It is not possible insert duplicate points: " + value.toString());
        }
    }
}
