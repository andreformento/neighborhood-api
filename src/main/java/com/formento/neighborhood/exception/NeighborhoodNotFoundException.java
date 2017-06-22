package com.formento.neighborhood.exception;

public class NeighborhoodNotFoundException extends NeighborhoodException {

    public NeighborhoodNotFoundException(String message) {
        super(message);
    }

    public NeighborhoodNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
