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
	
	private Integer testId;
	
	List<Integer> questions;
	
	private String name;
	
	private String leader;
	
	@JsonIgnore
	private Boolean finished;
}
