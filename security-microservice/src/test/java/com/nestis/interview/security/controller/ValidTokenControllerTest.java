package com.nestis.interview.security.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.nestis.interview.security.SecurityApplication;
import com.nestis.interview.security.service.TokenService;
import com.nestis.interview.security.service.model.TokenInfo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecurityApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ValidTokenControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TokenService tokenService;
	
	@Test
	public void shouldGetAValidJwtToken() throws Exception {
		TokenInfo token = new TokenInfo();
		token.setTestId(1);
		token.setTokenId("tokenId");
		given(tokenService.getTokenInfo(anyString())).willReturn(token);

		String headerValue = this.mockMvc.perform(get("/auth/testToken?token=testToken"))
        		.andReturn().getResponse().getHeader("Authorization");
		
		this.mockMvc.perform(get("/auth/validToken").header("Authorization", headerValue)).andExpect(status().isOk());
	}
	
	@Test
	public void shouldRefuseAnInvalidToken() throws Exception {
		TokenInfo token = new TokenInfo();
		token.setTestId(1);
		token.setTokenId("tokenId");
		given(tokenService.getTokenInfo(anyString())).willReturn(token);

		String headerValue = this.mockMvc.perform(get("/auth/testToken?token=testToken"))
        		.andReturn().getResponse().getHeader("Authorization").replaceAll("\\d", "a");
		
		this.mockMvc.perform(get("/auth/validToken").header("Authorization", headerValue)).andExpect(status().isForbidden());
	}
}
