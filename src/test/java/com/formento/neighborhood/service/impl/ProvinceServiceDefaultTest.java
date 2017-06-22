package com.formento.neighborhood.service.impl;

import com.formento.neighborhood.repository.ProvinceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProvinceServiceDefaultTest {

    @InjectMocks
    private ProvinceServiceDefault boundaryService;
    @Mock
    private ProvinceRepository provinceRepository;

    private BoundaryServiceDefaultBDD boundaryServiceDefaultBDD;

    @Before
    public void init() {
        boundaryServiceDefaultBDD = new BoundaryServiceDefaultBDD(boundaryService, provinceRepository);
    }

    @Test
    public void shouldFindPointInsideSimpleBoundary() {
        boundaryServiceDefaultBDD.
            givenSimpleRegion().
            andAPoint(2, 3).
            whenGetBoundariesByPoint().
            thenShouldBeExistExactlyNBoundaries(1).
            thenShouldHaveThisBoundary(0, 6, 5, 1);
    }

    @Test
    public void shouldFindPointInsideComplexBoundary() {
        boundaryServiceDefaultBDD.
            givenComplexRegion().
            andAPoint(5, 2).
            whenGetBoundariesByPoint().whenGetBoundariesByPoint().
            thenShouldBeExistExactlyNBoundaries(1).
            thenShouldHaveThisBoundary(3, 3, 6, 0);
    }

    @Test
    public void shouldFindPointInsideComplexBoundaryOnUpperLeftBorder() {
        boundaryServiceDefaultBDD.
            givenComplexRegion().
            andAPoint(0, 0).
            whenGetBoundariesByPoint().whenGetBoundariesByPoint().
            thenShouldBeExistExactlyNBoundaries(1).
            thenShouldHaveThisBoundary(0, 3, 2, 0);
    }

    @Test
    public void shouldNotFindPointOutsideBoundary() {
        boundaryServiceDefaultBDD.
            givenComplexRegion().
            andAPoint(6, 6).
            whenGetBoundariesByPoint().
            thenShouldBeExistExactlyNBoundaries(0);
    }

    @Test
    public void shouldFindPointInsideComplexBoundaryOnBottomRightBorder() {
        boundaryServiceDefaultBDD.
            givenComplexRegion().
            andAPoint(6, 5).
            whenGetBoundariesByPoint().
            thenShouldBeExistExactlyNBoundaries(1).
            thenShouldHaveThisBoundary(2, 5, 6, 3);
    }

    @Test
    public void shouldFind2PointsInsideComplexBoundary() {
        boundaryServiceDefaultBDD.
            givenComplexRegion().
            andAPoint(3, 1).
            whenGetBoundariesByPoint().
            thenShouldBeExistExactlyNBoundaries(2).
            thenShouldHaveThisBoundary(2, 3, 4, 0).
            thenShouldHaveThisBoundary(3, 3, 6, 0);
    }

    @Test
    public void shouldFind2PointsInsideComplexBoundaryOnBorder() {
        boundaryServiceDefaultBDD.
            givenComplexRegion().
            andAPoint(1, 4).
            whenGetBoundariesByPoint().
            thenShouldBeExistExactlyNBoundaries(2).
            thenShouldHaveThisBoundary(0, 5, 2, 3).
            thenShouldHaveThisBoundary(1, 4, 4, 2);
    }

    @Test
    public void shouldFind3PointsInsideComplexBoundary() {
        boundaryServiceDefaultBDD.
            givenComplexRegion().
            andAPoint(4, 3).
            whenGetBoundariesByPoint().
            thenShouldBeExistExactlyNBoundaries(4).
            thenShouldHaveThisBoundary(1, 4, 4, 2).
            thenShouldHaveThisBoundary(2, 3, 4, 0).
            thenShouldHaveThisBoundary(2, 5, 6, 3).
            thenShouldHaveThisBoundary(3, 3, 6, 0);
    }

    @Test
    public void shouldFind5PointsInsideMultiBoundaries() {
        boundaryServiceDefaultBDD.
            givenComplexRegion().
            andAPoint(2, 3).
            whenGetBoundariesByPoint().
            thenShouldBeExistExactlyNBoundaries(5).
            thenShouldHaveThisBoundary(0, 3, 2, 0).
            thenShouldHaveThisBoundary(0, 5, 2, 3).
            thenShouldHaveThisBoundary(1, 4, 4, 2).
            thenShouldHaveThisBoundary(2, 3, 4, 0).
            thenShouldHaveThisBoundary(2, 5, 6, 3);
    }

    @Test
    public void shouldFind4PointsInsideMultiBoundaries() {
        boundaryServiceDefaultBDD.
            givenComplexRegion().
            andAPoint(3, 3).
            whenGetBoundariesByPoint().
            thenShouldBeExistExactlyNBoundaries(4).
            thenShouldHaveThisBoundary(1, 4, 4, 2).
            thenShouldHaveThisBoundary(2, 3, 4, 0).
            thenShouldHaveThisBoundary(2, 5, 6, 3).
            thenShouldHaveThisBoundary(3, 3, 6, 0);
    }

}
