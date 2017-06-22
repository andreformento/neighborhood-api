package com.formento.neighborhood.api.mapper;

import com.formento.neighborhood.model.Property;
import java.io.Serializable;
import java.util.Collection;

public class PropertiesMapper implements Serializable {

    private final Integer foundProperties;
    private final Collection<Property> properties;

    public PropertiesMapper(Collection<Property> properties) {
        this.foundProperties = properties.size();
        this.properties = properties;
    }

    public Integer getFoundProperties() {
        return foundProperties;
    }

    public Collection<Property> getProperties() {
        return properties;
    }

}
