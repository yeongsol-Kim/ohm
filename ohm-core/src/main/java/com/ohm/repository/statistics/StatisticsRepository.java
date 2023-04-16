package com.ohm.repository.statistics;

import com.ohm.entity.Gym.Gym;
import com.ohm.entity.Statistics.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

    Optional<Statistics> findByGymIdAndStatisticsDate(Long gymId, LocalDate statisticsDate);


}
