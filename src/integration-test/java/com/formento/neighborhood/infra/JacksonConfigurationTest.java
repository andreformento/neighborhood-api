package com.formento.neighborhood.infra;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JacksonConfigurationTest {

    @InjectMocks
    private JacksonConfiguration jacksonConfiguration;

    @Test
    public void shouldCreateInstance() {
        assertThat(jacksonConfiguration.objectMapper()).isNotNull();
    }

}
