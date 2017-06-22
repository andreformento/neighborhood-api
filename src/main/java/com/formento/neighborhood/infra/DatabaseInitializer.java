package com.formento.neighborhood.infra;

import com.formento.neighborhood.importation.PropertyLoader;
import com.formento.neighborhood.importation.ProvinceLoader;
import com.formento.neighborhood.repository.PropertyRepository;
import com.formento.neighborhood.repository.ProvinceRepository;
import com.formento.neighborhood.repository.impl.PropertyRepositoryDefault;
import com.formento.neighborhood.repository.impl.ProvinceRepositoryDefault;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseInitializer {

    private final PropertyLoader propertyLoader;
    private final ProvinceLoader provinceLoader;

    @Autowired
    public DatabaseInitializer(PropertyLoader propertyLoader, ProvinceLoader provinceLoader) {
        this.propertyLoader = propertyLoader;
        this.provinceLoader = provinceLoader;
    }

    @Bean
    public PropertyRepository propertyRepository() throws IOException {
        return new PropertyRepositoryDefault(propertyLoader.load());
    }

    @Bean
    public ProvinceRepository provinceRepository() throws IOException {
        return new ProvinceRepositoryDefault(provinceLoader.load());
    }

}
