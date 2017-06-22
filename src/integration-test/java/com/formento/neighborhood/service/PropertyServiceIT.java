package com.formento.neighborhood.service;

import com.formento.neighborhood.model.Boundary;
import com.formento.neighborhood.model.Property;
import com.formento.neighborhood.repository.PropertyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
//
        System.out.println("total: " + propertyRepository.
                findAll().
                stream().
                filter(p -> p.getPoint().getX() >= 70).
                filter(p -> p.getPoint().getX() <= 70).
                filter(p -> p.getPoint().getY() <= 95).
                filter(p -> p.getPoint().getY() >= 0).count());

        // then
        assertThat(properties).isNotNull();
        assertThat(properties.size()).isEqualTo(3);
    }

    @Test
    public void shouldFindSpecificPointsInter() {
        // given
        final Boundary boundary = new Boundary(400, 1000, 600, 500);
//        final Boundary boundary = new Boundary(70, 95, 70, 0);

        // when
        final Collection<Property> properties = propertyService.findPropertiesInsideBoundary(boundary);
//
        System.out.println("total: " + propertyRepository.
                findAll().
                stream().
                filter(p -> p.getPoint().getX() >= 400).
                filter(p -> p.getPoint().getX() <= 600).
                filter(p -> p.getPoint().getY() <= 1000).
                filter(p -> p.getPoint().getY() >= 500).count());

        // then
        assertThat(properties).isNotNull();
        assertThat(properties.size()).isEqualTo(541);
    }

    @Test
    public void shouldFindInEachProvince() {
        // given
        final Boundary gode = new Boundary(0, 1000, 600, 500);
        final Boundary ruja = new Boundary(400, 1000, 1100, 500);
        final Boundary jaby = new Boundary(1100, 1000, 1400, 500);
        final Boundary scavy = new Boundary(0, 500, 600, 0);
        final Boundary groola = new Boundary(600, 500, 800, 0);
        final Boundary nova = new Boundary(800, 500, 1400, 0);

        // when
        final int godeSize = propertyService.findPropertiesInsideBoundary(gode).size();
        final int rujaSize = propertyService.findPropertiesInsideBoundary(ruja).size();
        final int jabySize = propertyService.findPropertiesInsideBoundary(jaby).size();
        final int scavySize = propertyService.findPropertiesInsideBoundary(scavy).size();
        final int groolaSize = propertyService.findPropertiesInsideBoundary(groola).size();
        final int novaSize = propertyService.findPropertiesInsideBoundary(nova).size();

        System.out.println("total gode: " + propertyRepository.
                findAll().
                stream().
                filter(p -> p.getPoint().getX() >= 0).
                filter(p -> p.getPoint().getX() <= 600).
                filter(p -> p.getPoint().getY() <= 1000).
                filter(p -> p.getPoint().getY() >= 500).count()
                );
        System.out.println("total ruja: " + propertyRepository.
                findAll().
                stream().
                filter(p -> p.getPoint().getX() >= 400).
                filter(p -> p.getPoint().getX() <= 1100).
                filter(p -> p.getPoint().getY() <= 1000).
                filter(p -> p.getPoint().getY() >= 500).count()
                );
        System.out.println("total jaby: " + propertyRepository.
                findAll().
                stream().
                filter(p -> p.getPoint().getX() >= 1100).
                filter(p -> p.getPoint().getX() <= 1400).
                filter(p -> p.getPoint().getY() <= 1000).
                filter(p -> p.getPoint().getY() >= 500).count()
                );
        System.out.println("total scavy: " + propertyRepository.
                findAll().
                stream().
                filter(p -> p.getPoint().getX() >= 0).
                filter(p -> p.getPoint().getX() <= 600).
                filter(p -> p.getPoint().getY() <= 500).
                filter(p -> p.getPoint().getY() >= 0).count()
                );

        System.out.println("total groola: " + propertyRepository.
                findAll().
                stream().
                filter(p -> p.getPoint().getX() >= 600).
                filter(p -> p.getPoint().getX() <= 800).
                filter(p -> p.getPoint().getY() <= 500).
                filter(p -> p.getPoint().getY() >= 0).count()
                );
        System.out.println("total nova: " + propertyRepository.
                findAll().
                stream().
                filter(p -> p.getPoint().getX() >= 800).
                filter(p -> p.getPoint().getX() <= 1400).
                filter(p -> p.getPoint().getY() <= 500).
                filter(p -> p.getPoint().getY() >= 0).count()
                );

        // then
        assertThat(godeSize).isEqualTo(1746);
        assertThat(rujaSize).isEqualTo(1969);
        assertThat(jabySize).isEqualTo(884);
        assertThat(scavySize).isEqualTo(1728);
        assertThat(groolaSize).isEqualTo(577);
        assertThat(novaSize).isEqualTo(1647);
    }

}
