package com.formento.neighborhood.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.formento.neighborhood.model.Point;
import com.formento.neighborhood.model.Province;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProvinceServiceIT {

    @Autowired
    private ProvinceService provinceService;

    @Test
    public void shouldFindPointInTwoRegions() {
        // given
        final Point point = new Point(424, 662);

        // when
        final Collection<Province> provinces = provinceService.findByPoint(point);

        // then
        assertThat(provinces).asList().hasSize(2);
    }
    
}
