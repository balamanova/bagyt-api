package com.kz.iitu.bagyt.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "hhJobses")
@TypeAlias("hhJobses")
public class HhJobses {
    @Id
    String _id;

    double hhJobId;
    String jobName;

}
