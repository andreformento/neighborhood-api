package com.formento.neighborhood.importation;

import com.formento.neighborhood.model.Property;
import java.io.IOException;
import java.util.Collection;

public interface PropertyLoader {

    Collection<Property> load() throws IOException;

}
