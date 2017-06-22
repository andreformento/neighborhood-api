package com.formento.neighborhood.service;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.formento.neighborhood.model.Boundary;
import com.formento.neighborhood.model.Point;
import com.formento.neighborhood.model.Property;
import com.formento.neighborhood.repository.PropertyRepository;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
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
    @Autowired
    private PropertyRepository propertyRepository;

    @Test
    public void shouldFindAllPoints() {
        // given
        final Boundary boundary = new Boundary(0, 1000, 1400, 0);

        // when
        final Collection<Property> properties = propertyService.findPropertiesInsideBoundary(boundary);

        // then
        assertThat(properties).isNotNull();
        assertThat(properties.size()).isEqualTo(8000);
    }

    @Test
    public void shouldFindSpecificPoints() {
        // given
        final Boundary boundary = new Boundary(70, 95, 70, 0);

        // when
        final Collection<Property> properties = propertyService.findPropertiesInsideBoundary(boundary);

        // then
        assertThat(properties).isNotNull();
        assertThat(properties.size()).isEqualTo(2);
    }

    @Test
    public void shouldFindInEachProvince() {
        // given
        final Boundary gode = new Boundary(0, 1000, 600, 500);
        final Boundary ruja = new Boundary(400, 1000, 1100, 500);
        final Boundary jaby = new Boundary(1100, 1000, 1400, 500);
        final Boundary scavy = new Boundary(0, 500, 600, 0);
        final Boundary groola = new Boundary(0, 500, 800, 0);
        final Boundary nova = new Boundary(800, 500, 1400, 0);

        // when
        final int godeSize = propertyService.findPropertiesInsideBoundary(gode).size();
        final int rujaSize = propertyService.findPropertiesInsideBoundary(ruja).size();
        final int jabySize = propertyService.findPropertiesInsideBoundary(jaby).size();
        final int scavySize = propertyService.findPropertiesInsideBoundary(scavy).size();
        final int groolaSize = propertyService.findPropertiesInsideBoundary(groola).size();
        final int novaSize = propertyService.findPropertiesInsideBoundary(nova).size();

        final List<Property> godeProperties = propertyRepository.
            findAll().
            stream().
            filter(p -> p.getPoint().getX() >= 0).
            filter(p -> p.getPoint().getY() <= 1000).
            filter(p -> p.getPoint().getX() >= 600).
            filter(p -> p.getPoint().getY() <= 500).
            collect(toList());

        System.out.println(godeProperties.size());

        // then
        assertThat(godeSize).isEqualTo(2221);
//        assertThat(rujaSize).isEqualTo(8000);
//        assertThat(jabySize).isEqualTo(8000);
//        assertThat(scavySize).isEqualTo(8000);
//        assertThat(groolaSize).isEqualTo(8000);
//        assertThat(novaSize).isEqualTo(8000);
    }

}
