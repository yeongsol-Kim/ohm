package com.ohm.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.ohm.config.AppConfig;
import com.ohm.dto.AnswerDto.AnswerDto;
import com.ohm.entity.Answer.Answer;
import com.ohm.entity.Question.Question;
import com.ohm.repository.answer.AnswerRepository;
import com.ohm.repository.question.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final AppConfig appConfig;

    public AnswerDto find_answer(Long id){
        Optional<Answer> byId = answerRepository.findById(id);
        return appConfig.modelMapper().map(byId.get(),AnswerDto.class);
    }


    @Transactional
    public AnswerDto save(AnswerDto answerDto,Long questionId){
        Optional<Question> byId = questionRepository.findById(questionId);
        Answer answer = Answer.builder()
                .content(answerDto.getContent())
                .question(byId.get())
                .build();
        Answer answwer = answerRepository.save(answer);
        byId.get().register_answer(answwer);
        return appConfig.modelMapper().map(answwer,AnswerDto.class);
    }

    @Transactional
    public void delete(Long answerId){
        answerRepository.delete(answerRepository.findById(answerId).get());
        return;
    }

    @Transactional
    public void update(Long answerId,AnswerDto answerDto){
        Optional<Answer> byId = answerRepository.findById(answerId);
        byId.get().update(answerDto);
        return;
    }

}
