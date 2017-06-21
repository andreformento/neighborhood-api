package com.formento.neighborhood.service.impl;

import com.formento.neighborhood.repository.RectangleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RectangleServiceDefaultTest {

    @InjectMocks
    private RectangleServiceDefault rectangleService;
    @Mock
    private RectangleRepository rectangleRepository;

    private RectangleServiceDefaultBDD rectangleServiceDefaultBDD;

    @Before
    public void init() {
        rectangleServiceDefaultBDD = new RectangleServiceDefaultBDD(rectangleService, rectangleRepository);
    }

    @Test
    public void shouldFindPointInsideSimpleRectangle() {
        rectangleServiceDefaultBDD.
                givenSimpleRegion().
                andAPoint(2, 3).
                whenGetRectanglesByPoint().
                thenShouldBeExistExactlyNRectangles(1).
                thenShouldHaveThisRectangle(0, 6, 5, 1);
    }

    @Test
    public void shouldFindPointInsideComplexRectangle() {
        rectangleServiceDefaultBDD.
                givenComplexRegion().
                andAPoint(5, 2).
                whenGetRectanglesByPoint().whenGetRectanglesByPoint().
                thenShouldBeExistExactlyNRectangles(1).
                thenShouldHaveThisRectangle(3, 3, 6, 0);
    }

    @Test
    public void shouldFindPointInsideComplexRectangleOnUpperLeftBorder() {
        rectangleServiceDefaultBDD.
                givenComplexRegion().
                andAPoint(0, 0).
                whenGetRectanglesByPoint().whenGetRectanglesByPoint().
                thenShouldBeExistExactlyNRectangles(1).
                thenShouldHaveThisRectangle(0, 3, 2, 0);
    }

    @Test
    public void shouldNotFindPointOutsideRectangle() {
        rectangleServiceDefaultBDD.
                givenComplexRegion().
                andAPoint(6, 6).
                whenGetRectanglesByPoint().
                thenShouldBeExistExactlyNRectangles(0);
    }

    @Test
    public void shouldFindPointInsideComplexRectangleOnRightBottomBorder() {
        rectangleServiceDefaultBDD.
                givenComplexRegion().
                andAPoint(6, 5).
                whenGetRectanglesByPoint().
                thenShouldBeExistExactlyNRectangles(1).
                thenShouldHaveThisRectangle(2, 5, 6, 3);
    }

    @Test
    public void shouldFind2PointsInsideComplexRectangle() {
        rectangleServiceDefaultBDD.
                givenComplexRegion().
                andAPoint(3, 1).
                whenGetRectanglesByPoint().
                thenShouldBeExistExactlyNRectangles(2).
                thenShouldHaveThisRectangle(2, 3, 4, 0).
                thenShouldHaveThisRectangle(3, 3, 6, 0);
    }

    @Test
    public void shouldFind2PointsInsideComplexRectangleOnBorder() {
        rectangleServiceDefaultBDD.
                givenComplexRegion().
                andAPoint(1, 4).
                whenGetRectanglesByPoint().
                thenShouldBeExistExactlyNRectangles(2).
                thenShouldHaveThisRectangle(0, 5, 2, 3).
                thenShouldHaveThisRectangle(1, 4, 4, 2);
    }

    @Test
    public void shouldFind3PointsInsideComplexRectangle() {
        rectangleServiceDefaultBDD.
                givenComplexRegion().
                andAPoint(4, 3).
                whenGetRectanglesByPoint().
                thenShouldBeExistExactlyNRectangles(4).
                thenShouldHaveThisRectangle(1, 4, 4, 2).
                thenShouldHaveThisRectangle(2, 3, 4, 0).
                thenShouldHaveThisRectangle(2, 5, 6, 3).
                thenShouldHaveThisRectangle(3, 3, 6, 0);
    }

    @Test
    public void shouldFind5PointsInsideMultiRectangles() {
        rectangleServiceDefaultBDD.
                givenComplexRegion().
                andAPoint(2, 3).
                whenGetRectanglesByPoint().
                thenShouldBeExistExactlyNRectangles(5).
                thenShouldHaveThisRectangle(0, 3, 2, 0).
                thenShouldHaveThisRectangle(0, 5, 2, 3).
                thenShouldHaveThisRectangle(1, 4, 4, 2).
                thenShouldHaveThisRectangle(2, 3, 4, 0).
                thenShouldHaveThisRectangle(2, 5, 6, 3);
    }

    @Test
    public void shouldFind4PointsInsideMultiRectangles() {
        rectangleServiceDefaultBDD.
                givenComplexRegion().
                andAPoint(3, 3).
                whenGetRectanglesByPoint().
                thenShouldBeExistExactlyNRectangles(4).
                thenShouldHaveThisRectangle(1, 4, 4, 2).
                thenShouldHaveThisRectangle(2, 3, 4, 0).
                thenShouldHaveThisRectangle(2, 5, 6, 3).
                thenShouldHaveThisRectangle(3, 3, 6, 0);
    }

}
