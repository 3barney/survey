package com.safaricom.hackaton.survey.services;

import com.safaricom.hackaton.survey.models.Question;
import com.safaricom.hackaton.survey.repositories.QuestionRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class QuestionServiceTest {

    private Question question;

    @Mock
    QuestionRepository questionRepository;

    @InjectMocks
    QuestionService questionService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        question = new Question();
        question.setId(1L);
        question.setQuestion("Hello World ?");
        question.setSurveyId(3L);
    }

    @Test
    public void getAllSurveyQuestions() {

        List<Question> questionList = new ArrayList<>();
        questionList.add(question);

        when(questionRepository.findAllBySurveyId(question.getSurveyId())).thenReturn(questionList);
        List<Question> questions = questionService.getAllSurveyQuestions(String.valueOf(question.getSurveyId()));
        assertNotEquals(0, questions.size());
    }

    @Test
    public void saveSurveyQuestion() {
        when(questionRepository.save(question)).thenReturn(question);
        Question questionItem = questionService.saveSurveyQuestion(question);
        assertNotNull(questionItem);
        assertEquals(question.getId(), questionItem.getId());
    }
}