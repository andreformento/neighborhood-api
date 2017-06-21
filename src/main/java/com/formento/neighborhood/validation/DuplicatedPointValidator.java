package com.formento.neighborhood.validation;

import com.formento.neighborhood.model.Point;

public class DuplicatedPointValidator {
    private final Point value;

    public DuplicatedPointValidator(final Point value) {
        this.value = value;
    }

    public void validate(final Point point) {
        if (value.equals(point)) {
            throw new KdtreeDuplicationPointException("It is not possible insert duplicated points: " + value.toString());
        }
    }
}
