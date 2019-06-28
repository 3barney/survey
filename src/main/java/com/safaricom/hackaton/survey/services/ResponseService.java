package com.safaricom.hackaton.survey.services;

import com.safaricom.hackaton.survey.models.Response;
import com.safaricom.hackaton.survey.repositories.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ResponseService {

    @Autowired
    ResponseRepository responseRepository;

    public List<Response> getAllQuestionResponses(String question_id) {
        List<Response> responses = new ArrayList<>();
        responseRepository.findAllByQuestionId(Long.parseLong(question_id))
                .iterator()
                .forEachRemaining(responses::add);
        return responses;
    }

    public Response saveResponse(Response response) {
        Response savedResponse = responseRepository.save(response);

        if (!Objects.isNull(savedResponse)) {
            return savedResponse;
        }
        return null;
    }
}

