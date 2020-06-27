package com.kz.iitu.bagyt.repository;

import com.kz.iitu.bagyt.model.University;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UniversityRepository extends MongoRepository<University, String> {
    University findBy_id(String id);
}
