package com.nestis.interview.questions.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nestis.interview.questions.entity.Question;

public interface QuestionRepository extends MongoRepository<Question, String> {
	
	List<Question> findByQuestionIdIn(List<Integer> questionIds);
}
