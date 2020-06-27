package com.kz.iitu.bagyt.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Data
@Document(collection = "major")
@TypeAlias("major")
public class Major {

    @Id
    String _id;

    String name;
    String profilSubject;
    String subject;
    String description;
    String object;
    String specializations;

    @DBRef
    List<HhJobses> hhJobses;

    @DBRef
    Set<MajorUniversity> universities;

//    @DBRef
//    List<MajorPoints> majorPoints;

    @Override
    public String toString() {
        return universities.toString();
    }
}
