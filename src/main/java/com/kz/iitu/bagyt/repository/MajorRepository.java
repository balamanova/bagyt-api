package com.kz.iitu.bagyt.repository;

import com.kz.iitu.bagyt.model.Major;
import com.kz.iitu.bagyt.model.University;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajorRepository extends MongoRepository<Major, String> {
    List<Major> findBySubjectContaining(String subject);
    Major findBy_id(String id);
}
