package com.safaricom.hackaton.survey.services;

import com.safaricom.hackaton.survey.models.Survey;
import com.safaricom.hackaton.survey.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SurveyService {

    @Autowired
    SurveyRepository surveyRepository;

    public List<Survey> getAllSurveys() {
        List<Survey> surveyList = new ArrayList<>();

        surveyRepository.findAll().iterator().forEachRemaining(surveyList::add);
        return surveyList;
    }

    public Survey getServiceByName(String name) {
        Optional<Survey> surveyOptional = surveyRepository.findBySurveyName(name);

        if (surveyOptional.isPresent())
            return surveyOptional.get();
        return null;
    }

    public Survey createSurvey(Survey survey) {
        Survey createdSurvey = surveyRepository.save(survey);

        if (!Objects.isNull(survey) && createdSurvey.getSurveyName().equalsIgnoreCase(survey.getSurveyName())) {
            return survey;
        }
        return null;
    }
}
