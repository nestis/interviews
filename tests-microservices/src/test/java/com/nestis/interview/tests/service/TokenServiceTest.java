package com.nestis.interview.tests.service;

import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nestis.interview.tests.entity.Token;
import com.nestis.interview.tests.repository.TokenRepository;
import com.nestis.interview.tests.service.impl.TokenServiceImpl;

@RunWith(SpringRunner.class)
public class TokenServiceTest {
	
	private TokenService tokenService;
	
	@MockBean
	private TokenRepository tokenRepository;
	
	@Before
	public void setup() {
		this.tokenService = new TokenServiceImpl(tokenRepository);
	}
	
	@Test
	public void shouldReturnAToken() {
		Token token = new Token();
		token.setId("TEST");
		token.setTestId(1);
		token.setToken("TESTTOKEN");
		given(tokenService.getToken(anyString())).willReturn(token);
		
		Token responseToken = tokenService.getToken("1");
		
		assertThat(responseToken.getToken(), comparesEqualTo("TESTTOKEN"));
	}

}
