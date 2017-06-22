package com.formento.neighborhood.exception;

public class NeighborhoodValidationException extends NeighborhoodException {

    public NeighborhoodValidationException(String message) {
        super(message);
    }

    public NeighborhoodValidationException(String message, Throwable cause) {
        super(message, cause);
    }

}
