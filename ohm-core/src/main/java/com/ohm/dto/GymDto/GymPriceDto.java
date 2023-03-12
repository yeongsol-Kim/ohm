package com.ohm.dto.GymDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class GymPriceDto {

    private Long id;

    private String during;

    private String price;

//    @JsonIgnore
//    private GymDto gym;
}
