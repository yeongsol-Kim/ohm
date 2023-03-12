package com.ohm.dto.InputDto;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class InputDto {

    private Long id;


    private LocalDateTime time;

    private String date;

    private int count;

    // input or output
    private String type;




}
