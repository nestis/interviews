package com.nestis.interview.questions.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.nestis.interview.questions.entity.Answer;
import com.nestis.interview.questions.rest.model.AnswerDto;


@Mapper(componentModel = "spring")
public interface AnswersMapper {
	
	@Mappings({
		@Mapping(source="id", target="id"),
		@Mapping(source="text", target="text")
	})
	AnswerDto answerToAnswerDto(Answer a);
	
	@Mappings({
		@Mapping(source="id", target="id"),
		@Mapping(source="text", target="text")
	})
	Answer answerDtoToAnswer(AnswerDto a);
}
