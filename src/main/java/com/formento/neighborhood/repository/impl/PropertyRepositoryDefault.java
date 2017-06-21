package com.formento.neighborhood.repository.impl;

import com.formento.neighborhood.model.Property;
import com.formento.neighborhood.repository.PropertyRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

@Repository
public class PropertyRepositoryDefault implements PropertyRepository {

    private final AtomicLong idGenerator;
    private final Map<Long, Property> properties;

    public PropertyRepositoryDefault() {
        this.idGenerator = new AtomicLong();
        this.properties = new HashMap<>();
    }

    @Override
    public Iterable<Property> findAll() {
        return properties.values();
    }

    @Override
    public Long count() {
        return (long) properties.size();
    }

    @Override
    public Optional<Property> findOne(Long id) {
        return Optional.ofNullable(properties.get(id));
    }

    @Override
    public Property insert(Property entity) {
        checkNotNull(entity);

        final long id = idGenerator.getAndIncrement();

        return properties.put(id, new Property(Optional.of(id), entity));
    }

    @Override
    public Collection<Property> insert(Collection<Property> properties) {
        return properties.
                stream().
                map(this::insert).
                collect(Collectors.toList());
    }

}
