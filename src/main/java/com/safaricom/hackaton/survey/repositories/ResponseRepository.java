package com.safaricom.hackaton.survey.repositories;

import com.safaricom.hackaton.survey.models.Response;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResponseRepository extends CrudRepository<Response, Long> {

    List<Response> findAllByQuestionId(Long question_id);
}
