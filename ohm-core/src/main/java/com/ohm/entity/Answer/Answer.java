package com.ohm.entity.Answer;

import lombok.*;
import com.ohm.dto.AnswerDto.AnswerDto;
import com.ohm.entity.Question.Question;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue
    @Column(name = "answer_id")
    private Long id;

    @NotNull
    @Column(name = "content")
    private String content;

    @OneToOne(mappedBy = "answer")
    private Question question;

    public void update(AnswerDto answerDto){
        this.content = answerDto.getContent();
    }

}
