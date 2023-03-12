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
    public Long save_question(Long gymId,QuestionDto questionDto){
        Optional<Gym> byId = gymRepository.findById(gymId);

        Question question = Question.builder()
                .content(questionDto.getContent())
                .gym(byId.get())
                .build();

        Question save = questionRepository.save(question);

        return save.getId();

    }

    @Transactional
    public void delete_question(Long questionId){
        questionRepository.delete(questionRepository.findById(questionId).get());

    }


    public List<QuestionDto> findall_question(Long gymId){

        List<QuestionDto> questionDtos = new ArrayList<QuestionDto>();
        List<Question> questions = questionRepository.findQuestionFetchJoin(gymId);
        for(Question question : questions){
            questionDtos.add(appConfig.modelMapper().map(question,QuestionDto.class));
        }

        return questionDtos;
    }


    public QuestionDto find_question(Long questionId){
        return appConfig.modelMapper().map(questionRepository.findById(questionId).get(),QuestionDto.class);
    }
}
