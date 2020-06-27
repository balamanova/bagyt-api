package com.kz.iitu.bagyt.model;

import com.kz.iitu.bagyt.model.enums.TestType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "test")
@TypeAlias("test")
public class Test {
    @Id
    String _id;
    String name;
    String photoUrl;
    String questionNum;
    @DBRef
    List<Question> questions;
    short stars;
    @DBRef
    List<TestResult> testResults;
    TestType type;
}
