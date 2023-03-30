package com.ohm.service;


import com.ohm.config.AppConfig;
import com.ohm.dto.Statistics.StatisticsDto;
import com.ohm.dto.Statistics.TotalStatisticsDto;
import com.ohm.entity.Statistics.Statistics;
import com.ohm.entity.Statistics.TotalStatistics;
import com.ohm.repository.gym.GymRepository;
import com.ohm.repository.statistics.StatisticsRepository;
import com.ohm.repository.statistics.TotalStatisticsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class TotalStatisticsService {

    private final TotalStatisticsRepository totalStatisticsRepository;
    private final GymRepository gymRepository;
    private final StatisticsRepository statisticsRepository;
    private final AppConfig appConfig;

    @Transactional
    public void registerTable(Long gymId) {

        TotalStatistics build = TotalStatistics.builder()
                .one(0L).two(0L).three(0L).four(0L).five(0L).six(0L).seven(0L).eight(0L).nine(0L).ten(0L).eleven(0L).twelve(0L).thirteen(0L).fourteen(0L).fifteen(0L).sixteen(0L).seventeen(0L).eighteen(0L).nineteen(0L).twenty(0L).twentyOne(0L).twentyTwo(0L).twentyThree(0L).zero(0L)
                .gym(gymRepository.findById(gymId).get())
                .build();

        totalStatisticsRepository.save(build);
    }

    public TotalStatisticsDto getStatistics(Long gymId) {
        TotalStatistics statistics = totalStatisticsRepository.get_statistics(gymId);
        return appConfig.modelMapper().map(statistics, TotalStatisticsDto.class);
    }

    public void updateStatistics(Long gymId, Long statisticsId) {
        Optional<Statistics> byId = statisticsRepository.findById(statisticsId);
        TotalStatistics totalStatistics = totalStatisticsRepository.get_statistics(gymId);
        totalStatistics.update(byId.get());
    }


}
