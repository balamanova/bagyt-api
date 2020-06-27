package com.kz.iitu.bagyt.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class StatisticMajor implements Serializable {
   String majorName;
   long numberOfVacancies;
}
