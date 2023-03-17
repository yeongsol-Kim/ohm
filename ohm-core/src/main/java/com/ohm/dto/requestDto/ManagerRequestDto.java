package com.ohm.dto.requestDto;


import com.ohm.entity.Gym.Gym;
import com.ohm.entity.Manager.Authority;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ManagerRequestDto {
    private String name;

    private String position;

    private String email;

    private String password;

    private String nickname;

    private String profile;

    private String onelineIntroduce;

    private String introduce;

    private int age;

    private String code;

    private Gym gym;

    public void setGym(Gym gym) {
        this.gym = gym;
    }
}