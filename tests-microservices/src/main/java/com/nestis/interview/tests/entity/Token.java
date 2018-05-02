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
	
	/** Test Id */
	private Integer testId;

	/** Token associated to a test */
	private String token;
}
