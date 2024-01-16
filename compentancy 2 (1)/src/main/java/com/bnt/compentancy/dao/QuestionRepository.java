package com.bnt.compentancy.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnt.compentancy.entity.Exam;
import com.bnt.compentancy.entity.Questions;

@Repository
public interface QuestionRepository extends JpaRepository<Questions, Long> {

	Set<Questions> findByExam(Exam exam);

}
