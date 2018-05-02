package com.nestis.interview.questions.service.model;

import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MarkTestDto {

	private Integer testId;
	
	/**
	 * Given answers.
	 */
	private Map<Integer, String> answers;
}
