package com.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Question;
import com.service.QuestionService;

@WebMvcTest(QuestionController.class)
public class QuestionControllerTest {

    @MockBean
    private QuestionService questionService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllQuestions() throws Exception {
        List<Question> questions = Arrays.asList(
                new Question(1L, "Question 1", "Option 1", "Option 2", "Option 3", "Option 4", "Answer 1", 10),
                new Question(2L, "Question 2", "Option 1", "Option 2", "Option 3", "Option 4", "Answer 2", 15)
        );

        when(questionService.getAllQuestions()).thenReturn(questions);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/questions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetQuestionById() throws Exception {
        long questionId = 1L;
        Question question = new Question(questionId, "Question 1", "Option 1", "Option 2", "Option 3", "Option 4", "Answer 1", 10);

        when(questionService.getQuestionById(questionId)).thenReturn(question);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/questions/{id}", questionId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.questionId").value(questionId));
    }

    @Test
    public void testCreateQuestion() throws Exception {
        Question questionToCreate = new Question(null, "New Question", "Option 1", "Option 2", "Option 3", "Option 4", "Answer", 5);
        Question createdQuestion = new Question(1L, "New Question", "Option 1", "Option 2", "Option 3", "Option 4", "Answer", 5);

        when(questionService.saveQuestion(any(Question.class))).thenReturn(createdQuestion);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(questionToCreate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.questionId").value(1L));
    }

    @Test
    public void testUpdateQuestion() throws Exception {
        // Arrange
        long questionId = 1L;
        Question existingQuestion = new Question(questionId, "Existing Question", "Option 1", "Option 2", "Option 3", "Option 4", "Answer", 10);
        Question updatedQuestion = new Question(questionId, "Updated Question", "Option 1", "Option 2", "Option 3", "Option 4", "Answer", 15);

        when(questionService.getQuestionById(questionId)).thenReturn(existingQuestion);
        when(questionService.saveQuestion(any(Question.class))).thenReturn(updatedQuestion);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/api/questions/{id}", questionId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedQuestion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.questionId").value(questionId))
                .andExpect(jsonPath("$.content").value(updatedQuestion.getContent())); // Check for the updated content
    }


    @Test
    public void testDeleteQuestion() throws Exception {
        long questionId = 1L;

        // Mocking the existence check
        when(questionService.getQuestionById(questionId)).thenReturn(new Question(questionId, "Question", "Option 1", "Option 2", "Option 3", "Option 4", "Answer", 10));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/questions/{id}", questionId))
                .andExpect(status().isOk());

        // Verify that deleteQuestion is called
        verify(questionService).deleteQuestion(questionId);
    }
}
