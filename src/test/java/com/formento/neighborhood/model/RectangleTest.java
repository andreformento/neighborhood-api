package com.formento.neighborhood.model;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class RectangleTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Rectangle.class).verify();
    }

    @Test
    public void shouldBeInside() {
        // given
        final Rectangle rectangle = new Rectangle(1, 2, 6, 7);
        final Point point = new Point(3, 4);

        // when
        final Boolean inside = rectangle.containsPoint(point);

        // then
        assertThat(inside, is(TRUE));
    }

    @Test
    public void shouldBeInsideOnTheBorderXLeftBottom() {
        // given
        final Rectangle rectangle = new Rectangle(1, 2, 6, 7);
        final Point point = new Point(1, 4);

        // when
        final Boolean inside = rectangle.containsPoint(point);

        // then
        assertThat(inside, is(TRUE));
    }

    @Test
    public void shouldBeInsideOnTheBorderYLeftBottom() {
        // given
        final Rectangle rectangle = new Rectangle(1, 2, 6, 7);
        final Point point = new Point(3, 2);

        // when
        final Boolean inside = rectangle.containsPoint(point);

        // then
        assertThat(inside, is(TRUE));
    }

    @Test
    public void shouldBeInsideOnTheBorderXRightTop() {
        // given
        final Rectangle rectangle = new Rectangle(1, 2, 6, 7);
        final Point point = new Point(6, 2);

        // when
        final Boolean inside = rectangle.containsPoint(point);

        // then
        assertThat(inside, is(TRUE));
    }

    @Test
    public void shouldBeInsideOnTheBorderYRightTop() {
        // given
        final Rectangle rectangle = new Rectangle(1, 2, 6, 7);
        final Point point = new Point(4, 7);

        // when
        final Boolean inside = rectangle.containsPoint(point);

        // then
        assertThat(inside, is(TRUE));
    }

    @Test
    public void shouldNotBeInsideOnTheBorderXLeftBottom() {
        // given
        final Rectangle rectangle = new Rectangle(1, 2, 6, 7);
        final Point point = new Point(0, 4);

        // when
        final Boolean inside = rectangle.containsPoint(point);

        // then
        assertThat(inside, is(FALSE));
    }

    @Test
    public void shouldNotBeInsideOnTheBorderYLeftBottom() {
        // given
        final Rectangle rectangle = new Rectangle(1, 2, 6, 7);
        final Point point = new Point(3, 1);

        // when
        final Boolean inside = rectangle.containsPoint(point);

        // then
        assertThat(inside, is(FALSE));
    }

    @Test
    public void shouldNotBeInsideOnTheBorderXRightTop() {
        // given
        final Rectangle rectangle = new Rectangle(1, 2, 6, 7);
        final Point point = new Point(7, 3);

        // when
        final Boolean inside = rectangle.containsPoint(point);

        // then
        assertThat(inside, is(FALSE));
    }

    @Test
    public void shouldNotBeInsideOnTheBorderYRightTop() {
        // given
        final Rectangle rectangle = new Rectangle(1, 2, 6, 7);
        final Point point = new Point(5, 8);

        // when
        final Boolean inside = rectangle.containsPoint(point);

        // then
        assertThat(inside, is(FALSE));
    }

    @Test
    public void shouldPrintRectangle() {
        // given
        final Rectangle rectangle = new Rectangle(4, 0, 6, 3);

        // when
        final String printedRectangle = rectangle.toString();

        // then
        assertThat(printedRectangle, is(equalTo("Rectangle(leftBottom=Point(x=4, y=0), rightTop=Point(x=6, y=3))")));
    }

}
