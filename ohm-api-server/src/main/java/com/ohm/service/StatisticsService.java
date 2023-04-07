package com.ohm.service;


import com.ohm.entity.Gym.Gym;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.ohm.entity.Statistics.Statistics;
import com.ohm.repository.gym.GymRepository;
import com.ohm.repository.statistics.StatisticsRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;
    private final TotalStatisticsService totalStatisticsService;
    private final GymRepository gymRepository;


    @Scheduled(cron = "0 0 0/1 * * *")
    public void hourlyStatistics() {
        LocalDateTime now = LocalDateTime.now();
        for (Gym gym : gymRepository.findAll()) {
            // 해당 gym의 현재 날짜 레코드 조회. 없다면 gym과 날짜를 이용해 생성
            Statistics stat = statisticsRepository.findByGymAndStatisticsDate(gym, now.toLocalDate()).orElse(Statistics.builder().one(0L).two(0L).three(0L).four(0L).five(0L).six(0L).seven(0L).eight(0L).nine(0L).ten(0L).eleven(0L).twelve(0L).thirteen(0L).fourteen(0L).fifteen(0L).sixteen(0L).seventeen(0L).eighteen(0L).nineteen(0L).twenty(0L).twentyOne(0L).twentyTwo(0L).twentyThree(0L).zero(0L).gym(gym).statisticsDate(now.toLocalDate()).build());
            log.info("id = {}", stat.getId());
            // 현재 시간에 현재 인원수 삽입
            stat.setTimeCount(now.getHour(), gym.getCurrentCount());

            // 저장
            log.info("id = {}", stat.getId());
            Statistics save = statisticsRepository.save(stat);
            totalStatisticsService.updateStatistics(gym.getId(), save.getId());

        }

    }


}
