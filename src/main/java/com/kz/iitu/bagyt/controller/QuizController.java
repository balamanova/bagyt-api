package com.kz.iitu.bagyt.controller;

import com.kz.iitu.bagyt.model.Question;
import com.kz.iitu.bagyt.model.Test;
import com.kz.iitu.bagyt.service.QuizService;
import com.kz.iitu.bagyt.model.TestView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/quiz")
@Slf4j
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class QuizController {

    @Autowired
    QuizService quizService;

    @GetMapping
    public List<TestView> getAll() {
        return quizService.getAll();
    }

    @GetMapping("/{id}")
    public Test getById(@PathVariable(name = "id") String id) {
        return quizService.getById(id);
    }

    @PostMapping
    public void createQuiz(@RequestBody List<Test> testList) {
        quizService.createQuiz(testList);
    }

    @PostMapping("/answer")
    public void createTestResults(@RequestBody List<Question> testResults) {
        quizService.createTestResults(testResults);
    }


}
