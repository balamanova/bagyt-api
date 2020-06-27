package com.kz.iitu.bagyt.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Document(collection = "comment")
@TypeAlias("comment")
public class Comment {
    @Id
    String _id;

    String username;
    LocalDateTime createdDate;
    String text;
    CommentUser commentUser;
}
