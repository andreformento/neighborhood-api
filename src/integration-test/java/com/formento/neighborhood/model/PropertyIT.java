package com.formento.neighborhood.model;

import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertyIT {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shoudSerialize() throws JsonProcessingException {
        // given
        final Property property = new Property(
            789L,
            "Property title",
            190000,
            "Property description",
            new Point(125, 248),
            (short) 5,
            (short) 2,
            8795
        );
        final String json = "{\n"
            + "    \"id\": 789,\n"
            + "    \"title\": \"Property title\",\n"
            + "    \"price\": 190000,\n"
            + "    \"description\": \"Property description\",\n"
            + "    \"x\": 125,\n"
            + "    \"y\": 248,\n"
            + "    \"beds\": 5,\n"
            + "    \"baths\": 2,\n"
            + "    \"squareMeters\": 8795\n"
            + "}";

        // when
        final String result = objectMapper.writeValueAsString(property);

        // then
        assertThatJson(result).isEqualTo(json);
    }

    @Test
    public void shoudDeserialize() throws IOException {
        // given
        final Property property = new Property(
            789L,
            "Property title",
            190000,
            "Property description",
            new Point(125, 248),
            (short) 5,
            (short) 2,
            8795
        );

        final String json = "{\n"
            + "    \"id\": 789,\n"
            + "    \"title\": \"Property title\",\n"
            + "    \"price\": 190000,\n"
            + "    \"description\": \"Property description\",\n"
            + "    \"x\": 125,\n"
            + "    \"y\": 248,\n"
            + "    \"beds\": 5,\n"
            + "    \"baths\": 2,\n"
            + "    \"squareMeters\": 8795\n"
            + "}";

        // when
        final Property result = objectMapper.readValue(json, Property.class);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(property.getId());
        assertThat(result.getTitle()).isEqualTo(property.getTitle());
        assertThat(result.getPrice()).isEqualTo(property.getPrice());
        assertThat(result.getDescription()).isEqualTo(property.getDescription());
        assertThat(result.getPoint()).isEqualTo(property.getPoint());
        assertThat(result.getBeds()).isEqualTo(property.getBeds());
        assertThat(result.getBaths()).isEqualTo(property.getBaths());
        assertThat(result.getSquareMeters()).isEqualTo(property.getSquareMeters());
    }

}
