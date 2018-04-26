package com.nestis.interview.tests.service.model;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;

/**
 * MarkTestDto. Defines a test and the answers the candidate has given.
 * @author nestis
 *
 */
@Data
public class FinishTestDto implements Serializable {

	/**
	 * Test id
	 */
	private Integer testId;
	
	/**
	 * Given answers.
	 */
	private Map<Integer, String> answers;
}
