package com.formento.neighborhood.repository;

import com.formento.neighborhood.model.Province;

import java.util.Collection;

public interface ProvinceRepository {

    Collection<Province> findAll();

    Long count();

}
