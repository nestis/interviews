package com.nestis.interview.questions.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.nestis.interview.questions.entity.Question;
import com.nestis.interview.questions.rest.model.QuestionDto;

@Mapper(componentModel = "spring")
public interface QuestionsMapper {
	
	@Mappings({
		@Mapping(source="questionId", target="questionId"),
		@Mapping(source="categoryId", target="categoryId"),
		@Mapping(source="text", target="text"),
		@Mapping(source="answers", target="answers"),
	})
	QuestionDto questionToQuestionDto(Question q);
	
	@Mappings({
		@Mapping(source="questionId", target="questionId"),
		@Mapping(source="categoryId", target="categoryId"),
		@Mapping(source="text", target="text"),
		@Mapping(source="correct", target="correct"),
		@Mapping(source="answers", target="answers"),
	})
	Question questionDtoToQuestion(QuestionDto q);
}
