package com.ohm.entity.Question;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.ohm.entity.Answer.Answer;
import com.ohm.entity.Gym.Gym;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "question_id")
    private Long id;

    @Column(name = "content")
    private String content;


    @Column(name = "gym_id")
    private Long gymId;

    @OneToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @Builder
    public Question(String content,Long gymId,Answer answer){
        this.content = content;
        this.gymId = gymId;
        this.answer = answer;
    }

    //Question Answer 답변 메서드
    public void register_answer(Answer answer){
        this.answer = answer;
    }


}
