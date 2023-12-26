package com.bnt.compentancy.service;

import java.util.Set;

import com.bnt.compentancy.model.Exam;

public interface ExamService {


	public Exam addQuiz(Exam exam);
	
	public Exam updateQuiz(Exam exam);
	
	public Set<Exam> getQuiz();
	
	public Exam getQuiz(Long quizId);
	
	public void deleteQuiz(Long quizId);
}
