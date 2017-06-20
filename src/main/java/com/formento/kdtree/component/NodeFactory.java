package com.formento.kdtree.component;

import com.formento.kdtree.model.Node;
import com.formento.kdtree.model.Point;
import java.util.Collection;
import java.util.Optional;

public interface NodeFactory {

    Node createRoot(final Collection<Point> points);

}
