package com.ohm.api;


import com.ohm.service.GymService;
import com.ohm.service.StatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
@Api(tags = {"StatisticsController API"})
@RequiredArgsConstructor
public class StatisticsController {


}
