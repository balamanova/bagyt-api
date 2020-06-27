package com.kz.iitu.bagyt.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class StatisticMajorByCity implements Serializable {
    String city;
    long vacancies;
    List<StatisticMajor> statisticMajors;
}
