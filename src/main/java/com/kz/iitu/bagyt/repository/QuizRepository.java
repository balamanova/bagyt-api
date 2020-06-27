package com.kz.iitu.bagyt.repository;

import com.kz.iitu.bagyt.model.Test;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends MongoRepository<Test, String> {
    Test getBy_id(String id);
}
