package com.kz.iitu.bagyt.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "question")
@TypeAlias("question")
public class Question {
    @Id
    String _id;
    String question;
    @DBRef
    List<Answer> answers;
}
