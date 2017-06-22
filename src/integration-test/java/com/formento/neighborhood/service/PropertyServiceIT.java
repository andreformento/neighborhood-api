package com.formento.neighborhood.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.formento.neighborhood.model.Boundary;
import com.formento.neighborhood.model.Point;
import com.formento.neighborhood.model.Property;
import com.formento.neighborhood.model.Province;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertyServiceIT {

    @Autowired
    private PropertyService propertyService;

    @Test
    public void shouldAllPoints() {
        // given
        final Boundary boundary = new Boundary(0, 1000, 1400, 0);

        // when
        final Collection<Property> properties = propertyService.findPropertiesInsideBoundary(boundary);

        // then
        assertThat(properties).isNotNull();
        assertThat(properties.size()).isEqualTo(8000);
    }
    
}
