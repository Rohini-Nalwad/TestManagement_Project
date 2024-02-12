package com.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.QuestionNotFoundException;
import com.model.Question;
import com.service.QuestionService;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        logger.info("Fetching all questions");
        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        try {
            logger.info("Fetching question by id: {}", id);
            Question question = questionService.getQuestionById(id);
            return ResponseEntity.ok(question);
        } catch (QuestionNotFoundException ex) {
            return ResponseEntity.status(404).body(null); // or return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Question createQuestion(@RequestBody Question question) {
        logger.info("Creating question: {}", question);
        return questionService.saveQuestion(question);
    }

    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        logger.info("Updating question with id {}: {}", id, question);
        question.setQuestionId(id);
        return questionService.saveQuestion(question);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        try {
            logger.info("Deleting question with id: {}", id);
            questionService.deleteQuestion(id);
        } catch (QuestionNotFoundException ex) {
            logger.error("Error deleting question with id {}: {}", id, ex.getMessage());
        }
    }
}
