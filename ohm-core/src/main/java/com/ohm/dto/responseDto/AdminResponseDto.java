package com.ohm.dto.responseDto;


import com.ohm.dto.AdminDto;
import com.ohm.dto.GymDto.GymDto;
import com.ohm.entity.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminResponseDto {

    private Long id;
    private String username;
    private String position;
    private String nickname;
    private String profile;
    private String onelineIntroduce;
    private String introduce;
    private AdminDto admin;
    private boolean showProfile;
    private boolean available;
    private GymDto gymDto;
    private Role role;
}
