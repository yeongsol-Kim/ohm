package com.ohm.dto.ManagerDto;

import com.ohm.entity.Enum.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.ohm.dto.AdminDto;


@Getter
@NoArgsConstructor
public class ManagerDto {

    private Long id;

    private String username;

    private String position;


    private boolean available;

    private String nickname;

    private String profile;

    private boolean showProfile;

    private String onelineIntroduce;

    private String introduce;

    private AdminDto admin;

    private Long gymId;

    private Role role;

    @Builder
    public ManagerDto(String profile, String oneline_introduce, String introduce, String nickname, Long id, String name, Long gymId, Role role) {
        this.id = id;
        this.username = name;
        this.nickname = nickname;
        this.profile = profile;
        this.onelineIntroduce = oneline_introduce;
        this.introduce = introduce;
        this.gymId = gymId;
        this.role = role;
    }

    //
    public ManagerDto(Long id, String name, String email, String password, Integer age, Long gymId, Role role) {
        this.id = id;
        this.username = name;
        this.gymId = gymId;
        this.role = role;
    }

    public ManagerDto(String name, String email) {
        this.username = name;
    }

    public ManagerDto(Long id, String name, String email) {
        this.id = id;
        this.username = name;
    }
}
