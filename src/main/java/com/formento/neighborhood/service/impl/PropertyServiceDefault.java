package com.formento.neighborhood.service.impl;

import com.formento.neighborhood.component.NodeFactory;
import com.formento.neighborhood.model.Boundary;
import com.formento.neighborhood.model.Node;
import com.formento.neighborhood.model.Property;
import com.formento.neighborhood.repository.PropertyRepository;
import com.formento.neighborhood.service.PropertyService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyServiceDefault implements PropertyService {

    private final Node root;
    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyServiceDefault(final NodeFactory nodeFactory, PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
        this.root = nodeFactory.createRoot(propertyRepository.findAll());
    }

    @Override
    public Property addProperty(final Property property) {
        // get province - do not permit whiout province
        // validate property
        return root.add(propertyRepository.insert(property)).getValue();
    }

    @Override
    public Collection<Property> findPropertiesInsideBoundary(Boundary boundary) {
        return root.findPropertiesInsideBoundary(boundary);
    }

}
