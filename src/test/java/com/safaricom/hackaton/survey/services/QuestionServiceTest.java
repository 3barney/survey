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

/**
node {
        stage('SCM Checkout') {
        git 'https://github.com/3barney/survey'
        }

        stage('Maven package') {
        def mavenHome = tool name: 'maven-3', type: 'maven'
        def mavenCommand = "${mavenHome}/bin/mvn"
        sh label: '', script: "${mavenCommand} clean package"
        }

        stage('Build Docker Image') {
        sh label: '', script: "docker build -t jomo90/survey:1.0.0 ."
        }

        stage('Push Docker Image to Dhub') {
        withCredentials([string(credentialsId: 'docker_hub', variable: 'dockerHubPwd')]) {
        sh label: '', script: "docker login -u jomo90 -p ${dockerHubPwd}"
        }

        sh label: '', script: "docker push jomo90/survey:1.0.0"
        }

        stage('Run Docker image locally') {
        sh label: '', script: "docker run -p 9001:9001 -d --name survey --network=host jomo90/survey:1.0.0"
        }
        }
 **/