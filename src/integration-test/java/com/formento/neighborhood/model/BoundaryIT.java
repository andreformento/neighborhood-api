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
public class BoundaryIT {

    @Autowired
    private ObjectMapper objectMapper;

    private static final Boundary boundary = new Boundary(new Point(1, 2), new Point(3, 4));
    private static final String json = "{\"upperLeft\": {\"x\": 1, \"y\": 2}, \"rightBottom\": {\"x\": 3, \"y\": 4}}";

    @Test
    public void shoudSerialize() throws JsonProcessingException {
        // when
        final String result = objectMapper.writeValueAsString(boundary);

        // then
        assertThatJson(result).isEqualTo(json);
    }

    @Test
    public void shoudDeserialize() throws IOException {
        // when
        final Boundary result = objectMapper.readValue(json, Boundary.class);

        // then
        assertThat(result).isEqualTo(boundary);
    }

}
