package com.formento.neighborhood.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.formento.neighborhood.component.ProvinceFinder;
import com.formento.neighborhood.model.Boundary;
import com.formento.neighborhood.model.Point;
import com.formento.neighborhood.model.Province;
import com.formento.neighborhood.repository.ProvinceRepository;
import com.formento.neighborhood.service.ProvinceService;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

class ProvinceServiceDefaultBDD {

    private final ProvinceService provinceService;
    private final ProvinceRepository provinceRepository;

    private Point point;
    private Collection<Province> provincesByPoint;

    public ProvinceServiceDefaultBDD(ProvinceService provinceService, ProvinceRepository provinceRepository) {
        this.provinceService = provinceService;
        this.provinceRepository = provinceRepository;
    }

    public ProvinceServiceDefaultBDD givenAValidRegion(final Collection<Boundary> boundaries) {
        final List<Province> provinces = boundaries.
            stream().
            map(o -> new Province("description", o)).
            collect(Collectors.toList());
        return givenAValidRegionProvince(provinces);
    }

    public ProvinceServiceDefaultBDD givenAValidRegionProvince(final Collection<Province> provinces) {
        when(provinceRepository.findAll()).thenReturn(provinces);
        return this;
    }

    public ProvinceServiceDefaultBDD givenSimpleRegion() {
        return givenAValidRegion(ImmutableList.<Boundary>builder().
            add(new Boundary(0, 6, 5, 1)).
            build()
        );
    }

    public ProvinceServiceDefaultBDD givenComplexRegion() {
        return givenAValidRegion(ImmutableList.<Boundary>builder().
            add(new Boundary(0, 3, 2, 0)).
            add(new Boundary(0, 5, 2, 3)).
            add(new Boundary(1, 4, 4, 2)).
            add(new Boundary(2, 3, 4, 0)).
            add(new Boundary(2, 5, 6, 3)).
            add(new Boundary(3, 3, 6, 0)).
            build()
        );
    }

    public ProvinceServiceDefaultBDD andAPoint(final Integer x, final Integer y) {
        point = new Point(x, y);
        return this;
    }

    public ProvinceServiceDefaultBDD whenGetBoundariesByPoint() {
        provincesByPoint = provinceService.findByPoint(point);
        return this;
    }

    public ProvinceServiceDefaultBDD thenShouldBeExistExactlyNBoundaries(final Integer quantityOfBoundaries) {
        assertThat(provincesByPoint).isNotNull();
        assertThat(provincesByPoint).hasSize(quantityOfBoundaries);
        return this;
    }

    public ProvinceServiceDefaultBDD thenShouldHaveThisBoundary(final Integer upperLeftX, final Integer upperLeftY, final Integer bottomRightX,
        final Integer bottomRightY) {
        return thenShouldHaveThisBoundary(new Boundary(upperLeftX, upperLeftY, bottomRightX, bottomRightY));
    }

    public ProvinceServiceDefaultBDD thenShouldHaveThisBoundary(final Boundary boundary) {
        assertThat(provincesByPoint.stream().map(Province::getBoundary).findAny()).isNotNull();
        return this;
    }

}
