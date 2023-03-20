package com.ohm.dto.responseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GymResponseDto {

    private Long id;

    private String name;

    private String address;

    private int count;

    private int current_count;

    private int code;

    private int trainer_count;


    private List<GymImgResponseDto> imgs;

    private String introduce;


    private String onelineIntroduce;

    @Builder
    public GymResponseDto(int code,int trainer_count,Long id,int current_count,String name,String address,int count,List<GymImgResponseDto> imgs,String introduce,String oneline_introduce){
        this.name = name;
        this.code = code;
        this.trainer_count = trainer_count;
        this.id = id;
        this.current_count = current_count;
        this.address = address;
        this.count = count;
        this.imgs = imgs;
        this.introduce = introduce;
        this.onelineIntroduce = oneline_introduce;
    }
}
