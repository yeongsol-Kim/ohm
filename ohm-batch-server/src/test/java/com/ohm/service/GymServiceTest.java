package com.ohm.service;

import com.ohm.dto.GymDto.GymDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class GymServiceTest {

    @Autowired
    GymService gymService;

    @Test
    public void getGymCountPageInfoTest() {

        GymDto gym = gymService.getGymMemberCountPageInfo(1L);
        Assertions.assertThat(gym.getCurrentCount()).isEqualTo(1);
    }

}