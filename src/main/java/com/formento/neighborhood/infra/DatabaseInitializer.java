package com.formento.neighborhood.infra;

import com.formento.neighborhood.importation.PropertyLoader;
import com.formento.neighborhood.importation.ProvinceLoader;
import com.formento.neighborhood.model.Province;
import com.formento.neighborhood.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Collection;

@Configuration
public class DatabaseInitializer {

    private final PropertyLoader propertyLoader;
    private final ProvinceLoader provinceLoader;
    private final PropertyRepository propertyRepository;

    @Autowired
    public DatabaseInitializer(PropertyLoader propertyLoader, ProvinceLoader provinceLoader, PropertyRepository propertyRepository) {
        this.propertyLoader = propertyLoader;
        this.provinceLoader = provinceLoader;
        this.propertyRepository = propertyRepository;
    }

    @PostConstruct
    private void initProperties() throws IOException {
        propertyRepository.insert(propertyLoader.load());
    }

    @Bean
    public Collection<Province> loadProvinces() throws IOException {
        return provinceLoader.load();
    }

}
