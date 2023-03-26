package com.ohm.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(value = false)
class StatisticsServiceTest {

    @Autowired
    private StatisticsService statisticsService;

    @Test
    void timeStatisticsTest() {
        statisticsService.hourlyStatistics();
    }
}