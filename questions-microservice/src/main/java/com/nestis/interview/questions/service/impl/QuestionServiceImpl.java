package com.nestis.interview.questions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nestis.interview.questions.entity.Question;
import com.nestis.interview.questions.repository.QuestionRepository;
import com.nestis.interview.questions.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	private QuestionRepository categoryRepository;
	
	public QuestionServiceImpl(@Autowired QuestionRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<Question> getQuestions(List<Integer> questionIds) {
		return this.categoryRepository.findByQuestionIdIn(questionIds);
	}

	@Override
	public boolean saveQuestions(List<Question> questions) {
		List<Question> qEntities = this.categoryRepository.saveAll(questions);
		return qEntities != null;
	}
	
}
