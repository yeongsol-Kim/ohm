package com.ohm.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.ohm.config.AppConfig;
import com.ohm.dto.QuestionDto.QuestionDto;
import com.ohm.entity.Gym.Gym;
import com.ohm.entity.Question.Question;
import com.ohm.repository.gym.GymRepository;
import com.ohm.repository.question.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final AppConfig appConfig;
    private final QuestionRepository questionRepository;
    private final GymRepository gymRepository;


    @Transactional
    public Long saveQuestion(Long gymId, QuestionDto questionDto) {
        Optional<Gym> byId = gymRepository.findById(gymId);

        Question question = Question.builder()
                .content(questionDto.getContent())
                .gymId(byId.get().getId())
                .build();

        Question save = questionRepository.save(question);

        return save.getId();
    }

    @Transactional
    public void deleteQuestion(Long questionId) {
        questionRepository.delete(questionRepository.findById(questionId).get());

    }


    public List<QuestionDto> findallQuestion(Long gymId) {

        List<QuestionDto> questionDtos = new ArrayList<QuestionDto>();
        List<Question> questions = questionRepository.findQuestionFetchJoin(gymId);
        for (Question question : questions) {
            questionDtos.add(appConfig.modelMapper().map(question, QuestionDto.class));
        }

        return questionDtos;
    }


    public QuestionDto findQuestion(Long questionId) {
        return appConfig.modelMapper().map(questionRepository.findById(questionId).get(), QuestionDto.class);
    }
}
