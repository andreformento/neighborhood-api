package com.formento.neighborhood.service.impl;

import com.formento.neighborhood.component.ProvinceFinder;
import com.formento.neighborhood.model.Point;
import com.formento.neighborhood.model.Province;
import com.formento.neighborhood.repository.ProvinceRepository;
import com.formento.neighborhood.service.ProvinceService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinceServiceDefault implements ProvinceService {

    private final ProvinceRepository provinceRepository;
    private final ProvinceFinder provinceFinder;

    @Autowired
    public ProvinceServiceDefault(ProvinceRepository provinceRepository, ProvinceFinder provinceFinder) {
        this.provinceRepository = provinceRepository;
        this.provinceFinder = provinceFinder;
    }

    @Override
    public Collection<Province> findByPoint(final Point point) {
        return provinceFinder.find(provinceRepository.findAll(), point);
    }

}
