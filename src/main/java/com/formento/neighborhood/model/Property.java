package com.formento.neighborhood.model;

import java.io.Serializable;

public class Property implements Serializable {

    private final Long id;
    private final String title;
    private final Integer price;
    private final String description;
    private final Point point;
    private final Short beds;
    private final Short baths;
    private final Integer squareMeters;

    //@ConstructorProperties({"id", "title", "price"})
    public Property(Long id, String title, Integer price, String description, Point point, Short beds, Short baths, Integer squareMeters) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.point = point;
        this.beds = beds;
        this.baths = baths;
        this.squareMeters = squareMeters;
    }

}
