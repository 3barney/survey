package com.safaricom.hackaton.survey.services;

import com.safaricom.hackaton.survey.models.Survey;
import com.safaricom.hackaton.survey.repositories.SurveyRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class SurveyServiceTest {

    private Survey survey;

    @Mock
    SurveyRepository surveyRepository;

    @InjectMocks
    SurveyService surveyService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        survey = new Survey();
        survey.setId(4L);
        survey.setSurveyName("Hello");
    }

    @Test
    public void getAllSurveys() {
        List<Survey> surveyList = new ArrayList<>();
        surveyList.add(survey);
        when(surveyRepository.findAll()).thenReturn(surveyList);

        List<Survey> items = surveyService.getAllSurveys();
        assertNotNull(items);
        assertEquals(1, items.size());
    }

    @Test
    public void getServiceByName() {
        List<Survey> surveyList = new ArrayList<>();
        surveyList.add(survey);

        Optional<Survey> surveyOptional = Optional.of(survey);
        when(surveyRepository.findBySurveyName(anyString())).thenReturn(surveyOptional);

        Survey surveyItem = surveyService.getServiceByName(anyString());
        assertNotNull(surveyItem);
    }
}