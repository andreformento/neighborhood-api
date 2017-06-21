package com.formento.neighborhood.model;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class BoundaryTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Boundary.class).verify();
    }

    @Test
    public void shouldBeInside() {
        // given
        final Boundary boundary = new Boundary(1, 7, 6, 2);
        final Point point = new Point(3, 4);

        // when
        final Boolean inside = boundary.containsPoint(point);

        // then
        assertThat(inside, is(TRUE));
    }

    @Test
    public void shouldBeInsideOnTheBorderXUpperLeft() {
        // given
        final Boundary boundary = new Boundary(1, 7, 6, 2);
        final Point point = new Point(1, 4);

        // when
        final Boolean inside = boundary.containsPoint(point);

        // then
        assertThat(inside, is(TRUE));
    }

    @Test
    public void shouldBeInsideOnTheBorderYUpperLeft() {
        // given
        final Boundary boundary = new Boundary(1, 7, 6, 2);
        final Point point = new Point(3, 2);

        // when
        final Boolean inside = boundary.containsPoint(point);

        // then
        assertThat(inside, is(TRUE));
    }

    @Test
    public void shouldBeInsideOnTheBorderXRightBottom() {
        // given
        final Boundary boundary = new Boundary(1, 7, 6, 2);
        final Point point = new Point(6, 2);

        // when
        final Boolean inside = boundary.containsPoint(point);

        // then
        assertThat(inside, is(TRUE));
    }

    @Test
    public void shouldBeInsideOnTheBorderYRightBottom() {
        // given
        final Boundary boundary = new Boundary(1, 7, 6, 2);
        final Point point = new Point(4, 7);

        // when
        final Boolean inside = boundary.containsPoint(point);

        // then
        assertThat(inside, is(TRUE));
    }

    @Test
    public void shouldNotBeInsideOnTheBorderXUpperLeft() {
        // given
        final Boundary boundary = new Boundary(1, 7, 6, 2);
        final Point point = new Point(0, 4);

        // when
        final Boolean inside = boundary.containsPoint(point);

        // then
        assertThat(inside, is(FALSE));
    }

    @Test
    public void shouldNotBeInsideOnTheBorderYUpperLeft() {
        // given
        final Boundary boundary = new Boundary(1, 7, 6, 2);
        final Point point = new Point(3, 1);

        // when
        final Boolean inside = boundary.containsPoint(point);

        // then
        assertThat(inside, is(FALSE));
    }

    @Test
    public void shouldNotBeInsideOnTheBorderXRightBottom() {
        // given
        final Boundary boundary = new Boundary(1, 7, 6, 2);
        final Point point = new Point(7, 3);

        // when
        final Boolean inside = boundary.containsPoint(point);

        // then
        assertThat(inside, is(FALSE));
    }

    @Test
    public void shouldNotBeInsideOnTheBorderYRightBottom() {
        // given
        final Boundary boundary = new Boundary(1, 7, 6, 2);
        final Point point = new Point(5, 8);

        // when
        final Boolean inside = boundary.containsPoint(point);

        // then
        assertThat(inside, is(FALSE));
    }

    @Test
    public void shouldPrintBoundary() {
        // given
        final Boundary boundary = new Boundary(4, 0, 6, 3);

        // when
        final String printedBoundary = boundary.toString();

        // then
        assertThat(printedBoundary, is(equalTo("Boundary(upperLeft=Point(x=4, y=0), rightBottom=Point(x=6, y=3))")));
    }

}
