package com.formento.neighborhood.model;

import static java.util.Optional.empty;
import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
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
        final Collection<Province> provinces = ImmutableList.<Province>builder().
            add(new Province("Scavy", null)).
            add(new Province("Gode", null)).
            build();

        final Property property = new Property(
            Optional.of(789L),
            "Imóvel código 1, com 5 quartos e 4 banheiros",
            1250000,
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            new Point(222, 444),
            (short) 4,
            (short) 3,
            210,
            provinces);
        final String json = "{\n"
            + "  \"id\": 789,\n"
            + "  \"x\": 222,\n"
            + "  \"y\": 444,\n"
            + "  \"title\": \"Imóvel código 1, com 5 quartos e 4 banheiros\",\n"
            + "  \"price\": 1250000,\n"
            + "  \"description\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit.\",\n"
            + "  \"beds\": 4,\n"
            + "  \"baths\": 3,\n"
            + "  \"squareMeters\": 210,\n"
            + "  \"provinces\" : [\"Scavy\", \"Gode\"]"
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
            empty(),
            "Imóvel código 1, com 5 quartos e 4 banheiros",
            1250000,
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            new Point(222, 444),
            (short) 4,
            (short) 3,
            210);
        final String json = "{\n"
            + "  \"x\": 222,\n"
            + "  \"y\": 444,\n"
            + "  \"title\": \"Imóvel código 1, com 5 quartos e 4 banheiros\",\n"
            + "  \"price\": 1250000,\n"
            + "  \"description\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit.\",\n"
            + "  \"beds\": 4,\n"
            + "  \"baths\": 3,\n"
            + "  \"squareMeters\": 210\n"
            + "}";

        // when
        final Property result = objectMapper.readValue(json, Property.class);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo(property.getTitle());
        assertThat(result.getPrice()).isEqualTo(property.getPrice());
        assertThat(result.getDescription()).isEqualTo(property.getDescription());
        assertThat(result.getPoint()).isEqualTo(property.getPoint());
        assertThat(result.getBeds()).isEqualTo(property.getBeds());
        assertThat(result.getBaths()).isEqualTo(property.getBaths());
        assertThat(result.getSquareMeters()).isEqualTo(property.getSquareMeters());
    }

}
