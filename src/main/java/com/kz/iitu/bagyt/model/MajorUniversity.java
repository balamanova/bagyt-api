package com.kz.iitu.bagyt.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "universities")
@TypeAlias("universities")
public class MajorUniversity {
    @Id
    String _id;
    String address;
    String name;
    String photo;
    String webSite;
    String phone;
    String city;

}
