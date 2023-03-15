package com.ohm.service;

import com.ohm.dto.GymDto.GymDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class GymServiceTest {

    @Autowired
    GymService gymService;

    @Test
    public void getGymCountPageInfoTest() {

        GymDto gym = gymService.getGymMemberCountPageInfo(1L);
        Assertions.assertThat(gym.getCurrent_count()).isEqualTo(1);
    }

}