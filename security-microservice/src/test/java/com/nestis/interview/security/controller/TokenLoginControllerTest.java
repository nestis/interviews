package com.nestis.interview.security.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.nestis.interview.security.service.TokenService;
import com.nestis.interview.security.service.model.TokenInfo;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TokenLoginController.class, secure = false)
public class TokenLoginControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

	@MockBean
	private TokenService tokenService;
	
	@Test
	public void shouldGetJwtToken() throws Exception {
		TokenInfo token = new TokenInfo();
		token.setTestId(1);
		token.setTokenId("tokenId");
		given(tokenService.getTokenInfo(anyString())).willReturn(token);

        this.mockMvc.perform(get("/auth/testToken?token=testToken")).andExpect(status().isOk())
                .andExpect(header().exists("Authorization"));
	}
	
	@Test
	public void shouldReturnUnauthorized() throws Exception {
		given(tokenService.getTokenInfo(anyString())).willReturn(null);

        this.mockMvc.perform(get("/auth/testToken?token=testToken"))
                .andExpect(status().isUnauthorized());
	}
}
