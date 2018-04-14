package com.nestis.interview.tests.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nestis.interview.tests.entity.Token;

/**
 * Token Repository.
 * @author nestis
 *
 */
public interface TokenRepository extends MongoRepository<Token, String> {

	/**
	 * Gets a Token entity from a given token.
	 * @param token Token.
	 * @return Token entity.
	 */
	Optional<Token> findByToken(String token);

}
