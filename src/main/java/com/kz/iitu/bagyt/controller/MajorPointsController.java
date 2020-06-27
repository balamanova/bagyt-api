package com.kz.iitu.bagyt.controller;

import com.kz.iitu.bagyt.model.MajorPoints;
import com.kz.iitu.bagyt.model.MajorUniversity;
import com.kz.iitu.bagyt.service.MajorPointsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/rest/major/points")
@Slf4j
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class MajorPointsController {

    @Autowired
    MajorPointsService majorPointsService;

    @GetMapping
    public List<MajorPoints> getUniversity() {
        return majorPointsService.getMajorPoints();
    }

//    @GetMapping("/setmajor")
//    public void setMajorToUniversities() {
//        majorPointsService.setUniversitiesTotMajor();
//    }

    @GetMapping("/set")
    public void setUniversity(@RequestParam String universityId) {
        majorPointsService.setMajorToUniversities(universityId);
    }

    @GetMapping("/filter")
    public Set<MajorUniversity> filterByPoint(@RequestParam boolean kazSchool, @RequestParam boolean selKvota, @RequestParam int point) {
        return majorPointsService.filterByPoint(kazSchool, selKvota, point);
    }

    @PostMapping
    public void createMajors(@RequestBody List<MajorPoints> majorList) {
        majorPointsService.createMajorPoints(majorList);
    }
}
