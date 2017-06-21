package com.formento.neighborhood.infra;

import com.formento.neighborhood.repository.PropertyRepository;
import com.formento.neighborhood.repository.ProvinceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseInitializerIT {

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private ProvinceRepository provinceRepository;

    @Test
    public void shouldInitializePropertiesAfterStartApplication() {
        assertThat(propertyRepository.count()).isEqualTo(8000L);
    }
    @Test
    public void shouldInitializeProvincesAfterStartApplication() {
        assertThat(provinceRepository.count()).isEqualTo(8000L);
    }

}
