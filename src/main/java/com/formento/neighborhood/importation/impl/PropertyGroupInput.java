package com.formento.neighborhood.importation.impl;

import com.formento.neighborhood.model.Property;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

class PropertyGroupInput implements Serializable {

    private final Integer totalProperties;
    private final Collection<PropertyInput> properties;

    @ConstructorProperties({"totalProperties", "properties"})
    PropertyGroupInput(Integer totalProperties, Collection<PropertyInput> properties) {
        this.totalProperties = totalProperties;
        this.properties = properties;
    }

    Collection<Property> generateProperties(){
        return properties.
                stream().
                map(PropertyInput::generateProperty).
                collect(Collectors.toList());
    }

}
