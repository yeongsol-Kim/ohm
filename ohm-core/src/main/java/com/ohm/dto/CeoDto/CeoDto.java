package com.ohm.dto.CeoDto;

import com.ohm.dto.AdminDto;
import com.ohm.dto.GymDto.GymDto;
import com.ohm.entity.Manager.Authority;
import lombok.Builder;

import java.util.List;
import java.util.Set;

public class CeoDto {

    private Long id;

    private String name;

    private String position;


    private String nickname;

    private String profile;

    private String oneline_introduce;

    private String introduce;

    private AdminDto admin;

    private List<GymDto> gymDto;

    private Set<Authority> authorities;

    @Builder
    public CeoDto(String profile, String oneline_introduce, String introduce, String nickname, Long id, String name, GymDto gymDto, Set<Authority> authorities) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.profile = profile;
        this.oneline_introduce = oneline_introduce;
        this.introduce = introduce;
        this.authorities = authorities;
    }

    //
    public CeoDto(Long id, String name, String email, String password, Integer age, GymDto gymDto, Set<Authority> authorities) {
        this.id = id;
        this.name = name;
        this.authorities = authorities;
    }

    public CeoDto(String name, String email) {
        this.name = name;
    }

    public CeoDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
    }
}
