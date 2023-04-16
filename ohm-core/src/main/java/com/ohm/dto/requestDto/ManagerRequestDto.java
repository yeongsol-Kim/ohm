package com.ohm.dto.requestDto;


import com.ohm.entity.Enum.Role;
import com.ohm.entity.Gym.Gym;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ManagerRequestDto {
    private String username;

    private String position;


    private String password;

    private String nickname;

    private String profile;

    private String onelineIntroduce;

    private String introduce;


    private Role role;

    private Long gymId;

    public void setGym(Long gymId) {
        this.gymId = gymId;
    }
}
