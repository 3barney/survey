package com.safaricom.hackaton.survey.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SurveyTest {

    private Survey survey;

    @Before
    public void setUp() throws Exception {
        survey = new Survey();
        survey.setSurveyName("Customer survey");
        survey.setDescription("A test description");
    }

    @Test
    public void getSurveyDetails() {
        assertEquals("A test description", survey.getDescription());
        assertEquals("Customer survey", survey.getSurveyName());
    }
}