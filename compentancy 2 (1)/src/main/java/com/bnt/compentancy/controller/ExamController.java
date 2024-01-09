package com.bnt.compentancy.controller;

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

import com.bnt.compentancy.model.Exam;
import com.bnt.compentancy.service.ExamService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class ExamController {
	
	
	@Autowired
	ExamService service;
	
	
	@PostMapping("/")
	public ResponseEntity<Exam> addQuiz(@RequestBody Exam exam){
		
		Exam quiz1= service.addQuiz(exam);
		//this is Exam Controller 
		return ResponseEntity.ok(quiz1);
		
	}
	
	
	@GetMapping("/{quizId}")
	public Exam getQuiz(@PathVariable("quizId") Long quizId){
		System.out.println("In the service");
		return this.service.getQuiz(quizId);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getQuizs(){
				
		return ResponseEntity.ok(this.service.getQuiz());
		
	}
	
	@PutMapping("/")
	public Exam updateQuiz(@RequestBody Exam exam) {
		
		return this.service.updateQuiz(exam);
		
	}
	
	@DeleteMapping("/{quizId}")
	public void deleteQuiz(@PathVariable("quizId") Long quizId) {
		this.service.deleteQuiz(quizId);
	}
	

}
