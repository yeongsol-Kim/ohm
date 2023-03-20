package com.ohm.dto.GymDto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ohm.entity.Gym.Gym;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class GymImgDto {

    private Long id;

    @JsonManagedReference
    private Gym gym;

    private String originFileName;

    private String filePath;

    @Builder
    public GymImgDto(String origFileName,String filePath){
        this.originFileName = origFileName;
        this.filePath = filePath;
    }
}
