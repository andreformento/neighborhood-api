package com.formento.kdtree.model;

import java.util.Comparator;

public interface PointComparator extends Comparator<Point> {

    PointComparator getNextPointComparator();

}
