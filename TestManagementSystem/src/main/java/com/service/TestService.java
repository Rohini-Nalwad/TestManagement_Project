package com.service;

import java.util.List;

import com.entity.Tests;

public interface TestService {

	public Tests addTest(Tests test);

	public Tests updateTest(Tests test);

	public List<Tests> getTest();

	public Tests getTestById(Long testId);

	public void deleteTestById(Long testId);

	
}
