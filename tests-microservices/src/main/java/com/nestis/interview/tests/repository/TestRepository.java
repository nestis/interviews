package com.nestis.interview.tests.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nestis.interview.tests.entity.Test;

public interface TestRepository extends MongoRepository<Test, String>{

	Test findByToken(String token);
}
