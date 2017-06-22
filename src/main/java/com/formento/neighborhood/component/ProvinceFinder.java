package com.formento.neighborhood.component;

import com.formento.neighborhood.model.Point;
import com.formento.neighborhood.model.Province;
import java.util.Collection;

public interface ProvinceFinder {

    Collection<Province> find(Collection<Province> provinces, Point point);

}
