package com.formento.neighborhood.api.controller;

import com.formento.neighborhood.api.mapper.PropertiesMapper;
import com.formento.neighborhood.model.Boundary;
import com.formento.neighborhood.model.Property;
import com.formento.neighborhood.service.PropertyService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/properties")
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
    public PropertiesMapper findPropertiesInsideBoundary(Integer ax, Integer ay, Integer bx, Integer by) {
        return new PropertiesMapper(propertyService.findPropertiesInsideBoundary(new Boundary(ax, ay, bx, by)));
    }

}
