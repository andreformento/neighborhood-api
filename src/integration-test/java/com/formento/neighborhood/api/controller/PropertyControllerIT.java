package com.formento.neighborhood.api.controller;

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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class PropertyControllerIT {

    @Autowired
    private WebApplicationContext context;
    private MockMvcRequestSpecification given;

    @Before
    public void init() {
        final MockMvc mvc = webAppContextSetup(context).build();
        given = RestAssuredMockMvc.given().mockMvc(mvc).contentType(MediaType.APPLICATION_JSON_VALUE).accept(ContentType.JSON);
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
            body(json).
        when().
            post("/properties").
        then().
            statusCode(is(HttpStatus.CREATED.value())).
            content("id", equalTo(8001)).
            content("title", equalTo("Imóvel código 1, com 5 quartos e 4 banheiros")).
            content("price", equalTo(1250000)).
            content("x", equalTo(222)).
            content("y", equalTo(444)).
            content("beds", equalTo(4)).
            content("baths", equalTo(3)).
            content("squareMeters", equalTo(210)).
            content("provinces", contains("Scavy"));
    }

    @Test
    public void shouldNotCreatePropertyAndValidate() {
        final String json = "{\n"
                + "  \"x\": 15000,\n"
                + "  \"y\": 444,\n"
                + "  \"title\": \"\",\n"
                + "  \"price\": null,\n"
                + "  \"beds\": 0,\n"
                + "  \"baths\": 31,\n"
                + "  \"squareMeters\": 1\n"
                + "}";

        given.
            body(json).
        when().
            post("/properties").
        then().
            statusCode(is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void shouldFindById() {
        given.
        when().
            get("/properties/665").
        then().
            statusCode(is(HttpStatus.OK.value())).
            content("id", equalTo(665)).
            content("title", equalTo("Imóvel código 665, com 5 quartos e 4 banheiros.")).
            content("price", equalTo(1779000)).
            content("description", equalTo("Ex laborum sunt eiusmod esse commodo. Nulla laboris in adipisicing aliqua Lorem est laboris nulla dolore esse nostrud non deserunt.")).
            content("x", equalTo(1317)).
            content("y", equalTo(182)).
            content("beds", equalTo(5)).
            content("baths", equalTo(4)).
            content("squareMeters", equalTo(175)).
            content("provinces", contains("Nova"));
    }

    @Test
    public void shouldNotFindByIdWhenNotExists() {
        given.
        when().
            get("/properties/9999999").
        then().
            statusCode(is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    public void shouldFindByBoundary() {
        given.
            accept(ContentType.JSON).
            contentType(MediaType.APPLICATION_JSON_VALUE).
        when().
            get("/properties?ax=1300&ay=180&bx=1400&by=300").
        then().
            statusCode(is(HttpStatus.OK.value()));
    }

}
