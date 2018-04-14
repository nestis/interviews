package com.nestis.interview.tests.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * Token Entity.
 * @author nestis
 *
 */
@Document(collection = "token")
@Data
public class Token {

	@Id
	private String id;

	private String token;
	
	private Integer testId;
}
