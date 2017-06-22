package com.formento.neighborhood.service.impl;

import static java.util.stream.Collectors.toList;

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

    @Autowired
    public ProvinceServiceDefault(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    @Override
    public Collection<Province> findByPoint(final Point point) {
        return provinceRepository.
            findAll().
            stream().
            sorted().
            filter(province -> province.getBoundary().containsPoint(point)).
            collect(toList());
    }

}
