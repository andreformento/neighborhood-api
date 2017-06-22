package com.formento.neighborhood.infra;

public class NeighborhoodException extends RuntimeException {

    public NeighborhoodException(String message) {
        super(message);
    }

    public NeighborhoodException(String message, Throwable cause) {
        super(message, cause);
    }

}
