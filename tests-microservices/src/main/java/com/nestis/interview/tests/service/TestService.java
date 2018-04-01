package com.nestis.interview.tests.service;

import com.nestis.interview.tests.entity.Test;

/**
 * TestService interface.
 * @author nestis
 *
 */
public interface TestService {
	
	/**
	 * Retrieves a test from the given id
	 * @param id Test id
	 * @return Test entity.
	 */
	Test getTest(Integer id);
	
	/**
	 * Saves a new test.
	 * @param test Test entity to save.
	 * @return String with containing the test token.
	 */
	String createTest(Test test);
	
	/**
	 * Marks a test.
	 * @param test Test to be marked.
	 */
	void markTest(Test test);
}
