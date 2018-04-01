package com.nestis.interview.questions.service;

import java.util.List;

import com.nestis.interview.questions.entity.Question;

/**
 * QuestionService. Manages all the operations regarding Question entities.
 * @author nestis
 *
 */
public interface QuestionService {

	/**
	 * Returns all the questions within the range passed as parameter.
	 * @param questionIds Array containing the question ids to search.
	 * @return List.
	 */
	List<Question> getQuestions(List<Integer> questionIds);
	
	/**
	 * Saves or updates the questions passed as parameter.
	 * @param questions List of Question to save/update.
	 * @return Boolean indicating if the operation was successful.
	 */
	boolean saveQuestions(List<Question> questions);
}
