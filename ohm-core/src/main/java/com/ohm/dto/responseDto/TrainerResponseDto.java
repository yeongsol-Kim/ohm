package com.ohm.dto.responseDto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TrainerResponseDto {

    private Long id;

    private String position;

    //아이디
    private String name;

    private boolean showProfile;

    private String profile;

    private String onelineIntroduce;
    //자기소개
    private String introduce;

    //이름,닉내임
    private String nickname;

    private int age;
}
