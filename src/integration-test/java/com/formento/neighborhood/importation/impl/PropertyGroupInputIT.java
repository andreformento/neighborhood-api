package com.formento.neighborhood.importation.impl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertyGroupInputIT {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shoudDeserialize() throws IOException {
        // given
        final String json = "{\n" +
            "  \"totalProperties\": 2,\n" +
            "  \"properties\": [\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"title\": \"Cod 1\",\n" +
            "      \"price\": 643000,\n" +
            "      \"description\": \"Laboris\",\n" +
            "      \"lat\": 1257,\n" +
            "      \"long\": 928,\n" +
            "      \"beds\": 3,\n" +
            "      \"baths\": 2,\n" +
            "      \"squareMeters\": 61\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 2,\n" +
            "      \"title\": \"Cod 2\",\n" +
            "      \"price\": 949000,\n" +
            "      \"description\": \"Anim\",\n" +
            "      \"lat\": 679,\n" +
            "      \"long\": 680,\n" +
            "      \"beds\": 4,\n" +
            "      \"baths\": 3,\n" +
            "      \"squareMeters\": 94\n" +
            "    }]\n" +
            "}";

        // when
        final PropertyGroupInput result = objectMapper.readValue(json, PropertyGroupInput.class);

        // then
        assertThat(result).isNotNull();
        assertThat(result.generateProperties()).
            isNotNull().
            asList().hasSize(2);
    }

}
