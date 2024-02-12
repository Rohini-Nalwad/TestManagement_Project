//package com.testController;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.doThrow;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.controller.QuestionController;
//import com.entity.Question;
//import com.exception.QuestionNotFoundException;
//import com.service.QuestionService;
//
//@ExtendWith(MockitoExtension.class)
//class QuestionControllerTest {
//
//    @InjectMocks
//    private QuestionController questionController;
//
//    @Mock
//    private QuestionService questionService;
//
//    @Test
//    void getAllQuestions() {
//
//        List<Question> mockQuestions = new ArrayList<>();
//        Mockito.when(questionService.getAllQuestions()).thenReturn(mockQuestions);
//
//        List<Question> result = questionController.getAllQuestions();
//
//        assertEquals(mockQuestions, result);
//    }
//
//    @Test
//    void getQuestionById_ExistingQuestion() {
//       
//        Long questionId = 1L;
//        Question mockQuestion = new Question();
//        mockQuestion.setQuestionId(questionId);
//        Mockito.when(questionService.getQuestionById(questionId)).thenReturn(mockQuestion);
//
//        ResponseEntity<Question> result = questionController.getQuestionById(questionId);
//
//        assertEquals(HttpStatus.OK, result.getStatusCode());
//        assertEquals(mockQuestion, result.getBody());
//    }
//
//    @Test
//    void getQuestionById_NonExistingQuestion() {
//
//        Long questionId = 1L;
//        Mockito.when(questionService.getQuestionById(questionId)).thenThrow(new QuestionNotFoundException("Question not found", null));
//
//        ResponseEntity<Question> result = questionController.getQuestionById(questionId);
//
//        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
//        assertEquals(null, result.getBody());
//    }
//
//    @Test
//    void createQuestion() {
//
//        Question mockQuestion = new Question();
//        Mockito.when(questionService.saveQuestion(Mockito.any(Question.class))).thenReturn(mockQuestion);
//
//        Question result = questionController.createQuestion(new Question());
//
//        assertEquals(mockQuestion, result);
//    }
//
//    @Test
//    void updateQuestion() {
//        Long questionId = 1L;
//        Question mockQuestion = new Question();
//        mockQuestion.setQuestionId(questionId);
//        Mockito.when(questionService.saveQuestion(Mockito.any(Question.class))).thenReturn(mockQuestion);
//
//        Question result = questionController.updateQuestion(questionId, new Question());
// 
//        assertEquals(mockQuestion, result);
//    }
//
//    @Test
//    void deleteQuestion_ExistingQuestion() {
//
//        Long questionId = 1L;
//
//        questionController.deleteQuestion(questionId);
//
//        verify(questionService, times(1)).deleteQuestion(questionId);
//    }
//
//    @Test
//    void deleteQuestion_NonExistingQuestion() {
//        Long questionId = 1L;
//        doThrow(new QuestionNotFoundException("Question not found", null)).when(questionService).deleteQuestion(questionId);
//
//        questionController.deleteQuestion(questionId);
//
//        verify(questionService, times(1)).deleteQuestion(questionId);
//    }
//
//    @Test
//    void importQuestions() throws IOException {
//      
//        MultipartFile mockFile = new MockMultipartFile("file", "test.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "content".getBytes());
//        List<Question> mockQuestions = new ArrayList<>();
//        Mockito.when(questionService.importQuestionsFromExcel(Mockito.any(InputStream.class))).thenReturn(mockQuestions);
//
//        ResponseEntity<List<Question>> result = questionController.importQuestions(mockFile);
//        assertEquals(HttpStatus.OK, result.getStatusCode());
//        assertEquals(mockQuestions, result.getBody());
//    }
//}
//
