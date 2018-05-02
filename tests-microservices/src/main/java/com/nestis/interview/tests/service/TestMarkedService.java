package com.nestis.interview.tests.service;

import com.nestis.interview.tests.service.model.MarkedTestDto;

/**
 * TestMarked Service interface.
 * @author nestis
 *
 */
public interface TestMarkedService {

	/**
	 * Handles a marked test.
	 * @param test MarkedTest. Contains a testId and its score.
	 */
	void handleMarkedTest(MarkedTestDto test);
}
