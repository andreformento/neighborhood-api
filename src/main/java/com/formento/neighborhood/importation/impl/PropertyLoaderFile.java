package com.formento.neighborhood.importation.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formento.neighborhood.importation.PropertyLoader;
import com.formento.neighborhood.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class PropertyLoaderFile implements PropertyLoader {

    private final ObjectMapper objectMapper;

    @Autowired
    public PropertyLoaderFile(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Collection<Property> load() throws IOException {
        final Resource resource = new ClassPathResource("properties.json");

        final PropertyGroupInput propertyGroupInput = objectMapper.readValue(resource.getInputStream(), PropertyGroupInput.class);

        return propertyGroupInput.generateProperties();
    }

}
