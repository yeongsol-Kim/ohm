package com.ohm.dto.ManagerDto;
import com.ohm.entity.Enum.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.ohm.dto.AdminDto;
import com.ohm.dto.GymDto.GymDto;


@Getter
@NoArgsConstructor
public class ManagerDto {

    private Long id;

    private String name;

    private String position;


    private String nickname;

    private String profile;

    private String onelineIntroduce;

    private String introduce;

    private AdminDto admin;

    private GymDto gymDto;

    private Role role;

    @Builder
    public ManagerDto(String profile, String oneline_introduce, String introduce, String nickname, Long id, String name, GymDto gymDto, Role role) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.profile = profile;
        this.onelineIntroduce = oneline_introduce;
        this.introduce = introduce;
        this.gymDto = gymDto;
        this.role = role;
    }

    //
    public ManagerDto(Long id, String name, String email, String password, Integer age, GymDto gymDto, Role role) {
        this.id = id;
        this.name = name;
        this.gymDto = gymDto;
        this.role = role;
    }

    public ManagerDto(String name, String email) {
        this.name = name;
    }

    public ManagerDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
    }
}
