package com.ohm.api;


import com.ohm.dto.requestDto.GymRequestDto;
import com.ohm.dto.responseDto.StatisticsResponseDto;
import com.ohm.service.GymService;
import com.ohm.service.StatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;


@RestController
@RequestMapping("/api")
@Api(tags = {"StatisticsController API"})
@RequiredArgsConstructor
public class StatisticsController {

    private final GymService gymService;
    private final StatisticsService statisticsService;

    @ApiOperation(value = "Gym 등록(ROLE_CEO만 사용)", response = Long.class)
    @PostMapping("/test")
    @PreAuthorize("hasRole('ROLE_CEO')")
    public ResponseEntity<String> save(

    ) throws Exception {
        statisticsService.hourlyStatistics();
//        Long save = gymService.save(gymRequestDto,ceoId);

        //헬스장 통계테이블 생성
//        statisticsService.register_table(save);
        return ResponseEntity.ok("ok");

    }
}
