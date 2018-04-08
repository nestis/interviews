package com.nestis.interview.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class TestTokenAuthenticationFilter extends OncePerRequestFilter {

	private String secret;

	private Long expirationTime = 2100000L;

	private String tokenHeader;

	private final String TOKEN_PREFIX = "Bearer";

	public TestTokenAuthenticationFilter(String secret, Long expirationTime, String tokenHeader) {
		this.secret = secret;
		this.expirationTime = expirationTime;
		this.tokenHeader = tokenHeader;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		Optional<String> testToken = Optional.ofNullable(request.getHeader(tokenHeader));
		if (testToken.isPresent()) {
			String tokenValue = testToken.get().replaceAll(TOKEN_PREFIX + " ", "");
			
			try {
				Claims claims = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(tokenValue).getBody();
				GrantedAuthority granted = new SimpleGrantedAuthority("ROLE_CANDIDATE");
				List<GrantedAuthority> grantedAuths = new ArrayList<>();
				grantedAuths.add(granted);
				final User user = new User(claims.getSubject(), "password", true, true, true, true, grantedAuths);
				
				UsernamePasswordAuthenticationToken us = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(us);
				filterChain.doFilter(request, response);
			} catch (Exception e) {
				response.setStatus(HttpStatus.FORBIDDEN.value());
			}
		} else {
			response.setStatus(HttpStatus.FORBIDDEN.value());
		}	
	}
}
