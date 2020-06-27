package com.kz.iitu.bagyt.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "subjects")
@TypeAlias("subjects")
public class Subject {

    @Id
    String _id;
    @Field("subject")
    String name;

    String photo;
}
