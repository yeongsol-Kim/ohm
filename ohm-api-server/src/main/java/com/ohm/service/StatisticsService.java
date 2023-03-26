package com.ohm.service;


import com.ohm.entity.Gym.Gym;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.ohm.config.AppConfig;
import com.ohm.dto.Statistics.StatisticsDto;
import com.ohm.entity.Statistics.Statistics;
import com.ohm.repository.gym.GymRepository;
import com.ohm.repository.statistics.StatisticsRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;
    private final GymRepository gymRepository;
    private final AppConfig appConfig;

    public StatisticsDto get_statistics(Long gymId){
        List<String> results = new ArrayList<>();
        Statistics statistics = statisticsRepository.get_statistics(gymId);

        return  appConfig.modelMapper().map(statistics, StatisticsDto.class);
    }


    @Transactional
    public void register_table(Long gymId){

        Statistics build = Statistics.builder()
                .gym(gymRepository.findById(gymId).get())
                .build();

        statisticsRepository.save(build);
    }


    @Scheduled(cron = "0 0 0/1 * * *")
    public void hourlyStatistics() {
        LocalDateTime now = LocalDateTime.now();
        for (Gym gym : gymRepository.findAll()) {
            // 해당 gym의 현재 날짜 레코드 조회. 없다면 gym과 날짜를 이용해 생성
            Statistics stat = statisticsRepository.findByGymAndStatisticsDate(gym, now.toLocalDate()).orElse(Statistics.builder().gym(gym).statisticsDate(now.toLocalDate()).build());
            log.info("id = {}", stat.getId());
            // 현재 시간에 현재 인원수 삽입
            stat.setTimeCount(now.getHour(), gym.getCurrentCount());
            // 저장
            log.info("id = {}", stat.getId());
            statisticsRepository.save(stat);
        }

    }



//    @Transactional
//    public void add_statistics(Long gymId, int count) {
//        LocalTime now = LocalTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH");
//        String formatedNow = now.format(formatter);
//
//        String hour;
//        if (formatedNow.substring(0, 1).equals("0")) {
//            hour = formatedNow.substring(1);
//        } else {
//            hour = formatedNow.toString();
//        }
//
//        System.out.println("시간 = "+hour);
//        switch (Integer.parseInt(hour)) {
//            case 1:
//                statisticsRepository.update_1(gymId, Double.valueOf(count));
//                break;
//            case 2:
//                statisticsRepository.update_2(gymId, Double.valueOf(count));
//                break;
//            case 3:
//                statisticsRepository.update_3(gymId, Double.valueOf(count));
//                break;
//            case 4:
//                statisticsRepository.update_4(gymId, Double.valueOf(count));
//                break;
//            case 5:
//                statisticsRepository.update_5(gymId, Double.valueOf(count));
//                break;
//
//            case 6:
//                statisticsRepository.update_6(gymId, Double.valueOf(count));
//                break;
//
//            case 7:
//                statisticsRepository.update_7(gymId, Double.valueOf(count));
//                break;
//            case 8:
//                statisticsRepository.update_8(gymId, Double.valueOf(count));
//                break;
//            case 9:
//                statisticsRepository.update_9(gymId, Double.valueOf(count));
//                break;
//            case 10:
//                statisticsRepository.update_10(gymId, Double.valueOf(count));
//                break;
//            case 11:
//                statisticsRepository.update_11(gymId, Double.valueOf(count));
//                break;
//            case 12:
//                statisticsRepository.update_12(gymId, Double.valueOf(count));
//                break;
//            case 13:
//                statisticsRepository.update_13(gymId, Double.valueOf(count));
//                break;
//            case 14:
//                statisticsRepository.update_14(gymId, Double.valueOf(count));
//                break;
//            case 15:
//                statisticsRepository.update_15(gymId, Double.valueOf(count));
//                break;
//            case 16:
//                statisticsRepository.update_16(gymId, Double.valueOf(count));
//                break;
//            case 17:
//                statisticsRepository.update_17(gymId, Double.valueOf(count));
//                break;
//            case 18:
//                statisticsRepository.update_18(gymId, Double.valueOf(count));
//                break;
//            case 19:
//                statisticsRepository.update_19(gymId, Double.valueOf(count));
//                break;
//            case 20:
//                statisticsRepository.update_20(gymId, Double.valueOf(count));
//                break;
//            case 21:
//                statisticsRepository.update_21(gymId, Double.valueOf(count));
//                break;
//            case 22:
//                statisticsRepository.update_22(gymId, Double.valueOf(count));
//                break;
//            case 23:
//                statisticsRepository.update_23(gymId, Double.valueOf(count));
//                break;
//            case 24:
//                statisticsRepository.update_24(gymId, Double.valueOf(count));
//                break;
//            default:
//
//                System.out.println("존재하지 않는 시간 입니다.");
//
//        }
//    }


}
