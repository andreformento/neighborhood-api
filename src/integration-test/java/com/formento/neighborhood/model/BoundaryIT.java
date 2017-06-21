package com.formento.neighborhood.model;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

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

    @Test
    public void shoudSerialize() throws JsonProcessingException {
        // given
        final Boundary boundary = new Boundary(new Point(1, 2),new Point(3, 4));
        final String json = "{\"upperLeft\": {\"x\": 1, \"y\": 2}, \"rightBottom\": {\"x\": 3, \"y\": 4}}";

        // when
        final String result = objectMapper.writeValueAsString(boundary);

        // then
        assertThatJson(result).isEqualTo(json);
    }

    @Test
    public void shoudDeserialize() throws IOException {
        // given
        final Boundary boundary = new Boundary(new Point(1, 2),new Point(3, 4));
        final String json = "{\"upperLeft\": {\"x\": 1, \"y\": 2}, \"rightBottom\": {\"x\": 3, \"y\": 4}}";

        // when
        final Boundary result = objectMapper.readValue(json, Boundary.class);

        // then
        assertThat(result).isEqualTo(boundary);
    }

}
