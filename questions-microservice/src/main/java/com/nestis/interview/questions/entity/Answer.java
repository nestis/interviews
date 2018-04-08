package com.nestis.interview.questions.entity;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
public class Answer {
	
	@Field("id")
	private Integer id;
	
	private String text;
}
