package com.ohm.dto.requestDto;


import com.ohm.entity.Enum.Role;
import com.ohm.entity.Gym.Gym;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ManagerRequestDto {
    private String username;

    private String position;

    private String email;

    private String password;

    private String nickname;

    private String profile;

    private String onelineIntroduce;

    private String introduce;

    private int age;

    private Role role;

    private String code;

    private Gym gym;

    public void setGym(Gym gym) {
        this.gym = gym;
    }
}
