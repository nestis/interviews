package com.nestis.interview.tests.service.model;

import java.util.Map;

import lombok.Data;

/**
 * MarkTestDto. Defines a test and the answers the candidate has given.
 * @author nestis
 *
 */
@Data
public class MarkTestDto {

	/**
	 * Test id
	 */
	private Integer testId;
	
	/**
	 * Given answers.
	 */
	private Map<Integer, String> answers;
}
