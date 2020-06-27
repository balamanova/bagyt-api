package com.kz.iitu.bagyt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kz.iitu.bagyt.model.enums.Role;
import com.kz.iitu.bagyt.model.enums.UserStatus;
import com.kz.iitu.bagyt.util.Msisdn;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
@TypeAlias("user")
public class User {

    @Id
    String id;

    @Msisdn
    String msisdn;

    String username;

    @JsonIgnore
    String password;

    String name;
    String email;
    String universityName;
    Role role;

    @DBRef
    University university;

    UserStatus userStatus;

    public String getUniversityName() {
        return university.getName();
    }
    public University getUniversity() {return university; }
    int shortCode;
}
