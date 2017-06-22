package com.formento.neighborhood.infra;

public class NeighborhoodNotFoundException extends NeighborhoodException {

    public NeighborhoodNotFoundException(String message) {
        super(message);
    }

    public NeighborhoodNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
