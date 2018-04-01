package com.nestis.interview.questions.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection="questions")
@Data
public class Question {
	
	@Id
	private String id;
	
	private Integer questionId;
	
	private Integer categoryId;
	
	private String text;
	
	private String correct;
	
	private List<Answer> answers;

}
