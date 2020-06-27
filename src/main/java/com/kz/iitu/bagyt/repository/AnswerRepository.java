package com.kz.iitu.bagyt.repository;

import com.kz.iitu.bagyt.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnswerRepository extends MongoRepository<Question, String> {
}
