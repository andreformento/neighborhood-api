package com.formento.neighborhood.api.controller;

import com.formento.neighborhood.api.mapper.PropertiesMapper;
import com.formento.neighborhood.model.Boundary;
import com.formento.neighborhood.model.Property;
import com.formento.neighborhood.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/properties")
@Validated
public class PropertyController {

    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Property create(@RequestBody final Property property) {
        return propertyService.create(property);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Property findById(@PathVariable("id") Long id) {
        return propertyService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PropertiesMapper findPropertiesInsideBoundary(@NotNull final Integer ax, @NotNull final Integer ay, @NotNull final Integer bx, @NotNull final Integer by) {
        return new PropertiesMapper(propertyService.findPropertiesInsideBoundary(new Boundary(ax, ay, bx, by)));
    }

}
