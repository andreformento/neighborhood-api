package com.formento.neighborhood.service.impl;

import com.formento.neighborhood.model.Point;
import com.formento.neighborhood.model.Rectangle;
import com.formento.neighborhood.repository.RectangleRepository;
import com.formento.neighborhood.service.RectangleService;
import com.google.common.collect.ImmutableList;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

class RectangleServiceDefaultBDD {

    private final RectangleService rectangleService;
    private final RectangleRepository rectangleRepository;

    private Point point;
    private Collection<Rectangle> rectanglesByPoint;

    public RectangleServiceDefaultBDD(RectangleService rectangleService, RectangleRepository rectangleRepository) {
        this.rectangleService = rectangleService;
        this.rectangleRepository = rectangleRepository;
    }

    public RectangleServiceDefaultBDD givenAValidRegion(final List<Rectangle> rectangles) {
        when(rectangleRepository.getAll()).thenReturn(rectangles);
        return this;
    }

    public RectangleServiceDefaultBDD givenSimpleRegion() {
        return givenAValidRegion(ImmutableList.<Rectangle>builder().
                add(new Rectangle(0, 6, 5, 1)).
                build()
        );
    }

    public RectangleServiceDefaultBDD givenComplexRegion() {
        return givenAValidRegion(ImmutableList.<Rectangle>builder().
                add(new Rectangle(0, 3, 2, 0)).
                add(new Rectangle(0, 5, 2, 3)).
                add(new Rectangle(1, 4, 4, 2)).
                add(new Rectangle(2, 3, 4, 0)).
                add(new Rectangle(2, 5, 6, 3)).
                add(new Rectangle(3, 3, 6, 0)).
                build()
        );
    }

    public RectangleServiceDefaultBDD andAPoint(final Integer x, final Integer y) {
        point = new Point(x, y);
        return this;
    }

    public RectangleServiceDefaultBDD whenGetRectanglesByPoint() {
        rectanglesByPoint = rectangleService.findRectanglesByPoint(point);
        return this;
    }

    public RectangleServiceDefaultBDD thenShouldBeExistExactlyNRectangles(final Integer quantityOfRectangles) {
        assertThat(rectanglesByPoint, is(notNullValue()));
        assertThat(rectanglesByPoint.size(), is(equalTo(quantityOfRectangles)));
        return this;
    }

    public RectangleServiceDefaultBDD thenShouldHaveThisRectangle(final Integer upperLeftX, final Integer upperLeftY, final Integer rightBottomX, final Integer rightBottomY) {
        return thenShouldHaveThisRectangle(new Rectangle(upperLeftX, upperLeftY, rightBottomX, rightBottomY));
    }

    public RectangleServiceDefaultBDD thenShouldHaveThisRectangle(final Rectangle rectangle) {
        assertThat(rectanglesByPoint.contains(rectangle), is(true));
        return this;
    }

}
