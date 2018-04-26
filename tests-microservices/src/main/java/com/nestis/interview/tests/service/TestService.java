package com.nestis.interview.tests.service;

import com.nestis.interview.tests.entity.Test;
import com.nestis.interview.tests.service.model.FinishTestDto;

/**
 * TestService interface.
 * @author nestis
 *
 */
public interface TestService {
	
	/**
	 * Retrieves a test from the given id
	 * @param token Test id
	 * @return Test entity.
	 */
	Test getTestById(Integer testId);
	
	/**
	 * Saves a new test.
	 * @param test Test entity to save.
	 * @return String with containing the test token.
	 */
	String createTest(Test test);
	
	/**
	 * Finish a test.
	 * @param test Test to be set as finished.
	 */
	boolean finishTest(FinishTestDto test);
}
