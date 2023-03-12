package com.ohm.dto.GymDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.ohm.entity.Gym.Gym;


@Getter
@NoArgsConstructor
public class GymTimeDto {


    private Long id;

    //휴관일
    private String closeddays;

    private String sunday;

    private String saturday;

    //평일
    private String weekday;

    //공휴일
    private String holiday;

    @JsonIgnore
    private Gym gym;
}
