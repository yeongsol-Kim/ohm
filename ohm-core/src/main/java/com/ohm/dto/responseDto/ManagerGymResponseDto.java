package com.ohm.dto.responseDto;

import com.ohm.dto.GymDto.GymImgDto;

import java.util.List;

public class ManagerGymResponseDto {

    private Long id;

    private String name;

    private String address;

    private int count;

    private List<GymImgDto> imgs;

    private String introduce;

    private boolean showProfile;

    private String oneline_introduce;

    private int code;

    private int current_count;

    private int trainer_count;
}
