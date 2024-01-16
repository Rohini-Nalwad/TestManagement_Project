package com.bnt.compentancy.service;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnt.compentancy.dao.ExamRepository;
import com.bnt.compentancy.entity.Exam;


@Service
public class ExamServiceImpl implements ExamService{
	
	@Autowired
	private ExamRepository repository;

	@Override
	public Exam addQuiz(Exam exam) {
		// TODO Auto-generated method stub
		return this.repository.save(exam);
	}

	@Override
	public Exam updateQuiz(Exam exam) {
		// TODO Auto-generated method stub
		return this.repository.save(exam);
	}

	@Override
	public Set<Exam> getQuiz() {
		// TODO Auto-generated method stub
		return  new LinkedHashSet<>(this.repository.findAll());
	}

	@Override
	public Exam getQuiz(Long quizId) {
		// TODO Auto-generated method stub
		return this.repository.findById(quizId).get();
	}

	@Override
	public void deleteQuiz(Long quizId) {
		// TODO Auto-generated method stub
		/*
		 * Exam exam=new Exam(); exam.setQid(quizId);
		 */
		this.repository.deleteById(quizId);
	}
	
	

}
