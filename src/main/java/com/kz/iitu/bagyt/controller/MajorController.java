package com.kz.iitu.bagyt.controller;

import com.kz.iitu.bagyt.dao.VacanciesStatisticDao;
import com.kz.iitu.bagyt.model.Major;
import com.kz.iitu.bagyt.model.StatisticMajorByCity;
import com.kz.iitu.bagyt.model.Subject;
import com.kz.iitu.bagyt.service.MajorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/rest/major")
@Slf4j
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class MajorController {

    @Autowired
    MajorService majorService;

    @Autowired
    private VacanciesStatisticDao vacanciesStatisticDao;

    @GetMapping
    public List<Major> getMajors() {
        return majorService.getMajors();
    }

    @GetMapping("/statistics")
    public Set<StatisticMajorByCity> getStatistics(@RequestParam(name = "subject") String subject){
        if(vacanciesStatisticDao.getVacancySubject(subject).isEmpty()) {
            return majorService.getStatistics(subject);
        } else
            return vacanciesStatisticDao.getVacancySubject(subject);
    }


    @GetMapping("/{id}")
    public Major getMajorById(@PathVariable(name = "id") String majorId) {
        return majorService.getMajorById(majorId);
    }

    @GetMapping("/subjects")
    public List<Subject> getAllSubjects() {return majorService.getAllSubjects(); };

    @GetMapping("/subjects/{subjectName}")
    public List<Major> getMajors(@PathVariable(name = "subjectName") String subjectName) {
        return majorService.getMajorsBySubject(subjectName);
    }

    @PostMapping
    public void createMajors(@RequestBody List<Major> majorList) {
        majorService.createMajors(majorList);
    }



}
