package com.formento.neighborhood.importation.impl;

import com.formento.neighborhood.model.Property;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertyImportFileIT {

    @Autowired
    private PropertyImportFile propertyImportFile;

    @Test
    public void shouldImportFromFile() throws IOException {
        // when
        final Iterable<Property> properties = propertyImportFile.doImport();

        // then
        assertThat(properties).isNotNull();
        assertThat(properties).asList().hasSize(8000);
    }

}
