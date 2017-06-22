package com.formento.neighborhood.model;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.Objects;

public class Province implements Serializable, Comparable<Province> {

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

    @Override
    public int compareTo(Province province) {
        return boundary.compareTo(province.boundary);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Province)) {
            return false;
        }
        Province province = (Province) o;
        return Objects.equals(boundary, province.boundary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boundary);
    }
}
