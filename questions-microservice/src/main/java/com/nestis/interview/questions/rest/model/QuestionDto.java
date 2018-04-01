package com.nestis.interview.questions.rest.model;

import java.util.List;

import lombok.Data;

@Data
public class QuestionDto {

	private Integer questionId;
	
	private Integer categoryId;
	
	private String correct;
	
	private String text;
	
	private List<AnswerDto> answers;
}
