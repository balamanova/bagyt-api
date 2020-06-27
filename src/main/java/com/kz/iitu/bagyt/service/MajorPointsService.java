package com.kz.iitu.bagyt.service;


import com.kz.iitu.bagyt.model.*;
import com.kz.iitu.bagyt.repository.MajorPointsRepository;
import com.kz.iitu.bagyt.repository.MajorRepository;
import com.kz.iitu.bagyt.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class MajorPointsService {

    @Autowired
    MajorPointsRepository majorPointsRepository;

    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    MajorRepository majorRepository;

    public List<MajorPoints> getMajorPoints() {
        return majorPointsRepository.findAll();
    }

    public List<MajorPoints> getMajorPointByUniversity(MajorUniversity university) {
        return majorPointsRepository.findByUniversity(university);
    }


    public void createMajorPoints(List<MajorPoints> majorList) {
        majorPointsRepository.insert(majorList);
    }

    public void setMajorToUniversities(String universitiyId){
//        List<University> universitySet = (List<University>) universityRepository.findBy_id(universitiyId);
        University university = universityRepository.findBy_id(universitiyId);
//        for(University university: universitySet) {
            Set<UniversityMajor> majors = new HashSet<>();
            List<MajorPoints> majorPoints = majorPointsRepository.findByUniversity__id(university.get_id());

            for(MajorPoints majorPoints1: majorPoints) {
                majors.add(majorPoints1.getMajor());
            }

//            university.setMajors(majors);
//        }

        universityRepository.save(university);
    }

    public Set<MajorUniversity> filterByPoint(boolean kazSchool, boolean selKvota, int point) {
        List<MajorPoints> majorPointsList = new ArrayList<>();
        if(kazSchool && selKvota) {
            majorPointsList= majorPointsRepository.findByKazSelPointLessThanEqual(point);
        }
        if(!kazSchool && selKvota) {
            majorPointsList = majorPointsRepository.findByRusSelPointLessThanEqual(point);
        }
        if(kazSchool) {
            majorPointsList = majorPointsRepository.findByKazPointLessThanEqual(point);
        }
        if(!kazSchool) {
            majorPointsList = majorPointsRepository.findByRusPointLessThanEqual(point);
        }
        return majorPointsList.stream().map(MajorPoints::getUniversity).collect(Collectors.toSet());
    }

//    public void setUniversitiesTotMajor(){
//     List<Major> majors = (List<Major>) majorRepository.findAll();
////        University university = universityRepository.findBy_id(universitiyId);
//        for(Major major: majors) {
////        Set<Major> majors = new HashSet<>();
//        Set<UniversityView> majorPoints = universityRepository.findByMajors(major);
//
////        for(MajorPoints majorPoints1: majorPoints) {
////            majors.add(majorPoints1.getMajor());
////        }
////
//              major.setUniversities(majorPoints);
//            majorRepository.save(major);
//          }
//
//    }

}
