package com.formento.neighborhood.service.impl;

import com.formento.neighborhood.component.NodeFactory;
import com.formento.neighborhood.infra.NeighborhoodNotFoundException;
import com.formento.neighborhood.model.Boundary;
import com.formento.neighborhood.model.Node;
import com.formento.neighborhood.model.Property;
import com.formento.neighborhood.model.Province;
import com.formento.neighborhood.repository.PropertyRepository;
import com.formento.neighborhood.service.PropertyService;
import com.formento.neighborhood.service.ProvinceService;
import com.formento.neighborhood.validation.PropertyValidation;
import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyServiceDefault implements PropertyService {

    private final Node root;
    private final PropertyRepository propertyRepository;
    private final ProvinceService provinceService;
    private final PropertyValidation propertyValidation;

    @Autowired
    public PropertyServiceDefault(final NodeFactory nodeFactory, final PropertyRepository propertyRepository, final ProvinceService provinceService, final PropertyValidation propertyValidation) {
        this.propertyRepository = propertyRepository;
        this.provinceService = provinceService;
        this.propertyValidation = propertyValidation;
        this.root = nodeFactory.createRoot(propertyRepository.findAll());
    }

    @Override
    public Property addProperty(@Valid final Property property) {
        final Collection<Province> provinces = provinceService.findByPoint(property.getPoint());
        final Property entity = new Property(property, provinces);
        propertyValidation.validateBeforeInsert(entity);

        return root.add(propertyRepository.insert(entity)).getValue();
    }

    @Override
    public Property findById(Long id) {
        return propertyRepository.findOne(id).orElseThrow(() -> new NeighborhoodNotFoundException("Id cannot found: " + id));
    }

    @Override
    public Collection<Property> findPropertiesInsideBoundary(Boundary boundary) {
        return root.findPropertiesInsideBoundary(boundary);
    }

}
