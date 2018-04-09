package com.nestis.interview.security.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "token")
@Data
public class Token {
	
	private String id;
	
	private Integer testId;
	
	private String token;
}
