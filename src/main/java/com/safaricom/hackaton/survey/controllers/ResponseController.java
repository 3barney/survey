package com.safaricom.hackaton.survey.controllers;

import com.safaricom.hackaton.survey.models.Response;
import com.safaricom.hackaton.survey.services.ResponseService;
import com.safaricom.hackaton.survey.utils.ApiConstants;
import com.safaricom.hackaton.survey.utils.ControllerReturnEntity;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/response")
@Api(value = "Response Endpoint", description = "Handler for capturing response")
public class ResponseController {

    @Autowired
    ResponseService responseService;

    /**
     * Fetch all responses given a particular question id
     * @param question_id
     * @return
     */
    @RequestMapping(
            method = {RequestMethod.GET},
            params = "question_id",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    public ControllerReturnEntity<Object> getQuestionResponses(@RequestParam String question_id) {
        List<Response> responses = responseService.getAllQuestionResponses(question_id);
        if (responses.size() > 0) {
            return controllerResponse(ApiConstants.OK, ApiConstants.MSG_OK, responses);
        }
        return controllerResponse(ApiConstants.OK, ApiConstants.MSG_OK, "No items found");
    }

    /**
     * Create a survey question item
     * @param response object to be created
     * @return controllerResponse
     */
    @RequestMapping(method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ControllerReturnEntity<Object> createSurveyQuestions(@RequestBody Response response) {
        Response savedEntity = responseService.saveResponse(response);

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
