package com.formento.neighborhood.importation.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formento.neighborhood.importation.PropertyImport;
import com.formento.neighborhood.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        // read from file (buffer)
        // insert into database
//        final Resource resource = new ClassPathResource("properties.json");
//        final File file = resource.getFile();
//        final Iterable<Property> properties = objectMapper.readValue(file, new TypeReference<Iterable<Property>>() {
//        });

        return null;
    }
}
