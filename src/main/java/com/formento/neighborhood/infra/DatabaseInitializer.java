package com.formento.neighborhood.infra;

import com.formento.neighborhood.importation.PropertyImport;
import com.formento.neighborhood.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
public class DatabaseInitializer {

    private final PropertyImport propertyImport;

    @Autowired
    public DatabaseInitializer(PropertyImport propertyImport) {
        this.propertyImport = propertyImport;
    }

    @PostConstruct
    private void initDatabase() throws IOException {
        final Iterable<Property> properties = propertyImport.doImport();

        System.out.println(properties);
    }

}
