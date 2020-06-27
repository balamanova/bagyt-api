package com.kz.iitu.bagyt.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "test")
@TypeAlias("test")
public class TestView {
    @Id
    String _id;
    String name;
    String photoUrl;
    String questionNum;
    short stars;
}
