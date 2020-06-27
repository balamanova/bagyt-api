package com.kz.iitu.bagyt.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "testResult")
@TypeAlias("testResult")
public class TestResult {

    @Id
    String _id;

    String result;
    String resultDes;
    short scoreMax;
    short scoreMin;
}
