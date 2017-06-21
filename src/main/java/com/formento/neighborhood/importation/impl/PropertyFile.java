package com.formento.neighborhood.importation.impl;

import com.formento.neighborhood.model.Point;
import com.formento.neighborhood.model.Property;

import java.beans.ConstructorProperties;
import java.io.Serializable;

class PropertyFile implements Serializable {

    private final Long id;
    private final String title;
    private final Integer price;
    private final String description;
    private final Integer lat;
    private final Integer longi;
    private final Short beds;
    private final Short baths;
    private final Integer squareMeters;

    @ConstructorProperties({"id", "title", "price", "description", "lat", "long", "beds", "baths", "squareMeters"})
    PropertyFile(Long id, String title, Integer price, String description, Integer lat, Integer longi, Short beds, Short baths, Integer squareMeters) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.lat = lat;
        this.longi = longi;
        this.beds = beds;
        this.baths = baths;
        this.squareMeters = squareMeters;
    }

    public Property generateProperty() {
        return new Property(
                id,
                title,
                price,
                description,
                new Point(lat,longi),
                beds,
                baths,
                squareMeters
        );
    }

}
