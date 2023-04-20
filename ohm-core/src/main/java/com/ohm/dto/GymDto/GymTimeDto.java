package com.ohm.dto.GymDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.ohm.entity.Gym.Gym;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GymTimeDto {


    private Long id;

    //휴관일
    private String closeDay;

    private String sunday;

    private String saturday;

    //평일
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;

    //공휴일
    private String holiday;

    @JsonIgnore
    private Gym gym;
}
