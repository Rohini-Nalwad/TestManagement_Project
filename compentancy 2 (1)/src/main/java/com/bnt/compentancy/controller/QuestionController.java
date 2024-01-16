package com.bnt.compentancy.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnt.compentancy.model.Questions;
import com.bnt.compentancy.model.Exam;
import com.bnt.compentancy.service.QuestionService;
import com.bnt.compentancy.service.ExamService;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

	
	
	@Autowired
	QuestionService service;
	
	@Autowired
	ExamService examService;
	

	@PostMapping("/")
	public ResponseEntity<Questions> addCategory(@RequestBody Questions question){
		
		Questions question1= service.addQuestion(question);
		
		return ResponseEntity.ok(question1);
		
	}
	
	@GetMapping("/{questionId}")
	public Questions getQuestion(@PathVariable("questionId") Long questionId){
		
		return this.service.getQuestion(questionId);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getQuestions(){
				
		return ResponseEntity.ok(this.service.getQuestion());
		
	}
	
	@PutMapping("/")
	public Questions updateCategory(@RequestBody Questions question) {
		
		return this.service.updateQuestion(question);
		
	}
	
	
	@GetMapping("/quiz/{qid}")
	public ResponseEntity<?> getQuestionsQuiz(@PathVariable("qid") Long qid){
		return null;
		
		
		/*
		 * Exam exam=this.examService.getQuiz(qid); Set<Questions>
		 * questionsOfQuiz=exam.getQuestions(); List<Questions> list= new
		 * ArrayList<Questions>(questionsOfQuiz);
		 * 
		 * if(list.size() > Integer.parseInt(exam.getNumberofQuestions())) {
		 * list=list.subList(0, Integer.parseInt(exam.getNumberofQuestions()+1)); }
		 * Collections.shuffle(list); return ResponseEntity.ok(list);
		 */
		
	}
	

	@GetMapping("/quiz/all/{qid}")
	public ResponseEntity<?> getQuestionsQuizManager(@PathVariable("qid") Long qid){
		
		
		/*
		 * Exam exam=this.examService.getQuiz(qid); Set<Questions>
		 * questionsOfQuiz=exam.getQuestions(); List<Questions> list= new
		 * ArrayList<Questions>(questionsOfQuiz);
		 * 
		 * if(list.size() > Integer.parseInt(exam.getNumberofQuestions())) {
		 * list=list.subList(0, Integer.parseInt(exam.getNumberofQuestions()+1)); }
		 * Collections.shuffle(list); return ResponseEntity.ok(list);
		 */
		
		Exam exam=new Exam();
		exam.setQid(qid);
		Set<Questions> questions = this.service.getQuestion(exam);
		return ResponseEntity.ok(questions);
		
		
	}
	
	
	
	
	
	@DeleteMapping("/{categoryId}")
	public void deleteCategory(@PathVariable("categoryId") Long categoryId) {
		this.service.deleteQuestion(categoryId);
	}

}
