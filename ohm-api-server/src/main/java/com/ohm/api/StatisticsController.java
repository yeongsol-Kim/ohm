package com.ohm.api;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@Api(tags = {"StatisticsController API"})
@RequiredArgsConstructor
public class StatisticsController {

}
