package com.ohm.repository.question;

import com.ohm.entity.Answer.Answer;
import com.ohm.entity.Question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {

    @Query("select q from Question q where q.gym.id = :gymId")
    List<Question> findQuestionfindByGymId(@Param("gymId") Long gymId);

    @Query("select distinct q from Question q left join fetch q.answer where q.gym.id =:gymId")
    List<Question> findQuestionFetchJoin(@Param("gymId") Long gymId);


}
