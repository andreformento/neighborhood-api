package com.formento.neighborhood.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
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

    @JsonCreator
    public Property(
        @JsonProperty(value = "id") Long id,
        @JsonProperty(value = "title") String title,
        @JsonProperty(value = "price") Integer price,
        @JsonProperty(value = "description") String description,
        @JsonProperty(value = "point") @JsonUnwrapped Point point,
        @JsonProperty(value = "beds") Short beds,
        @JsonProperty(value = "baths") Short baths,
        @JsonProperty(value = "squareMeters") Integer squareMeters) {

        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.point = point;
        this.beds = beds;
        this.baths = baths;
        this.squareMeters = squareMeters;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    @JsonUnwrapped
    public Point getPoint() {
        return point;
    }

    public Short getBeds() {
        return beds;
    }

    public Short getBaths() {
        return baths;
    }

    public Integer getSquareMeters() {
        return squareMeters;
    }

}
