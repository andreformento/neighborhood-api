package com.formento.neighborhood.importation.impl;

import com.formento.neighborhood.model.Point;
import com.formento.neighborhood.model.Property;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.Optional;

class PropertyInput implements Serializable {

    private final String title;
    private final Integer price;
    private final String description;
    private final Integer lat;
    private final Integer longi;
    private final Short beds;
    private final Short baths;
    private final Integer squareMeters;

    @ConstructorProperties({"title", "price", "description", "lat", "long", "beds", "baths", "squareMeters"})
    PropertyInput(String title, Integer price, String description, Integer lat, Integer longi, Short beds, Short baths, Integer squareMeters) {
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
                Optional.empty(),
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
