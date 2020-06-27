package com.kz.iitu.bagyt.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Data
@Document(collection = "universities")
@TypeAlias("universities")
public class University {

    @Id
    String _id;
    String address;
    String name;
    String description;
    String photo;
    String email;
    int universityCode;
    String webSite;
    String phone;
    String city;

//    @DBRef
    List<MajorPoints> majorPoints;

    @DBRef
    List<Comment> commentList;

    @DBRef
    Set<UniversityMajor> majors;

}
