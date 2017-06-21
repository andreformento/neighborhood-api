package com.formento.neighborhood.component.impl;

import com.formento.neighborhood.component.NodeFactory;
import com.formento.neighborhood.validation.KdtreeDuplicationPointException;
import com.formento.neighborhood.model.Node;
import com.formento.neighborhood.model.Point;
import com.formento.neighborhood.model.PointComparatorX;
import com.formento.neighborhood.model.PointComparatorY;
import com.google.common.collect.ImmutableList;
import org.assertj.core.api.OptionalAssert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

public class NodeFactoryDefaultTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private NodeFactory nodeFactory = new NodeFactoryDefault();

    /*
    * ( 3,  6), (17, 15), (13, 15), ( 6, 12), ( 9,  1), ( 2,  7), (10, 19) original
    *
    * level 0
    * ( 2,  7), ( 3,  6), ( 6, 12), ( 9,  1), (10, 19), (13, 15), (17, 15) x
    *
    * level 1
    *                               ( 9,  1)                               x
    * ( 3,  6), ( 2,  7), ( 6, 12)            (13, 15), (17, 15), (10, 19) y
    *
    * level 2
    *                               ( 9,  1)                               x
    *           ( 2,  7)                                (17, 15)           y
    * ( 3,  6)            ( 6, 12)            (13, 15)            (10, 19) x
    * */
    @Test
    public void shouldGenerateBalancedOddTree() {
        // given
        final Point point_3_6 = new Point(3, 6);
        final Point point_17_15 = new Point(17, 15);
        final Point point_13_15 = new Point(13, 15);
        final Point point_6_12 = new Point(6, 12);
        final Point point_9_1 = new Point(9, 1);
        final Point point_2_7 = new Point(2, 7);
        final Point point_10_19 = new Point(10, 19);

        final List<Point> points = ImmutableList.<Point>builder().
                add(point_3_6).
                add(point_17_15).
                add(point_13_15).
                add(point_6_12).
                add(point_9_1).
                add(point_2_7).
                add(point_10_19).

                build();

        // when
        final Node root = nodeFactory.createRoot(points);

        // then
        assertThat(root.getValue()).isEqualTo(point_9_1);
        assertThat(root.getPointComparator()).isInstanceOf(PointComparatorX.class);

        final OptionalAssert<Node> leftLevel1 = assertThat(root.getLeft());
        leftLevel1.map(Node::getPointComparator).containsInstanceOf(PointComparatorY.class);
        leftLevel1.map(Node::getValue).hasValue(point_2_7);
        leftLevel1.map(Node::getLeft).map(Optional::get).map(Node::getValue).hasValue(point_3_6);
        leftLevel1.map(Node::getLeft).map(Optional::get).map(Node::getPointComparator).containsInstanceOf(PointComparatorX.class);
        leftLevel1.map(Node::getRight).map(Optional::get).map(Node::getValue).hasValue(point_6_12);
        leftLevel1.map(Node::getRight).map(Optional::get).map(Node::getPointComparator).containsInstanceOf(PointComparatorX.class);

        final OptionalAssert<Node> rightLevel1 = assertThat(root.getRight());
        rightLevel1.map(Node::getPointComparator).containsInstanceOf(PointComparatorY.class);
        rightLevel1.map(Node::getValue).hasValue(point_17_15);
        rightLevel1.map(Node::getLeft).map(Optional::get).map(Node::getValue).hasValue(point_13_15);
        rightLevel1.map(Node::getLeft).map(Optional::get).map(Node::getPointComparator).containsInstanceOf(PointComparatorX.class);
        rightLevel1.map(Node::getRight).map(Optional::get).map(Node::getValue).hasValue(point_10_19);
        rightLevel1.map(Node::getRight).map(Optional::get).map(Node::getPointComparator).containsInstanceOf(PointComparatorX.class);
    }

    /*
    * ( 3,  6), (17, 15), (13, 15), ( 9,  1), ( 2,  7), (10, 19) original
    *
    * level 0
    * ( 2,  7), ( 3,  6), ( 9,  1), (10, 19), (13, 15), (17, 15) x
    *
    * level 1
    *                               (10, 19)                     x
    * ( 9,  1), ( 3,  6), ( 2,  7)            (13, 15), (17, 15) y
    *
    * level 2
    *                               (10, 19)                     x
    *           ( 3,  6)                                (17, 15) y
    * ( 9,  1)            ( 2,  7)            (13, 15)           x
    * */
    @Test
    public void shouldGenerateBalancedPairTree() {
        // given
        final Point point_3_6 = new Point(3, 6);
        final Point point_17_15 = new Point(17, 15);
        final Point point_13_15 = new Point(13, 15);
        final Point point_9_1 = new Point(9, 1);
        final Point point_2_7 = new Point(2, 7);
        final Point point_10_19 = new Point(10, 19);

        final List<Point> points = ImmutableList.<Point>builder().
                add(point_3_6).
                add(point_17_15).
                add(point_13_15).
                add(point_9_1).
                add(point_2_7).
                add(point_10_19).

                build();

        // when
        final Node root = nodeFactory.createRoot(points);

        // then
        assertThat(root.getValue()).isEqualTo(point_10_19);
        assertThat(root.getPointComparator()).isInstanceOf(PointComparatorX.class);

        final OptionalAssert<Node> leftLevel1 = assertThat(root.getLeft());
        leftLevel1.map(Node::getValue).hasValue(point_3_6);
        leftLevel1.map(Node::getPointComparator).containsInstanceOf(PointComparatorY.class);
        leftLevel1.map(Node::getLeft).map(Optional::get).map(Node::getValue).hasValue(point_9_1);
        leftLevel1.map(Node::getLeft).map(Optional::get).map(Node::getPointComparator).containsInstanceOf(PointComparatorX.class);
        leftLevel1.map(Node::getRight).map(Optional::get).map(Node::getValue).hasValue(point_2_7);
        leftLevel1.map(Node::getRight).map(Optional::get).map(Node::getPointComparator).containsInstanceOf(PointComparatorX.class);

        final OptionalAssert<Node> rightLevel1 = assertThat(root.getRight());
        rightLevel1.map(Node::getValue).hasValue(point_17_15);
        rightLevel1.map(Node::getPointComparator).containsInstanceOf(PointComparatorY.class);
        rightLevel1.map(Node::getLeft).map(Optional::get).map(Node::getValue).hasValue(point_13_15);
        rightLevel1.map(Node::getLeft).map(Optional::get).map(Node::getPointComparator).containsInstanceOf(PointComparatorX.class);
    }

    @Test
    public void shouldNotGenerateWithEmptyList() {
        // given
        final List<Point> points = emptyList();

        // expected
        expectedException.expect(KdtreeDuplicationPointException.class);
        expectedException.expectMessage("List of points cannot be empty");

        // when
        nodeFactory.createRoot(points);
    }

    @Test
    public void shouldNotGenerateWithDuplication() {
        // given
        final Point pointA = new Point(3, 6);
        final Point pointB = new Point(3, 6);

        final List<Point> points = ImmutableList.<Point>builder().
                add(pointA).
                add(pointB).
                build();

        // expected
        expectedException.expect(KdtreeDuplicationPointException.class);
        expectedException.expectMessage("It is not possible insert duplicated points: Point(x=3, y=6)");

        // when
        nodeFactory.createRoot(points);
    }

}
