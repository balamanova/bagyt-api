package com.kz.iitu.bagyt.service;

import com.kz.iitu.bagyt.model.*;
import com.kz.iitu.bagyt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UniversityService {

    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    UniversityViewRepository universityViewRepository;

    @Autowired
    MajorPointsService majorPointsService;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    public void createUniversity(List<University> university) {
        universityRepository.insert(university);
    }


    public List<UniversityView> getAllUniversity() {
        return universityViewRepository.findAll();
    }

    public UniversityListAndSizePair getAll(int page,String searchText, String city, String majorName, String subject) {

        int size = page * 15;

        List<UniversityView> universityViews = (city.equals("") ?
                universityViewRepository.findAllByNameContaining(searchText) : universityViewRepository.getAllByCityAndNameStartingWith(city, searchText));

        if(!majorName.equals("")||!subject.equals("")) {
            universityViews = universityViews.stream().filter(universityView -> universityView.getMajors().stream().anyMatch(major ->
                    majorName.equals("")
                            ? subject.equals(major.getProfilSubject()) || subject.equals(major.getSubject()) : majorName.equals(major.getName())
            )).collect(Collectors.toList());
        }
        int universitySize = universityViews.size();
        if(universitySize < size && size - universityViews.size() > 15) return new UniversityListAndSizePair(universitySize, new ArrayList<>());
         else if(universitySize < size) return new UniversityListAndSizePair(universitySize, universityViews.subList(size-15,universityViews.size()));
         else return new UniversityListAndSizePair(universitySize, universityViews.subList(size-15,size));
    }

    public void createComment(Comment comment, String id) {
        comment.setCreatedDate(LocalDateTime.now());
        commentRepository.insert(comment);
        University university = universityRepository.findBy_id(id);
        List<Comment> commentList = university.getCommentList();
        if(commentList == null) {
            commentList = new ArrayList<>();
        }
        //TODO
        commentList.add(comment);
        university.setCommentList(commentList);
        universityRepository.save(university);

    }

    public University getUniversity(String id) {
        University university = universityRepository.findBy_id(id);
        MajorUniversity majorUniversity = new MajorUniversity();
        majorUniversity.set_id(university.get_id());
        university.setMajorPoints(majorPointsService.getMajorPointByUniversity(majorUniversity));
        return university;
    }

    public void update(University university) {
        List<MajorPoints> majorPointsSet = university.getMajorPoints().stream().filter(majorPoint -> majorPoint.get_id().equals("_id") ).collect(Collectors.toList());
        majorPointsService.createMajorPoints(majorPointsSet);
        universityRepository.save(university);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public List<UniversityView> getUniversitiesById(List<String> universityIdList) {
        return universityViewRepository.findAllBy_idIsIn(universityIdList);
    }
}
