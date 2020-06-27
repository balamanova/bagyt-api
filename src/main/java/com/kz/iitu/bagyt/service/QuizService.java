package com.kz.iitu.bagyt.service;

import com.kz.iitu.bagyt.model.Question;
import com.kz.iitu.bagyt.model.Test;
import com.kz.iitu.bagyt.model.TestView;
import com.kz.iitu.bagyt.repository.AnswerRepository;
import com.kz.iitu.bagyt.repository.QuizRepository;
import com.kz.iitu.bagyt.repository.TestViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    TestViewRepository testViewRepository;

    @Autowired
    AnswerRepository answerRepository;

    public List<TestView> getAll() {
        return testViewRepository.findAll();
    }
    public Test getById(String id) {
        Test test = quizRepository.getBy_id(id);
        return test;
    }

    public void createQuiz(List<Test> testList) {
        quizRepository.insert(testList);
    }

    public void createTestResults(List<Question> testResults) {
        answerRepository.insert(testResults);
    }
}
