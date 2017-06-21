package com.formento.neighborhood.component.impl;

import com.formento.neighborhood.component.NodeFactory;
import com.formento.neighborhood.model.*;
import com.formento.neighborhood.validation.NeighborhoodDuplicationException;
import com.formento.neighborhood.validation.DuplicatedPointValidator;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class NodeFactoryDefault implements NodeFactory {

    @Override
    public Node createRoot(final Collection<Property> properties) {
        if (properties.isEmpty()) {
            throw new NeighborhoodDuplicationException("List of points cannot be empty");
        }

        return createRoot(properties, PropertyComparatorX.getInstance());
    }

    private Node createRoot(final Collection<Property> properties, final PropertyComparator propertyComparator) {
        final List<Property> sortedProperties = ImmutableList.sortedCopyOf(propertyComparator, properties);
        final Integer middle = sortedProperties.size() / 2;

        final PropertyComparator nextPropertyComparator = propertyComparator.getNextPropertyComparator();
        final Optional<Node> left = getLeftNode(sortedProperties, nextPropertyComparator, middle);
        final Optional<Node> right = getRightNode(sortedProperties, nextPropertyComparator, middle);

        final Property value = sortedProperties.get(middle);

        final DuplicatedPointValidator duplicatedPropertyValidator = new DuplicatedPointValidator(value.getPoint());
        left.map(Node::getValue).ifPresent(duplicatedPropertyValidator::validate);
        right.map(Node::getValue).ifPresent(duplicatedPropertyValidator::validate);

        return new Node(value, left, right, propertyComparator);
    }

    private Optional<Node> getLeftNode(final List<Property> allProperties, final PropertyComparator nextPropertyComparator, final Integer middle) {
        if (middle >= 1) {
            final int fromIndex = 0;
            return Optional.of(createRoot(allProperties.subList(fromIndex, middle), nextPropertyComparator));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Node> getRightNode(final List<Property> allProperties, final PropertyComparator nextPropertyComparator, final Integer middle) {
        final int fromIndex = middle + 1;
        final int toIndex = allProperties.size();
        if (fromIndex < toIndex) {
            return Optional.of(createRoot(allProperties.subList(fromIndex, toIndex), nextPropertyComparator));
        } else {
            return Optional.empty();
        }
    }

}
