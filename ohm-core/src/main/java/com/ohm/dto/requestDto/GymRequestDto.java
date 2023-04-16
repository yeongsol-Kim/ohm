package com.ohm.dto.requestDto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GymRequestDto {

    private String name;

    private String address;

    private int count;

    private String onelineIntroduce;


    //헬스장 소개 문구
    private String introduce;

    //헬스장 면적수
    private String area;


    //휴관일
    private String CLOSEDDAYS;

    private String SUNDAY;

    private String SATURDAY;

    //평일
    private String WEEKDAY;

    //공휴일
    private String HOLIDAY;

}
