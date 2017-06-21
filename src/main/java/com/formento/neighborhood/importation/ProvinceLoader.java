package com.formento.neighborhood.importation;

import com.formento.neighborhood.model.Province;

import java.io.IOException;
import java.util.Collection;

public interface ProvinceLoader {

    Collection<Province> load() throws IOException;

}
