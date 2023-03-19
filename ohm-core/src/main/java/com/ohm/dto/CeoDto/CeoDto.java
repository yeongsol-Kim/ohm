package com.ohm.dto.CeoDto;

import com.ohm.dto.AdminDto;
import com.ohm.dto.GymDto.GymDto;
import com.ohm.entity.Enum.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor
public class CeoDto {

    private Long id;

    private String name;

    private String position;

    private String nickname;

    private String profile;

    private String onelineIntroduce;

    private String introduce;

    private AdminDto admin;

    private List<GymDto> gymDto;

    private Role role;

    @Builder
    public CeoDto(String profile, String oneline_introduce, String introduce, String nickname, Long id, String name, GymDto gymDto, Role role) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.profile = profile;
        this.onelineIntroduce = oneline_introduce;
        this.introduce = introduce;
        this.role = role;
    }

    //
    public CeoDto(Long id, String name, String email, String password, Integer age, GymDto gymDto, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public CeoDto(String name, String email) {
        this.name = name;
    }

    public CeoDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
    }
}
