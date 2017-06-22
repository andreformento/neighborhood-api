package com.formento.neighborhood.infra;

public class NeighborhoodValidationException extends NeighborhoodException {

    public NeighborhoodValidationException(String message) {
        super(message);
    }

    public NeighborhoodValidationException(String message, Throwable cause) {
        super(message, cause);
    }

}
