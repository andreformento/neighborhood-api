package com.formento.neighborhood.model;

import com.formento.neighborhood.component.NodeFactory;
import com.formento.neighborhood.component.impl.NodeFactoryDefault;
import com.formento.neighborhood.validation.KdtreeDuplicationPointException;
import com.google.common.collect.ImmutableList;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class NodeTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private NodeFactory nodeFactory = new NodeFactoryDefault();
    private Node root;

    /* tree
    *                               (10, 19)                              x
    *           ( 3,  6)                                (17, 15)          y
    * ( 9,  1)            ( 2,  7)            (13, 15)                    x
    */
    @Before
    public void init() {
        final List<Point> points = ImmutableList.<Point>builder().
                add(new Point(3, 6)).
                add(new Point(17, 15)).
                add(new Point(13, 15)).
                add(new Point(9, 1)).
                add(new Point(2, 7)).
                add(new Point(10, 19)).
                build();

        // when
        root = nodeFactory.createRoot(points);
    }

    /*
    * after insert                                                        
    *                               (10, 19)                              x
    *           ( 3,  6)                                (17, 15)          y
    * ( 9,  1)            ( 2,  7)            (13, 15)           (11, 16) x
    * */
    @Test
    public void shouldInsertPointIntoTree() {
        // given
        final Point point_11_16 = new Point(11, 16);

        // when
        root.add(point_11_16);

        // then
        Assertions.
                assertThat(root.getRight()).
                map(Node::getRight).
                map(Optional::get).
                map(Node::getValue).
                hasValue(point_11_16);
    }

    @Test
    public void shouldFindPointsInsideBoundary() {
        // given
        final Point point_17_15 = new Point(17, 15);
        final Point point_13_15 = new Point(13, 15);
        final Point point_10_19 = new Point(10, 19);

        final Boundary boundary = new Boundary(10, 19, 17, 14);

        // when
        final Collection<Point> points = root.findPointsInsideBoundary(boundary);

        // then
        assertThat(points, containsInAnyOrder(equalTo(point_10_19), equalTo(point_13_15), equalTo(point_17_15)));
    }

    @Test
    public void shouldFindPointsInsideBoundaryAfterInsertNew() {
        // given
        final Point point_17_15 = new Point(17, 15);
        final Point point_13_15 = new Point(13, 15);
        final Point point_10_19 = new Point(10, 19);
        final Point point_12_14 = new Point(12, 14);

        final Boundary boundary = new Boundary(10, 19, 17, 14);

        // when
        root.add(point_12_14);
        final Collection<Point> points = root.findPointsInsideBoundary(boundary);

        // then
        assertThat(points, containsInAnyOrder(equalTo(point_10_19), equalTo(point_12_14), equalTo(point_13_15), equalTo(point_17_15)));
    }

    @Test
    public void shouldFailOnAddDuplicatePoint() {
        // given
        final Point point_3_6 = new Point(3, 6);

        // expected
        expectedException.expect(KdtreeDuplicationPointException.class);
        expectedException.expectMessage("It is not possible insert duplicated points: " + point_3_6.toString());

        // when
        root.add(point_3_6);
    }

}
