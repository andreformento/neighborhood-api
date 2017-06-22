package com.formento.neighborhood.infra;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.formento.neighborhood.repository.PropertyRepository;
import com.formento.neighborhood.repository.ProvinceRepository;
import com.formento.neighborhood.service.PropertyService;
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
        assertThat(propertyRepository.count()).isEqualTo(8000L);
    }

    @Test
    public void shouldInitializeProvincesAfterStartApplication() {
        assertThat(provinceRepository.count()).isEqualTo(6L);
    }

//    @Test
//    public void shouldInitializeProvincesAfterStartApplication() {
//        assertThat(propertyService.).isEqualTo(8L);
//    find each region
//    }

}
