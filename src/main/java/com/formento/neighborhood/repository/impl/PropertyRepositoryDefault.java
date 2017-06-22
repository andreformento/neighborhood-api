package com.formento.neighborhood.repository.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import com.formento.neighborhood.model.Property;
import com.formento.neighborhood.repository.PropertyRepository;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PropertyRepositoryDefault implements PropertyRepository {

    private final AtomicLong idGenerator;
    private final Map<Long, Property> properties;

    public PropertyRepositoryDefault(final Collection<Property> properties) {
        final long lastId = properties.
            stream().
            mapToLong(property -> property.getId().filter(id -> id > 0L).orElse(0L)).
            max().
            orElse(0);
        this.idGenerator = new AtomicLong(lastId + 1);

        this.properties = properties.
            stream().
            collect(Collectors.toMap(
                property -> getId(property.getId().filter(id -> id > 0L).orElse(0L)),
                Function.identity()
            ));
    }

    private Long getId(Long id) {
        if (id == null || id <= 0) {
            return idGenerator.getAndIncrement();
        } else {
            return id;
        }
    }

    @Override
    public Collection<Property> findAll() {
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

}
