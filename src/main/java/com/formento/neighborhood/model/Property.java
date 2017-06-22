package com.formento.neighborhood.model;

import static java.util.Collections.emptyList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class Property implements Serializable {

    private final Optional<Long> id;
    private final String title;
    private final Integer price;
    private final String description;
    private final Point point;
    private final Short beds;
    private final Short baths;
    private final Integer squareMeters;
    private final Collection<Province> provinces;

    public Property(@NotNull Optional<Long> id, @NotNull String title, @NotNull Integer price, @NotNull String description, @NotNull Point point,
        @NotNull Short beds, @NotNull Short baths, @NotNull Integer squareMeters, @NotNull Collection<Province> provinces) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.point = point;
        this.beds = beds;
        this.baths = baths;
        this.squareMeters = squareMeters;
        this.provinces = provinces;
    }

    @JsonCreator
    public Property(
        @JsonProperty(value = "id") Optional<Long> id,
        @NotEmpty @JsonProperty(value = "title") String title,
        @NotEmpty @JsonProperty(value = "price") Integer price,
        @NotEmpty @JsonProperty(value = "description") String description,
        @NotEmpty @JsonProperty(value = "point") @JsonUnwrapped Point point,
        @NotEmpty @Size(min = 1, max = 5) @JsonProperty(value = "beds") Short beds,
        @NotEmpty @Size(min = 1, max = 4) @JsonProperty(value = "baths") Short baths,
        @NotEmpty @Size(min = 20, max = 240) @JsonProperty(value = "squareMeters") Integer squareMeters) {
        this(id, title, price, description, point, beds, baths, squareMeters, emptyList());
    }

    public Property(Optional<Long> id, Property entity) {
        this(id, entity.title, entity.price, entity.description, entity.point, entity.beds, entity.baths, entity.squareMeters, entity.provinces);
    }

    public Property(@NotNull Property entity, @NotEmpty(message = "Provinces not found on this point") Collection<Province> provinces) {
        this(entity.id, entity.title, entity.price, entity.description, entity.point, entity.beds, entity.baths, entity.squareMeters, provinces);
    }

    public Optional<Long> getId() {
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

    @JsonSerialize(using = ProvincesSerializer.class)
    public Collection<Province> getProvinces() {
        return provinces;
    }

}
