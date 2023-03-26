package com.ohm.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GymResponseDto {

    private Long id;

    private String name;

    private String address;

    private Long count;

    private Long current_count;

    private int code;

    private int trainer_count;


    private List<GymImgResponseDto> imgs;

    private String introduce;


    private String onelineIntroduce;
}
