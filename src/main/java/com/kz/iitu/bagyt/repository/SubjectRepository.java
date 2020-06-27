package com.kz.iitu.bagyt.repository;

import com.kz.iitu.bagyt.model.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubjectRepository  extends MongoRepository<Subject, String> {
}