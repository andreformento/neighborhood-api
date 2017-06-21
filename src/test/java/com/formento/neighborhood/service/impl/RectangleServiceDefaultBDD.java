package com.formento.neighborhood.service.impl;

import com.formento.neighborhood.model.Boundary;
import com.formento.neighborhood.model.Point;
import com.formento.neighborhood.repository.BoundaryRepository;
import com.formento.neighborhood.service.BoundaryService;
import com.google.common.collect.ImmutableList;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

class BoundaryServiceDefaultBDD {

    private final BoundaryService boundaryService;
    private final BoundaryRepository boundaryRepository;

    private Point point;
    private Collection<Boundary> boundariesByPoint;

    public BoundaryServiceDefaultBDD(BoundaryService boundaryService, BoundaryRepository boundaryRepository) {
        this.boundaryService = boundaryService;
        this.boundaryRepository = boundaryRepository;
    }

    public BoundaryServiceDefaultBDD givenAValidRegion(final List<Boundary> boundaries) {
        when(boundaryRepository.findAll()).thenReturn(boundaries);
        return this;
    }

    public BoundaryServiceDefaultBDD givenSimpleRegion() {
        return givenAValidRegion(ImmutableList.<Boundary>builder().
                add(new Boundary(0, 6, 5, 1)).
                build()
        );
    }

    public BoundaryServiceDefaultBDD givenComplexRegion() {
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

    public BoundaryServiceDefaultBDD andAPoint(final Integer x, final Integer y) {
        point = new Point(x, y);
        return this;
    }

    public BoundaryServiceDefaultBDD whenGetBoundariesByPoint() {
        boundariesByPoint = boundaryService.findByPoint(point);
        return this;
    }

    public BoundaryServiceDefaultBDD thenShouldBeExistExactlyNBoundaries(final Integer quantityOfBoundaries) {
        assertThat(boundariesByPoint, is(notNullValue()));
        assertThat(boundariesByPoint.size(), is(equalTo(quantityOfBoundaries)));
        return this;
    }

    public BoundaryServiceDefaultBDD thenShouldHaveThisBoundary(final Integer upperLeftX, final Integer upperLeftY, final Integer rightBottomX, final Integer rightBottomY) {
        return thenShouldHaveThisBoundary(new Boundary(upperLeftX, upperLeftY, rightBottomX, rightBottomY));
    }

    public BoundaryServiceDefaultBDD thenShouldHaveThisBoundary(final Boundary boundary) {
        assertThat(boundariesByPoint.contains(boundary), is(true));
        return this;
    }

}
