package com.ohm.dto.CeoDto;

import com.ohm.entity.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CeoDto {

    private Long id;

    private String username;

    private String nickname;

    private boolean available;

    private Role role;



}
