package com.formento.neighborhood.component;

import com.formento.neighborhood.model.Node;
import com.formento.neighborhood.model.Property;

import java.util.Collection;

public interface NodeFactory {

    Node createRoot(final Collection<Property> properties);

}
