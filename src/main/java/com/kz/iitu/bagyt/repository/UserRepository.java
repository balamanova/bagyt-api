package com.kz.iitu.bagyt.repository;

import com.kz.iitu.bagyt.model.User;
import com.kz.iitu.bagyt.model.enums.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    User findByMsisdn(String msisdn);

    User findByUsername(String username);

    User findByEmail(String email);

    User findByEmailContaining(String email);

    List<User> findAllByRole(Role role);
}
