package com.formento.neighborhood.model;

import java.beans.ConstructorProperties;
import java.io.Serializable;

public class Province implements Serializable {

    private final String description;
    private final Boundary boundary;

    @ConstructorProperties({"description", "boundary"})
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
