package com.nestis.interview.security.service.impl;

import org.springframework.stereotype.Service;

import com.nestis.interview.security.service.TestTokenService;
import com.nestis.interview.security.service.TokenInfo;

@Service
public class TestTokenServiceImpl implements TestTokenService {

	@Override
	public TokenInfo getTokenInfo(String token) {
		if ("testToken".equals(token)) {
			TokenInfo tokenInfo =  new TokenInfo();
			return tokenInfo;
		}
		return null;
	}

}
