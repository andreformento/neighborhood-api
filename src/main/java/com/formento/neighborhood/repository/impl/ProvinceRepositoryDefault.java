package com.formento.neighborhood.repository.impl;

import com.formento.neighborhood.model.Province;
import com.formento.neighborhood.repository.ProvinceRepository;
import java.util.Collection;

public class ProvinceRepositoryDefault implements ProvinceRepository {

    private final Collection<Province> provinces;

    public ProvinceRepositoryDefault(Collection<Province> provinces) {
        this.provinces = provinces;
    }

    @Override
    public Collection<Province> findAll() {
        return provinces;
    }

    @Override
    public Long count() {
        return (long) provinces.size();
    }

}
