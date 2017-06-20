package com.formento.neighborhood.model;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import com.formento.neighborhood.component.NodeFactory;
import com.formento.neighborhood.component.impl.NodeFactoryDefault;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class NodeTest {

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
    public void shouldFindPointsInsideRectangle() {
        // given
        final Point point_17_15 = new Point(17, 15);
        final Point point_13_15 = new Point(13, 15);
        final Point point_10_19 = new Point(10, 19);

        final Rectangle rectangle = new Rectangle(10, 14, 17, 19);

        // when
        final Collection<Point> points = root.findPointsInsideRectangle(rectangle);

        // then
        assertThat(points, containsInAnyOrder(equalTo(point_10_19), equalTo(point_13_15), equalTo(point_17_15)));
    }

    @Test
    public void shouldFindPointsInsideRectangleAfterInsertNew() {
        // given
        final Point point_17_15 = new Point(17, 15);
        final Point point_13_15 = new Point(13, 15);
        final Point point_10_19 = new Point(10, 19);
        final Point point_12_14 = new Point(12, 14);

        final Rectangle rectangle = new Rectangle(10, 14, 17, 19);

        // when
        root.add(point_12_14);
        final Collection<Point> points = root.findPointsInsideRectangle(rectangle);

        // then
        assertThat(points, containsInAnyOrder(equalTo(point_10_19), equalTo(point_12_14), equalTo(point_13_15), equalTo(point_17_15)));
    }

}
