package com.formento.neighborhood.model;

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
public class ProvinceIT {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shoudDeserialize() throws IOException {
        // given
        final Province PROVINCE = new Province("abc", new Boundary(new Point(1, 2), new Point(3, 4)));
        final String JSON = "{\"description\": \"abc\", \"boundary\": {\"upperLeft\": {\"x\": 1, \"y\": 2}, \"bottomRight\": {\"x\": 3, \"y\": 4}}}";

        // when
        final Province result = objectMapper.readValue(JSON, Province.class);

        // then
        assertThat(result).isEqualTo(PROVINCE);
    }

}
