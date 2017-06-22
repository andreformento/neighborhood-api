package com.formento.neighborhood.validation.impl;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.formento.neighborhood.model.Property;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.Collections;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PropertyValidationDefaultTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @InjectMocks
    private PropertyValidationDefault propertyValidationDefault;

    @Mock
    private Validator validator;

    @Mock
    private Property property;

    @Test
    public void shouldValidateAndThrowViolations() {
        // given
        final Set build = ImmutableSet.builder().add(mock(ConstraintViolation.class)).build();

        // expected exception
        expectedException.expect(ConstraintViolationException.class);

        // when
        when(validator.validate(any(Property.class))).thenReturn(build);
        propertyValidationDefault.validateBeforeInsert(property);
    }

    @Test
    public void shouldValidate() {
        // given
        final Set build = Collections.emptySet();

        // when
        when(validator.validate(any(Property.class))).thenReturn(build);
        propertyValidationDefault.validateBeforeInsert(property);
    }

}
