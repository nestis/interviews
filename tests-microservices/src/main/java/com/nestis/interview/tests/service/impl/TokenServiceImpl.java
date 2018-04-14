package com.nestis.interview.tests.service.impl;

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
		return tokenRepository.findByToken(token);
	}
}
