package com.ohm.dto.QuestionDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import com.ohm.dto.AnswerDto.AnswerDto;

@Getter
@NoArgsConstructor
public class QuestionDto {

    private Long id;

    private String content;

    private AnswerDto answer;

}
