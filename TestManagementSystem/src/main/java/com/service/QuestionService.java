package com.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.entity.Category;
import com.entity.Question;



public interface QuestionService {

    List<Question> getAllQuestions();

    Question getQuestionById(Long id);

    Question saveQuestion(Question question);

    void deleteQuestion(Long id);

//    List<Question> importQuestionsFromExcel(InputStream excelInputStream) throws IOException;
//    
//    Optional<Category> findByTitle(String title);

	void importQuestionsFromExcel(List<MultipartFile> multipartFiles) throws IOException;

	

}
