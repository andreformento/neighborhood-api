package com.formento.neighborhood.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.formento.neighborhood.component.impl.NodeFactoryDefault;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NodeTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @InjectMocks
    private NodeFactoryDefault nodeFactory;
    private Node root;

    private Property mapFromPoint(Point point) {
        return new Property(Optional.empty(), "title", 123, "description", point,  10,  11, 321);
    }

    /* tree
    *                               (10, 19)                              x
    *           ( 3,  6)                                (17, 15)          y
    * ( 9,  1)            ( 2,  7)            (13, 15)                    x
    */
    @Before
    public void init() {
        final List<Property> properties = ImmutableList.<Point>builder().
            add(new Point(3, 6)).
            add(new Point(17, 15)).
            add(new Point(13, 15)).
            add(new Point(9, 1)).
            add(new Point(2, 7)).
            add(new Point(10, 19)).
            build().
            stream().
            map(this::mapFromPoint).
            collect(Collectors.toList());

        // when
        root = nodeFactory.createRoot(properties);
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
        root.add(mapFromPoint(point_11_16));

        // then
        assertThat(root.getRight()).
            map(Node::getRight).
            map(Optional::get).
            map(Node::getValue).
            map(Property::getPoint).
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
        final Collection<Point> points = root.findPropertiesInsideBoundary(boundary).stream().map(Property::getPoint).collect(Collectors.toList());

        // then
        assertThat(points).containsExactlyInAnyOrder(point_10_19, point_13_15, point_17_15);
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
        root.add(mapFromPoint(point_12_14));
        final Collection<Point> points = root.findPropertiesInsideBoundary(boundary).stream().map(Property::getPoint).collect(Collectors.toList());

        // then
        assertThat(points).containsExactlyInAnyOrder(point_10_19, point_12_14, point_13_15, point_17_15);

    }

    @Test
    public void shouldPermitDuplicatedPoint() {
        // given
        final Point pointA = new Point(3, 6);
        final Point pointB = new Point(3, 6);

        // when
        final Node simpleRoot = new Node(mapFromPoint(pointA), Optional.empty(), Optional.empty(), PropertyComparatorX.getInstance());
        simpleRoot.add(mapFromPoint(pointB));
        final Collection<Property> propertiesInsideBoundary = simpleRoot.findPropertiesInsideBoundary(new Boundary(pointA, pointB));

        // then
        assertThat(propertiesInsideBoundary).isNotNull();
        assertThat(propertiesInsideBoundary.stream().map(Property::getPoint)).containsExactly(pointA, pointB);
    }

}
