package com.kz.iitu.bagyt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "majorPoints")
@TypeAlias("majorPoints")
public class MajorPoints {

    @Id
    String _id;

    int kazPoint;
    int kazSelPoint;
    int rusSelPoint;
    int rusPoint;
    String majorName;

    @JsonIgnore
    @DBRef
    MajorUniversity university;

    @DBRef
    UniversityMajor major;

    public String getMajorName() {
        return major.getName();
    }
}


