package com.ohm.dto.CeoDto;

import com.ohm.dto.AdminDto;
import com.ohm.dto.GymDto.GymDto;
import com.ohm.entity.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CeoDto {

    private Long id;

    private String username;

    private String nickname;


    private List<GymDto> gyms;

    private Role role;



}
