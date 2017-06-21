package com.formento.neighborhood.model;

import java.util.Comparator;

public interface PropertyComparator extends Comparator<Property> {

    PropertyComparator getNextPropertyComparator();

}
