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
public class PointIT {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shoudSerializePoint() throws JsonProcessingException {
        // given
        final Point point = new Point(1, 2);
        final String json = "{\"x\": 1, \"y\": 2}";

        // when
        final String result = objectMapper.writeValueAsString(point);

        // then
        assertThatJson(result).isEqualTo(json);
    }

    @Test
    public void shoudDeserializePoint() throws IOException {
        // given
        final Point point = new Point(1, 2);
        final String json = "{\"x\": 1, \"y\": 2}";

        // when
        final Point result = objectMapper.readValue(json, Point.class);

        // then
        assertThat(result).isEqualTo(point);
    }

}
