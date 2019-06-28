package com.safaricom.hackaton.survey.services;

import com.safaricom.hackaton.survey.models.Question;
import com.safaricom.hackaton.survey.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public List<Question> getAllSurveyQuestions(String survey_id) {
        List<Question> questions = new ArrayList<>();
        questionRepository.findAllBySurveyId(Long.parseLong(survey_id)).iterator().forEachRemaining(questions::add);
        return questions;
    }

    public Question saveSurveyQuestion(Question question) {
        Question createdQuestion = questionRepository.save(question);

        if (!Objects.isNull(createdQuestion)) {
            return createdQuestion;
        }
        return null;
    }
}
