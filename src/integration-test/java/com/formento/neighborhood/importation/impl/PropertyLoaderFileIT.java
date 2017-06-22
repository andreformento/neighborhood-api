package com.formento.neighborhood.importation.impl;

import static org.assertj.core.api.Assertions.assertThat;

import com.formento.neighborhood.model.Property;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertyLoaderFileIT {

    @Autowired
    private PropertyLoaderFile propertyImportFile;

    @Test
    public void shouldImportFromFile() throws IOException {
        // when
        final Iterable<Property> properties = propertyImportFile.load();

        // then
        assertThat(properties).isNotNull();
        assertThat(properties).asList().hasSize(8000);
    }

}
