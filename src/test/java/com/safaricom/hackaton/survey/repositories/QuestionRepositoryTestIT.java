package com.safaricom.hackaton.survey.repositories;

import com.safaricom.hackaton.survey.models.Question;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(initializers = {QuestionRepositoryTestIT.Initializer.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class QuestionRepositoryTestIT {

    @Autowired
    QuestionRepository questionRepository;

    private Question question;

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
    public void setUp() throws Exception {
        Question questionItems = new Question();
        questionItems.setQuestion("Hello world ?");
        questionItems.setSurveyId(1L);

        question = questionRepository.save(questionItems);
        assertNotNull(question);
    }

    @Test
    public void findAllBySurveyId() {
        Iterable<Question> questions = questionRepository.findAllBySurveyId(question.getSurveyId());
        assertEquals("Hello world ?", questions.iterator().next().getQuestion());
    }
}