package com.nestis.interview.tests.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nestis.interview.tests.entity.Test;

/**
 * Test repository.
 * @author nestis
 *
 */
public interface TestRepository extends MongoRepository<Test, String>{

	/**
	 * Gets a Test entity from a given test id.
	 * @param testId Test id.
	 * @return Test entity.
	 */
	Optional<Test> findByTestId(Integer testId);
	
	/**
	 * Gets the Test entity with the top id
	 * @return Test entity.
	 */
	Test findFirstByOrderByTestIdDesc();

}
