package com.nestis.interview.security.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nestis.interview.security.entity.Token;
import com.nestis.interview.security.repo.TokenRepository;
import com.nestis.interview.security.service.TokenService;
import com.nestis.interview.security.service.model.TokenInfo;

@Service
public class TokenServiceImpl implements TokenService {

	private TokenRepository tokenRepository;
	
	/**
	 * Class constructor.
	 * @param tokenRepository TokenRepository autowired instance.
	 */
	public TokenServiceImpl(@Autowired TokenRepository tokenRepository) {
		this.tokenRepository = tokenRepository;
	}
	
	@Override
	public TokenInfo getTokenInfo(String token) {
		TokenInfo tokenInfo = null;
		Optional<Token> tokenEntity = this.tokenRepository.findByToken(token);
		if (tokenEntity.isPresent()) {
			tokenInfo = new TokenInfo();
			tokenInfo.setTestId(tokenEntity.get().getTestId());
			tokenInfo.setTokenId(tokenEntity.get().getToken());
		}
		return tokenInfo;
	}

}
