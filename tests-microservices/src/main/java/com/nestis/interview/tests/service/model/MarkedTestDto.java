package com.nestis.interview.tests.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Defines a marked test.
 * @author nestis
 *
 */
@Data
public class MarkedTestDto {

	/** Test percent score */
	@JsonProperty("percent")
	private Float score;
	
	/** Test id */
	private Integer testId;
}
