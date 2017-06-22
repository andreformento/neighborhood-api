package com.formento.neighborhood.service;

import com.formento.neighborhood.model.Point;
import com.formento.neighborhood.model.Province;
import java.util.Collection;

public interface ProvinceService {

    Collection<Province> findByPoint(final Point point);

}
