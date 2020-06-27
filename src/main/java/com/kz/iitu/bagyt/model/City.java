package com.kz.iitu.bagyt.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "city")
@TypeAlias("city")
public class City {
    @Id
    String _id;
    @Field("city")
    String name;
}
