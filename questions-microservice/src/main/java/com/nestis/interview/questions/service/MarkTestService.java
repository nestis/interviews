package com.nestis.interview.questions.service;

import com.nestis.interview.questions.service.model.MarkTestDto;

/**
 * Defines the interface for marking a test.
 * @author nestis
 *
 */
public interface MarkTestService {

	void markTest(MarkTestDto message); 
}
