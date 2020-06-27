package com.kz.iitu.bagyt.service;

import com.kz.iitu.bagyt.dao.VacanciesStatisticDao;
import com.kz.iitu.bagyt.model.*;
import com.kz.iitu.bagyt.repository.MajorRepository;
import com.kz.iitu.bagyt.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class MajorService {

    public static String HH_API = "https://api.hh.ru/vacancies";
    public static Map<Integer, String> cityMap = new HashMap<Integer, String>(){{ put(159, "Нур-Султан"); put(160, "Алматы"); }};

    @Autowired
    private VacanciesStatisticDao vacanciesStatisticDao;

    @Autowired
    MajorRepository majorRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    private RestTemplate restTemplate;

    public long doRequest(int cityId, List<HhJobses> hhJobsList){
        int vacanciesCount = 0;
        for (HhJobses hhJob: hhJobsList) {
            vacanciesCount += restTemplate.getForObject(HH_API + "?area="+cityId+"&specialization="+hhJob.getHhJobId(), HHResponse.class).getFound();
        }
        return vacanciesCount;
    }

    public void createMajors(List<Major> majors) {
        majorRepository.insert(majors);
    }

    public List<Major> getMajors() {
        return majorRepository.findAll();
    }

    public Set<StatisticMajorByCity> getStatistics(String subject) {

        Set<StatisticMajorByCity> statisticMajorByCities = new HashSet<>();

        List<Major> majors = getMajorsBySubject(subject);
        for (Map.Entry<Integer, String> city: cityMap.entrySet()) {
            long cityVacancyCount = 0;
            List<StatisticMajor> statisticMajorList = new ArrayList<>();
            for (Major major: majors) {

                long majorVacancy=doRequest(city.getKey(), major.getHhJobses());

                StatisticMajor statisticMajor = new StatisticMajor();
                statisticMajor.setMajorName(major.getName());
                statisticMajor.setNumberOfVacancies(majorVacancy);

                statisticMajorList.add(statisticMajor);

                cityVacancyCount += majorVacancy;
            }

            StatisticMajorByCity statisticMajorByCity = new StatisticMajorByCity();
            statisticMajorByCity.setCity(city.getValue());
            statisticMajorByCity.setStatisticMajors(statisticMajorList);
            statisticMajorByCity.setVacancies(cityVacancyCount);
            statisticMajorByCities.add(statisticMajorByCity);
        }

        vacanciesStatisticDao.addVacancySubject(subject, statisticMajorByCities);
        return statisticMajorByCities;
    }

    public List<Major> getMajorsBySubject(String subjectName) {
        return majorRepository.findBySubjectContaining(subjectName);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Major getMajorById(String majorId) {
        return majorRepository.findBy_id(majorId);
    }
}
