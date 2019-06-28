package com.safaricom.hackaton.survey.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuestionsTest {

    private Question question;

    @Before
    private void setUp() throws Exception {
        question = new Question();
        question.setQuestion("Whats my name");
    }

    @Test
    public void getQuestionDetails() {
        assertEquals("Whats my name", question.getQuestion());
    }
}