package com.bnt.compentancy.service;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnt.compentancy.dao.QuestionRepository;
import com.bnt.compentancy.entity.Exam;
import com.bnt.compentancy.entity.Questions;

@Service
public class QuestionServiceImpl implements QuestionService{
	
	
	@Autowired
	private QuestionRepository repository;

	@Override
	public Questions addQuestion(Questions question) {
		// TODO Auto-generated method stub
		return this.repository.save(question);
	}

	@Override
	public Questions updateQuestion(Questions question) {
		// TODO Auto-generated method stub
		return this.repository.save(question);
	}

	@Override
	public Set<Questions> getQuestion() {
		// TODO Auto-generated method stub
		return new LinkedHashSet<>(this.repository.findAll());
	}

	@Override
	public Questions getQuestion(Long questionId) {
		// TODO Auto-generated method stub
		return this.repository.findById(questionId).get();
	}

	@Override
	public void deleteQuestion(Long question) {
		// TODO Auto-generated method stub
		Questions questions=new Questions();
		questions.setQuesId(question);
		this.repository.delete(questions);
		
	}

	@Override
	public Set<Questions> getQuestion(Exam exam) {
		// TODO Auto-generated method stub
		return this.repository.findByExam(exam);
	}


	
}
