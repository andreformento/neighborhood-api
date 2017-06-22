package com.formento.neighborhood.importation.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formento.neighborhood.importation.PropertyLoader;
import com.formento.neighborhood.model.Property;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

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
        final File file = resource.getFile();
        final PropertyGroupInput propertyGroupInput = objectMapper.readValue(file, PropertyGroupInput.class);

        return propertyGroupInput.generateProperties();
    }

}
