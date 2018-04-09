package com.nestis.interview.security.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nestis.interview.security.entity.Token;

public interface TokenRepository extends MongoRepository<Token, String> {

	/**
	 * Search a token entity using the token string passed as parameter.
	 * @param token String token.
	 * @return Token entity.
	 */
	Optional<Token> findByToken(String token);
}
