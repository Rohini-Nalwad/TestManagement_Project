package com.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exception.QuestionNotFoundException;
import com.model.Question;
import com.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> getAllQuestions() {
        logger.info("Fetching all questions");
        return questionRepository.findAll();
    }

    @Override
    public Question getQuestionById(Long id) {
        logger.info("Fetching question by id: {}", id);
        return questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Question not found with id: " + id));
    }

    @Override
    public Question saveQuestion(Question question) {
        logger.info("Saving question: {}", question);
        return questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(Long id) {
        logger.info("Deleting question with id: {}", id);
        if (!questionRepository.existsById(id)) {
            throw new QuestionNotFoundException("Question not found with id: " + id);
        }
        questionRepository.deleteById(id);
    }
}
