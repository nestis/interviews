package com.nestis.interview.tests.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * Test Entity.
 * @author nestis
 *
 */
@Data
@Document(collection = "tests")
public class Test {

	@Id
	@JsonIgnore
	private String id;
	
	@JsonIgnore
	private Boolean finished;

	private String leader;
	
	private String name;
	
	private Integer testId;
	
	private Float score;
	
	List<Integer> questions;
}
