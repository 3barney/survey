package com.safaricom.hackaton.survey.services;

import com.safaricom.hackaton.survey.models.Response;
import com.safaricom.hackaton.survey.repositories.ResponseRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ResponseServiceTest {

    private Response response;

    @Mock
    ResponseRepository responseRepository;

    @InjectMocks
    ResponseService responseService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        response = new Response();
        response.setResponse("Not helllo world");
        response.setQuestionId(1L);
        response.setId(4L);
    }

    @Test
    public void getAllQuestionResponses() {
        List<Response> responseList = new ArrayList<>();
        responseList.add(response);

        when(responseRepository.findAllByQuestionId(response.getQuestionId())).thenReturn(responseList);
        List<Response> responses = responseService.getAllQuestionResponses(String.valueOf(response.getQuestionId()));
        assertNotEquals(0, responses.size());
    }

    @Test
    public void saveResponse() {
        when(responseRepository.save(response)).thenReturn(response);
        Response responseItem = responseService.saveResponse(response);
        assertNotNull(responseItem);
        assertEquals(response.getId(), responseItem.getId());
    }
}