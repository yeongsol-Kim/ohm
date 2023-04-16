package com.ohm.repository.statistics;

import com.ohm.entity.Statistics.Statistics;
import com.ohm.entity.Statistics.TotalStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TotalStatisticsRepository extends JpaRepository<TotalStatistics,Long> {

    @Query("select st from TotalStatistics st where st.gymId = :gymId")
    TotalStatistics get_statistics(@Param("gymId") Long gymId);


}
