package com.safaricom.hackaton.survey.controllers;

import com.safaricom.hackaton.survey.models.Question;
import com.safaricom.hackaton.survey.services.QuestionService;
import com.safaricom.hackaton.survey.utils.ApiConstants;
import com.safaricom.hackaton.survey.utils.ControllerReturnEntity;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/questions")
@Api(value = "Survey Endpoint", description = "Handler for Survey questions")
public class QuestionsController {

    @Autowired
    QuestionService questionService;

    /**
     * Fetch all questions for a given survey
     * @return controllerResponse
     */
    @RequestMapping(
            method = {RequestMethod.GET},
            params = "survey_id",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ControllerReturnEntity<Object> getSurveyQuestions(@RequestParam String survey_id) {
        List<Question> questionList = questionService.getAllSurveyQuestions(survey_id);
        if (questionList.size() > 0) {
            return controllerResponse(ApiConstants.OK, ApiConstants.MSG_OK, questionList);
        }
        return controllerResponse(ApiConstants.OK, ApiConstants.MSG_OK, "No items found");
    }

    /**
     * Create a survey question item
     * @param question object to be created
     * @return controllerResponse
     */
    @RequestMapping(method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ControllerReturnEntity<Object> createSurveyQuestions(@RequestBody Question question) {
        Question savedEntity = questionService.saveSurveyQuestion(question);

        if (!Objects.isNull(savedEntity)) {
            return controllerResponse(ApiConstants.OK, ApiConstants.CREATED_OK, savedEntity);
        }
        return controllerResponse(ApiConstants.OK, ApiConstants.CREATED_ERROR, "Failed to save item");
    }


    /**
     * Controller response
     * @param code status code
     * @param message message of response
     * @param result result object if necessary
     * @return
     */
    private ControllerReturnEntity<Object> controllerResponse(String code, String message, Object result) {
        ControllerReturnEntity<Object> controllerReturnEntity = new ControllerReturnEntity<>();
        controllerReturnEntity.setCode(code);
        controllerReturnEntity.setMessage(message);
        controllerReturnEntity.setResult(result);

        return controllerReturnEntity;
    }
}
