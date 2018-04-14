package com.nestis.interview.tests.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nestis.interview.tests.entity.Token;
import com.nestis.interview.tests.repository.TokenRepository;
import com.nestis.interview.tests.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {

	private TokenRepository tokenRepository;
	
	public TokenServiceImpl(@Autowired TokenRepository tokenRepository) {
		this.tokenRepository = tokenRepository;
	}
	
	@Override
	public Token getToken(String token) {
		Optional<Token> tokenEntity = this.tokenRepository.findByToken(token);
		return tokenEntity.orElseThrow(() -> new RuntimeException("Token not found"));
	}
}
