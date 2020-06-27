package com.kz.iitu.bagyt.repository;

import com.kz.iitu.bagyt.model.TestView;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestViewRepository extends MongoRepository<TestView, String> {
}
