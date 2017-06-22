package com.formento.neighborhood.exception;

import java.io.Serializable;
import java.util.Map;

class ErrorResult implements Serializable {

    private final Map<String, String> errors;

    ErrorResult(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

}
