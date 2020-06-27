package com.kz.iitu.bagyt.repository;

import com.kz.iitu.bagyt.model.MajorPoints;
import com.kz.iitu.bagyt.model.MajorUniversity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajorPointsRepository extends MongoRepository<MajorPoints, String> {

    List<MajorPoints> findByUniversity(MajorUniversity university);
    List<MajorPoints> findByUniversity__id(String id);

    List<MajorPoints> findByKazSelPointLessThanEqual(int kazSelPoint);
    List<MajorPoints> findByKazPointLessThanEqual(int kazSelPoint);
    List<MajorPoints> findByRusSelPointLessThanEqual(int kazSelPoint);
    List<MajorPoints> findByRusPointLessThanEqual(int kazSelPoint);

}
