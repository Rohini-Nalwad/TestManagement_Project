package com.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.model.Question;
import com.repository.QuestionRepository;

@SpringBootTest
public class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestionServiceImpl questionService;

    @Test
    public void testGetAllQuestions() {
        when(questionRepository.findAll()).thenReturn(Arrays.asList(
                new Question(1L, "Question 1", "Option 1", "Option 2", "Option 3", "Option 4", "Answer 1", 10),
                new Question(2L, "Question 2", "Option 1", "Option 2", "Option 3", "Option 4", "Answer 2", 15)
        ));

        assertEquals(2, questionService.getAllQuestions().size());
    }

    @Test
    public void testGetQuestionById() {
        long questionId = 1L;
        Question question = new Question(questionId, "Question 1", "Option 1", "Option 2", "Option 3", "Option 4", "Answer 1", 10);

        when(questionRepository.findById(questionId)).thenReturn(Optional.of(question));

        assertEquals(question, questionService.getQuestionById(questionId));
    }

    @Test
    public void testSaveQuestion() {
        Question questionToSave = new Question(null, "New Question", "Option 1", "Option 2", "Option 3", "Option 4", "Answer", 5);
        Question savedQuestion = new Question(1L, "New Question", "Option 1", "Option 2", "Option 3", "Option 4", "Answer", 5);

        when(questionRepository.save(questionToSave)).thenReturn(savedQuestion);

        assertEquals(savedQuestion, questionService.saveQuestion(questionToSave));
    }

    @Test
    public void testDeleteQuestion() {
        long questionId = 1L;

        when(questionRepository.existsById(questionId)).thenReturn(true);

        questionService.deleteQuestion(questionId);

        Mockito.verify(questionRepository).deleteById(questionId);
    }
}

