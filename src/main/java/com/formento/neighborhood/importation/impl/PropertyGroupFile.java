package com.formento.neighborhood.importation.impl;

import com.formento.neighborhood.model.Property;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

class PropertyGroupFile implements Serializable {

    private final Integer totalProperties;
    private final Collection<PropertyFile> properties;

    @ConstructorProperties({"totalProperties", "properties"})
    PropertyGroupFile(Integer totalProperties, Collection<PropertyFile> properties) {
        this.totalProperties = totalProperties;
        this.properties = properties;
    }

    Collection<Property> generateProperties(){
        return properties.
                stream().
                map(PropertyFile::generateProperty).
                collect(Collectors.toList());
    }

}
