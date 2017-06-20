package com.formento.neighborhood.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class PointTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Point.class).verify();
    }

    @Test
    public void shouldBeLessWhenXIsSmaller() {
        // given
        final Point point = new Point(1, 3);
        final Point other = new Point(2, 3);

        // when
        final Integer compareTo = point.compareToX(other);

        // then
        assertThat(compareTo, is(lessThan(0)));
    }

    @Test
    public void shouldBegGreaterWhenXIsBigger() {
        // given
        final Point point = new Point(2, 3);
        final Point other = new Point(1, 3);

        // when
        final Integer compareTo = point.compareToX(other);

        // then
        assertThat(compareTo, is(greaterThan(0)));
    }

    @Test
    public void shouldBeLessWhenYIsSmaller() {
        // given
        final Point point = new Point(3, 1);
        final Point other = new Point(3, 2);

        // when
        final Integer compareTo = point.compareToY(other);

        // then
        assertThat(compareTo, is(lessThan(0)));
    }

    @Test
    public void shouldBegGreaterWhenYIsBigger() {
        // given
        final Point point = new Point(3, 2);
        final Point other = new Point(3, 1);

        // when
        final Integer compareTo = point.compareToY(other);

        // then
        assertThat(compareTo, is(greaterThan(0)));
    }

    @Test
    public void shouldPrintPoint() {
        // given
        final int x = 1;
        final int y = 2;

        // when
        final Point point = new Point(x, y);

        // then
        assertThat(point, hasToString(equalTo("Point(x=1, y=2)")));
    }

}
