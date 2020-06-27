package com.kz.iitu.bagyt.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UniversityListAndSizePair {

        int totalSize;
        List<UniversityView> universityList;

}
