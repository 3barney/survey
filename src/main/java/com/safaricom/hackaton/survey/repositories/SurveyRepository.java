package com.safaricom.hackaton.survey.repositories;

import com.safaricom.hackaton.survey.models.Survey;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SurveyRepository extends CrudRepository<Survey, Long> {
    Optional<Survey> findBySurveyName(String surveyName);
}
