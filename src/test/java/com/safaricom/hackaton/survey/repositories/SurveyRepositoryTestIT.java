package com.safaricom.hackaton.survey.repositories;

import com.safaricom.hackaton.survey.models.Survey;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(initializers = {SurveyRepositoryTestIT.Initializer.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SurveyRepositoryTestIT {

    @Autowired
    SurveyRepository surveyRepository;

    private Survey survey;

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer =
            (PostgreSQLContainer) new PostgreSQLContainer<>("postgres:10.6")
                    .withStartupTimeout(Duration.ofSeconds(600));

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Before
    public void setUp() {
        Survey surveyTest = new Survey();
        surveyTest.setSurveyName("Customer survey");
        surveyTest.setDescription("A new way to enhance richness");

        survey = surveyRepository.save(surveyTest);
        assertNotNull(survey);
    }

    @Test
    public void testFindWorks() {
        Iterable<Survey> surveyOptional = surveyRepository.findAll();
        assertEquals("Customer survey", surveyOptional.iterator().next().getSurveyName());
    }

    @Test
    public void testFindBySurveyName() {
        Optional<Survey> surveyOptional = surveyRepository.findBySurveyName(survey.getSurveyName());
        assertEquals("Customer survey", surveyOptional.get().getSurveyName());
    }
}