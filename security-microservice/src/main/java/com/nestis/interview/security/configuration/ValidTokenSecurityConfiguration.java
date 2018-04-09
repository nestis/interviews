package com.nestis.interview.security.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nestis.interview.security.filter.TokenAuthenticationFilter;

/**
 * Configures a TokenAuthenticationFilter instance on /auth/validToken url.
 * Requests to that endpoint will be intercepted by the filter, which will check the authorization header for a valid jwt.
 * Invalid or empty tokens will return a 403. Valid tokens will make the request go to ValidTokenController, that will simply return a 200.
 * @author nestis
 *
 */
@Configuration
@EnableWebSecurity
@Order(3)
public class ValidTokenSecurityConfiguration extends WebSecurityConfigurerAdapter {


	@Value("${security.secret:testTokenSecret}")
	private String secret;

	@Value("${security.expirationTime:2100000}")
	private Long expirationTime = 2100000L;

	@Value("${security.tokenHeader:Authorization}")
	private String tokenHeader;
	
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/auth/validToken").authorizeRequests()
			.anyRequest().authenticated()
			.and()
	        .addFilterBefore(new TokenAuthenticationFilter(secret, expirationTime, tokenHeader), UsernamePasswordAuthenticationFilter.class);
	}
}
