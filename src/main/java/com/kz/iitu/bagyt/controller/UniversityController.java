package com.kz.iitu.bagyt.controller;

import com.kz.iitu.bagyt.model.*;
import com.kz.iitu.bagyt.service.UniversityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/university")
@Slf4j
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UniversityController {

    @Autowired
    UniversityService universityService;

    @GetMapping("/all")
    public List<UniversityView> getAllUniversity() {
        return universityService.getAllUniversity();
    }

    @GetMapping("/{id}")
    public University getUniversity(@PathVariable(name = "id") String id) {
        return universityService.getUniversity(id);
    }

    @GetMapping("/cities")
    public List<City> getAllCities() {
        return universityService.getAllCities();
    }

    @GetMapping
    public UniversityListAndSizePair getUniversities(@RequestParam(name = "page") int page,
                                                     @RequestParam(name = "searchText", defaultValue = "", required = false) String searchText,
                                                     @RequestParam(name = "city", defaultValue = "", required = false) String city,
                                                     @RequestParam(name = "major", defaultValue = "", required = false) String majorName,
                                                     @RequestParam(name = "subject", defaultValue = "", required = false) String subject
    ) {

        return universityService.getAll(page, searchText, city, majorName, subject);
    }


    @PostMapping
    public ResponseEntity createUniversity(@RequestBody List<University> universities) {
        universityService.createUniversity(universities);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/comment/{id}")
    public ResponseEntity createComment(@RequestBody Comment comment, @PathVariable(name = "id") String id) {
        universityService.createComment(comment, id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity createUniversity(@RequestBody University university) {
        universityService.update(university);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/getById")
    public List<UniversityView> getUniversitiesById(@RequestBody List<String> universityIdList) {
        return universityService.getUniversitiesById(universityIdList);
    }
}
