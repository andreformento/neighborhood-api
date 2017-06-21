package com.formento.neighborhood.importation.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formento.neighborhood.importation.PropertyImport;
import com.formento.neighborhood.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class PropertyImportFile implements PropertyImport {

    private final ObjectMapper objectMapper;

    @Autowired
    public PropertyImportFile(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Iterable<Property> doImport() throws IOException {
        final Resource resource = new ClassPathResource("properties.json");
        final File file = resource.getFile();
        final PropertyGroupInput propertyGroupInput = objectMapper.readValue(file, PropertyGroupInput.class);

        return propertyGroupInput.generateProperties();
    }

}
