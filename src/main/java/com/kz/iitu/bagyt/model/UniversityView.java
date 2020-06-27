package com.kz.iitu.bagyt.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document(collection = "universities")
@TypeAlias("universities")
public class UniversityView {

    @Id
    String _id;
    String address;
    String name;
    String photo;
    String webSite;
    String phone;
    String city;

    @DBRef
    Set<MajorView> majors;


}
