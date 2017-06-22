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

    private static final Point point = new Point(1, 2);
    private static final String json = "{\"x\": 1, \"y\": 2}";

    @Test
    public void shoudSerialize() throws JsonProcessingException {
        // when
        final String result = objectMapper.writeValueAsString(point);

        // then
        assertThatJson(result).isEqualTo(json);
    }

    @Test
    public void shoudDeserialize() throws IOException {
        // when
        final Point result = objectMapper.readValue(json, Point.class);

        // then
        assertThat(result).isEqualTo(point);
    }

}
