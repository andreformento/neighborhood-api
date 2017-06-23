package com.formento.neighborhood.infra;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.formento.neighborhood.model.Boundary;
import com.formento.neighborhood.model.Point;
import com.formento.neighborhood.model.Province;
import com.formento.neighborhood.repository.PropertyRepository;
import com.formento.neighborhood.repository.ProvinceRepository;
import com.formento.neighborhood.service.PropertyService;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseInitializerIT {

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private PropertyService propertyService;

    @Test
    public void shouldInitializePropertiesAfterStartApplication() {
        assertThat(propertyRepository.count()).isGreaterThanOrEqualTo(8000L);
    }

    @Test
    public void shouldInitializeProvincesAfterStartApplication() {
        assertThat(provinceRepository.count()).isEqualTo(6L);
    }
    @Test
    public void shouldInitializeProvincesNotNull() {
        // given
        final Collection<Province> provinces = provinceRepository.findAll();

        // when
        assertThat(provinces).asList().doesNotContainNull();
        assertThat(provinces.stream().map(Province::getDescription).collect(toList())).asList().doesNotContainNull();
        assertThat(provinces.stream().map(Province::getBoundary).collect(toList())).asList().doesNotContainNull();
        assertThat(provinces.stream().map(Province::getBoundary).map(Boundary::getBottomRight).collect(toList())).asList().doesNotContainNull();
        assertThat(provinces.stream().map(Province::getBoundary).map(Boundary::getBottomRight).map(Point::getX).collect(toList())).asList().doesNotContainNull();
        assertThat(provinces.stream().map(Province::getBoundary).map(Boundary::getBottomRight).map(Point::getY).collect(toList())).asList().doesNotContainNull();
        assertThat(provinces.stream().map(Province::getBoundary).map(Boundary::getUpperLeft).collect(toList())).asList().doesNotContainNull();
        assertThat(provinces.stream().map(Province::getBoundary).map(Boundary::getUpperLeft).map(Point::getX).collect(toList())).asList().doesNotContainNull();
        assertThat(provinces.stream().map(Province::getBoundary).map(Boundary::getUpperLeft).map(Point::getY).collect(toList())).asList().doesNotContainNull();
    }

}
