package com.safaricom.hackaton.survey.controllers;

import com.safaricom.hackaton.survey.models.Survey;
import com.safaricom.hackaton.survey.services.SurveyService;
import com.safaricom.hackaton.survey.utils.ApiConstants;
import com.safaricom.hackaton.survey.utils.ControllerReturnEntity;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/survey")
@Api(value = "Survey Endpoint", description = "Handler for creating surveys")
public class SurveyController {

    @Autowired
    SurveyService surveyService;

    /**
     * Fetch all surveys in the system
     * @return controllerResponse
     */
    @RequestMapping(method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ControllerReturnEntity<Object> getAllSurveys() {
        List<Survey> surveyList = surveyService.getAllSurveys();

        if (!Objects.isNull(surveyList) && surveyList.size() > 0) {
            return controllerResponse(ApiConstants.OK, ApiConstants.MSG_OK, surveyList);
        }
        return controllerResponse(ApiConstants.OK, ApiConstants.MSG_OK, "No items found");
    }

    /**
     * Create a survey item
     * @param survey object to be created
     * @return controllerResponse
     */
    @RequestMapping(method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ControllerReturnEntity<Object> createSurvey(@RequestBody Survey survey) {
        Survey createSurvey = surveyService.createSurvey(survey);

        if (!Objects.isNull(survey)) {
            return controllerResponse(ApiConstants.OK, ApiConstants.CREATED_OK, survey);
        }
        return controllerResponse(ApiConstants.OK, ApiConstants.CREATED_ERROR, "Failed to create item");
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
