package com.formento.neighborhood.model;

import java.util.Comparator;

public interface PropertyComparator extends Comparator<Property> {

    int compare(Point var1, Point var2);

    PropertyComparator getNextPropertyComparator();

}
