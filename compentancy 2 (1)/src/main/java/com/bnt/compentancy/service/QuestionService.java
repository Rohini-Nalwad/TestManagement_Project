package com.bnt.compentancy.service;

import java.util.Set;

import com.bnt.compentancy.model.Questions;
import com.bnt.compentancy.model.Exam;

public interface QuestionService {
	
	public Questions addQuestion(Questions question);
	
	public Questions updateQuestion(Questions question);
	
	public Set<Questions> getQuestion();
	
	public Set<Questions> getQuestion(Exam exam);
	
	
	public Questions getQuestion(Long questionId);
	
	public void deleteQuestion(Long question);

}
