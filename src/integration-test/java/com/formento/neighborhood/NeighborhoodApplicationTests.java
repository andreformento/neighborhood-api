package com.formento.neighborhood;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NeighborhoodApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void shouldCallMain() {
        NeighborhoodApplication.main(new String[]{});
    }

}