package com.nestis.interview.questions.service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MarkTestDto {

	private Integer testId;
	
	private Integer[] answers;
}
