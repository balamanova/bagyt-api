package com.kz.iitu.bagyt.dao;

import com.kz.iitu.bagyt.model.StatisticMajorByCity;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Repository
public class VacanciesStatisticDao {

    private static final String KEY = "vacancy";

    @Resource(name="redisTemplate")
    private SetOperations<String, Set<StatisticMajorByCity>> redisOperations;

    public void addVacancySubject(String subject, Set<StatisticMajorByCity> statisticMajorByCitySet) {
        redisOperations.add(subject, statisticMajorByCitySet);
    }

    public Set<StatisticMajorByCity> getVacancySubject(String subject) {
        Set<StatisticMajorByCity> statisticMajorByCitySet = new HashSet<>();
        try{
            statisticMajorByCitySet = redisOperations.members(subject).iterator().next();
        } catch(Exception e) {
            System.out.println("Error " + e);
        }
        return statisticMajorByCitySet;
    }

}
