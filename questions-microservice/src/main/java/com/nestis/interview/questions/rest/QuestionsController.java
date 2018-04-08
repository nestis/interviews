package com.nestis.interview.questions.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nestis.interview.questions.entity.Question;
import com.nestis.interview.questions.mappers.QuestionsMapper;
import com.nestis.interview.questions.rest.model.QuestionDto;
import com.nestis.interview.questions.service.QuestionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Question Rest Controller
 * @author nestis
 *
 */
@RestController
@RequestMapping("${endpoints.questions:question}")
public class QuestionsController {

	private QuestionsMapper questionsMapper;
	
	private QuestionService questionService;
	
	public QuestionsController(@Autowired QuestionService questionService, @Autowired QuestionsMapper questionMapper) {
		this.questionService = questionService;
		this.questionsMapper = questionMapper;
	}
	
	/**
	 * Get the questions body for the given question ids.
	 * @param questionIds Question ids to get the info.
	 * @return Flux of QuestionDto objects.
	 */
	@PostMapping
	public Flux<QuestionDto> getQuestions(@RequestBody List<Integer> questionIds) {
		List<QuestionDto> questionsOut = this.questionService.getQuestions(questionIds).parallelStream().map(q -> {
			return this.questionsMapper.questionToQuestionDto(q);
		}).collect(Collectors.toList());
		
		return Flux.fromIterable(questionsOut);
	}
	
	/**
	 * Add a new question.
	 * @param questions Question to add.
	 * @return Boolean.
	 */
	@PostMapping(value = "/add")
	public Mono<Boolean> addQuestions(@RequestBody List<QuestionDto> questions) {
		List<Question> questionEntities = questions.stream().map(q -> {
			return this.questionsMapper.questionDtoToQuestion(q);
		}).collect(Collectors.toList());
		
		return Mono.just(this.questionService.saveQuestions(questionEntities));
	}
}
