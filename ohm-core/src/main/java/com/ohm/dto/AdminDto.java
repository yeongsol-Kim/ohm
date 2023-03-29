package com.ohm.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import com.ohm.dto.ManagerDto.ManagerDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class AdminDto {

    private Long id;



    private String name;

    private List<ManagerDto> managers = new ArrayList<ManagerDto>();

    public AdminDto(String name){
        this.name = name;
    }
}
