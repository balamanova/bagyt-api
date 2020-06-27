package com.kz.iitu.bagyt.repository;

import com.kz.iitu.bagyt.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
//    List<Comment> findByUniversity__id(String universityId);
}
