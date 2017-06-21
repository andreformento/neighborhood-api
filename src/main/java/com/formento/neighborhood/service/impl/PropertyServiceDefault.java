package com.formento.neighborhood.service.impl;

import com.formento.neighborhood.component.NodeFactory;
import com.formento.neighborhood.model.Boundary;
import com.formento.neighborhood.model.Node;
import com.formento.neighborhood.model.Property;
import com.formento.neighborhood.repository.PropertyRepository;
import com.formento.neighborhood.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PropertyServiceDefault implements PropertyService {

    private final Node root;

    @Autowired
    public PropertyServiceDefault(final NodeFactory nodeFactory, final PropertyRepository propertyRepository) {
        this.root = nodeFactory.createRoot(propertyRepository.findAll());
    }

    @Override
    public void addProperty(final Property property) {
        root.add(property);
    }

    @Override
    public Collection<Property> findPropertiesInsideBoundary(Boundary boundary) {
        return root.findPropertiesInsideBoundary(boundary);
    }

}
