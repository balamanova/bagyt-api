package com.kz.iitu.bagyt.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "commentUser")
@TypeAlias("commentUser")
public class CommentUser {
    @Id
    String id;
    String email;
    String name;
    String photo;
}
