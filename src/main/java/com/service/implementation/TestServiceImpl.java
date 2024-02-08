package com.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Tests;
import com.repository.TestRepository;
import com.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestRepository repository;

	@Override
	public Tests addTest(Tests exam) {
		return repository.save(exam);
	}

	@Override
	public Tests updateTest(Tests exam) {
		return repository.save(exam);
	}

	@Override
	public List<Tests> getTest() {
		return new ArrayList<>(repository.findAll());
	}

	@Override
	public Tests getTestById(Long testId) {
		return repository.findById(testId).get();
	}

	@Override
	public void deleteTestById(Long testId) {
		repository.deleteById(testId);
	}

}