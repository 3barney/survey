package com.safaricom.hackaton.survey.repositories;

import com.safaricom.hackaton.survey.models.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Long> {

    List<Question> findAllBySurveyId(Long survey_id);
}
