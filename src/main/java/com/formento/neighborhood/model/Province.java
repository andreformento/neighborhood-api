package com.formento.neighborhood.model;

public class Province {

    private final String description;
    private final Boundary boundary;

    public Province(String description, Boundary boundary) {
        this.description = description;
        this.boundary = boundary;
    }

    public String getDescription() {
        return description;
    }

    public Boundary getBoundary() {
        return boundary;
    }

}
