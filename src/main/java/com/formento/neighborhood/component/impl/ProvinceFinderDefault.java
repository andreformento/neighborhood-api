package com.formento.neighborhood.component.impl;

import static java.util.stream.Collectors.toList;

import com.formento.neighborhood.component.ProvinceFinder;
import com.formento.neighborhood.model.Point;
import com.formento.neighborhood.model.Province;
import java.util.Collection;
import org.springframework.stereotype.Component;

@Component
public class ProvinceFinderDefault implements ProvinceFinder {

    @Override
    public Collection<Province> find(Collection<Province> provinces, Point point) {
        return provinces.
            stream().
            sorted().
            filter(province -> province.getBoundary().containsPoint(point)).
            collect(toList());
    }

}
