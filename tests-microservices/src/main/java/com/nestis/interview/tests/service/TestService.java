package com.nestis.interview.tests.service;

import com.nestis.interview.tests.entity.Test;

/**
 * TestService interface.
 * @author nestis
 *
 */
public interface TestService {
	
	/**
	 * Retrieves a test from the given token
	 * @param token Test token
	 * @return Test entity.
	 */
	Test getTestByToken(String token);
	
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
