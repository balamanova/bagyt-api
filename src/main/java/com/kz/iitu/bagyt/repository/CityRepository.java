package com.kz.iitu.bagyt.repository;

import com.kz.iitu.bagyt.model.City;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CityRepository extends MongoRepository<City, String> {
}