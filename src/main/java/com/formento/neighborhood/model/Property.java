package com.formento.neighborhood.model;

import static java.util.Collections.emptyList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

public class Property implements Serializable {

    private final Optional<Long> id;
    @NotEmpty
    private final String title;
    @NotNull
    @Min(0)
    private final Integer price;
    @NotEmpty
    private final String description;
    @NotNull
    @Valid
    private final Point point;
    @Min(1)
    @Max(5)
    private final Integer beds;
    @Min(1)
    @Max(4)
    private final Integer baths;
    @Min(20)
    @Max(240)
    private final Integer squareMeters;
    @NotEmpty(message = "A localização (x, y) da propriedade está fora da extensão do mapa")
    private final Collection<Province> provinces;

    public Property(Optional<Long> id, String title, Integer price, String description, Point point,
        Integer beds, Integer baths, Integer squareMeters, Collection<Province> provinces) {
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
        @JsonProperty(value = "title") String title,
        @JsonProperty(value = "price") Integer price,
        @JsonProperty(value = "description") String description,
        @JsonProperty(value = "point") @JsonUnwrapped Point point,
        @JsonProperty(value = "beds") Integer beds,
        @JsonProperty(value = "baths") Integer baths,
        @JsonProperty(value = "squareMeters") Integer squareMeters) {
        this(id, title, price, description, point, beds, baths, squareMeters, emptyList());
    }

    public Property(Optional<Long> id, Property entity) {
        this(id, entity.title, entity.price, entity.description, entity.point, entity.beds, entity.baths, entity.squareMeters, entity.provinces);
    }

    public Property(Property entity, Collection<Province> provinces) {
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

    public Integer getBeds() {
        return beds;
    }

    public Integer getBaths() {
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
