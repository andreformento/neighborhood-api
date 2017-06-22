package com.formento.neighborhood.service.impl;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.formento.neighborhood.component.NodeFactory;
import com.formento.neighborhood.exception.NeighborhoodNotFoundException;
import com.formento.neighborhood.model.Node;
import com.formento.neighborhood.model.Point;
import com.formento.neighborhood.model.Property;
import com.formento.neighborhood.repository.PropertyRepository;
import com.formento.neighborhood.service.ProvinceService;
import com.formento.neighborhood.validation.PropertyValidation;
import java.util.Optional;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PropertyServiceDefaultTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private PropertyServiceDefault propertyServiceDefault;
    @Mock
    private NodeFactory nodeFactory;
    @Mock
    private PropertyRepository propertyRepository;
    @Mock
    private ProvinceService provinceService;
    @Mock
    private PropertyValidation propertyValidation;
    @Mock
    private Node node;

    @Before
    public void init() {
        when(nodeFactory.createRoot(propertyRepository.findAll())).thenReturn(node);
        this.propertyServiceDefault = new PropertyServiceDefault(nodeFactory, propertyRepository, provinceService, propertyValidation);
    }

    @Test
    public void shouldFindPropertyById() {
        // given
        final long id = 1L;
        final Optional<Property> property = Optional
            .of(new Property(Optional.of(id), "title", 123, "description", new Point(1, 2),  10,  11, 321));

        // when
        when(propertyRepository.findOne(id)).thenReturn(property);
        final Property result = propertyServiceDefault.findById(id);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    public void shouldNotFindPropertyWhenIdDoNotExists() {
        // given
        final long id = 1L;

        // expected exception
        expectedException.expect(NeighborhoodNotFoundException.class);

        // when
        when(propertyRepository.findOne(id)).thenReturn(Optional.empty());
        propertyServiceDefault.findById(id);
    }

    @Test
    public void shouldInsertProperty() {
        // given
        final Property property = new Property(Optional.of(1L), "title", 123, "description", new Point(1, 2),  10,  11, 321);

        // when
        when(provinceService.findByPoint(property.getPoint())).thenReturn(emptyList());
        when(propertyRepository.insert(property)).thenReturn(property);
        when(node.add(any(Property.class))).thenReturn(node);
        when(node.getValue()).thenReturn(property);
        final Property result = propertyServiceDefault.create(property);

        // then
        assertThat(result).isNotNull();
        verify(provinceService, only()).findByPoint(property.getPoint());
        verify(propertyValidation, only()).validateBeforeInsert(any(Property.class));
    }

}
