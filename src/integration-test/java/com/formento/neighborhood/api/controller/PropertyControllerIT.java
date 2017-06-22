package com.formento.neighborhood.api.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.formento.neighborhood.model.Property;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertyControllerIT {

    @Autowired
    private WebApplicationContext context;
    private MockMvcRequestSpecification given;

    @Before
    public void init() {
        final MockMvc mvc = webAppContextSetup(context).build();
        given = RestAssuredMockMvc.given().mockMvc(mvc).accept(ContentType.JSON);
    }

    @Test
    public void shouldCreateProperty() {
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

        given.
            accept(ContentType.JSON).
            contentType(MediaType.APPLICATION_JSON_VALUE).
            body(json).
        when().
            post("/properties").
        then().
            statusCode(is(HttpStatus.CREATED.value())).
            extract().body().as(Property.class);
    }

}
