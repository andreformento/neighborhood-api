package com.formento.neighborhood.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class ProvincesSerializer extends JsonSerializer<Iterable<Province>> {

    @Override
    public void serialize(Iterable<Province> provinces, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        jsonGenerator.writeStartArray();

        for (Province province : provinces) {
            jsonGenerator.writeString(province.getDescription());
        }

        jsonGenerator.writeEndArray();
    }

}
