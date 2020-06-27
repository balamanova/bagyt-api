package com.kz.iitu.bagyt.repository;

import com.kz.iitu.bagyt.model.UniversityView;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface UniversityViewRepository extends MongoRepository<UniversityView, String> {

    List<UniversityView> getAllByCityAndNameStartingWith(String city, String searchText);
    List<UniversityView> findAllByCity(String city);

    List<UniversityView> findAllByNameContaining(String searchText);

    @Query(value = "{ 'majors' : { 'name': ?0 }}")
    List<UniversityView> getAllByMajorName(String name, Pageable pageable);

    @Query(value = "{ 'majors' : { 'name': ?0 }, 'city' : ?1 }")
    List<UniversityView> getAllByMajorNameAndCity(String name, String city, Pageable pageable);

    @Query(value = "{ 'majors' : { 'name': ?0, 'subject': ?1 }, 'city' : ?2 }")
    List<UniversityView> getAllByMajorNameSubjectAndCity(String name, String subject, String city);

    List<UniversityView> findAllBy_idIsIn(List<String> id);
}

