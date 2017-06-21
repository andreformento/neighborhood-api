package com.formento.neighborhood.importation;

import com.formento.neighborhood.model.Property;

import java.io.IOException;

public interface PropertyImport {

    Iterable<Property> doImport() throws IOException;

}
