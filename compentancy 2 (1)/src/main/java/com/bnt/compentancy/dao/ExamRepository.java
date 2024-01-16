package com.bnt.compentancy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnt.compentancy.entity.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

}
