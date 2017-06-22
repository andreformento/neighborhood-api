package com.formento.neighborhood.infra;

import static java.util.stream.Collectors.toList;

import com.formento.neighborhood.component.ProvinceFinder;
import com.formento.neighborhood.importation.PropertyLoader;
import com.formento.neighborhood.importation.ProvinceLoader;
import com.formento.neighborhood.model.Property;
import com.formento.neighborhood.model.Province;
import com.formento.neighborhood.repository.PropertyRepository;
import com.formento.neighborhood.repository.ProvinceRepository;
import com.formento.neighborhood.repository.impl.PropertyRepositoryDefault;
import com.formento.neighborhood.repository.impl.ProvinceRepositoryDefault;
import java.io.IOException;
import java.util.Collection;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseInitializer {

    private final PropertyLoader propertyLoader;
    private final ProvinceLoader provinceLoader;
    private final ProvinceFinder provinceFinder;

    private Collection<Property> properties;
    private Collection<Province> provinces;

    @Autowired
    public DatabaseInitializer(PropertyLoader propertyLoader, ProvinceLoader provinceLoader,
        ProvinceFinder provinceFinder) {
        this.propertyLoader = propertyLoader;
        this.provinceLoader = provinceLoader;
        this.provinceFinder = provinceFinder;
    }

    @PostConstruct
    public void init() throws IOException {
        provinces = provinceLoader.load();

        properties = propertyLoader.
            load().
            stream().
            map(p -> new Property(p, provinceFinder.find(provinces, p.getPoint()))).
            collect(toList());
    }

    @Bean
    public PropertyRepository propertyRepository() throws IOException {
        return new PropertyRepositoryDefault(properties);
    }

    @Bean
    public ProvinceRepository provinceRepository() throws IOException {
        return new ProvinceRepositoryDefault(provinces);
    }

}
