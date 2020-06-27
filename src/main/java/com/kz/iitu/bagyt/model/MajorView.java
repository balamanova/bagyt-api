package com.kz.iitu.bagyt.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "major")
@TypeAlias("major")
public class MajorView {
    @Id
    String _id;

    String name;
    String profilSubject;
    String subject;
    String specializations;
}
