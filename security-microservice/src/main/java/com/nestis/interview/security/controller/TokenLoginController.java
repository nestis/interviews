package com.nestis.interview.security.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nestis.interview.security.service.TokenService;
import com.nestis.interview.security.service.model.TokenInfo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * TokenLoginController.
 * @author nestis
 *
 */
@RestController
@RequestMapping("/auth/testToken")
public class TokenLoginController {

	@Value("${interview.security.secret:testTokenSecret}")
	private String secret;

	@Value("${interview.security.expirationTime:2100000}")
	private Long expirationTime = 2100000L;

	@Value("${interview.security.tokenHeader:Authorization}")
	private String tokenHeader;

	private final String TOKEN_PREFIX = "Bearer";
	
	private TokenService testTokenService;
	
	public TokenLoginController(@Autowired TokenService testTokenService) {
		this.testTokenService = testTokenService;
	}

	/**
	 * Checks if the request param token matches with a current test token stored in database.
	 * If it does, it will generate a jwt that will be returned in a response header.
	 * @param response HttpServletResponse.
	 * @param token Token to check.
	 * @return ResponseEntity<Void>
	 * @throws Exception If something goes south...
	 */
	@GetMapping
	public ResponseEntity<Void> getTestToken(HttpServletResponse response, @RequestParam String token) throws Exception {
		TokenInfo tokenInfo = testTokenService.getTokenInfo(token);
		if (tokenInfo != null) {
			GrantedAuthority granted = new SimpleGrantedAuthority("ROLE_CANDIDATE");
			List<GrantedAuthority> grantedAuths = new ArrayList<>();
			grantedAuths.add(granted);
			final User user = new User("username", "password", true, true, true, true, grantedAuths);
			UsernamePasswordAuthenticationToken us = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

			String tokenHeader = Jwts.builder().setSubject(((User) us.getPrincipal()).getUsername())
					.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
					.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();

			response.addHeader(this.tokenHeader, TOKEN_PREFIX + " " + tokenHeader);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
}
