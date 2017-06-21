package com.formento.neighborhood.importation.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formento.neighborhood.importation.ProvinceLoader;
import com.formento.neighborhood.model.Boundary;
import com.formento.neighborhood.model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ProvinceLoaderFile implements ProvinceLoader {

    private final ObjectMapper objectMapper;

    @Autowired
    public ProvinceLoaderFile(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Collection<Province> load() throws IOException {
        final Resource resource = new ClassPathResource("provinces.json");
        final File file = resource.getFile();
        final Map<String, Boundary> boundaryMap = objectMapper.readValue(file, new TypeReference<Map<String, Boundary>>() {
        });

        return boundaryMap.
                entrySet().
                stream().
                map(entry -> new Province(entry.getKey(), entry.getValue())).
                collect(Collectors.toList());
    }

}
