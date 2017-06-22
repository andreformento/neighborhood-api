package com.formento.neighborhood.infra;

import java.io.Serializable;
import java.util.Map;

class ValidationResult implements Serializable {

    private final Map<String, String> errors;

    ValidationResult(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

}
