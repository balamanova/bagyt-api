package com.kz.iitu.bagyt.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "answer")
@TypeAlias("answer")
public class Answer {
    @Id
    String _id;
    short point;
    String text;
}
