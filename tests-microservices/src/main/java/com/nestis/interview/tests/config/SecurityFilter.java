package com.nestis.interview.tests.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@Component
public class SecurityFilter implements WebFilter {
	
	private RestTemplate restTemplate;
	
	@Value("${security.endpoint}")
	private String securityEndpoint;

	@Value("${security.tokenHeader}")
	private String tokenHeader;
	
	public SecurityFilter(@Autowired RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		if (exchange.getRequest().getHeaders().containsKey(tokenHeader)) {
			HttpEntity<String> entity = new HttpEntity<>("parameters", exchange.getRequest().getHeaders());
			ResponseEntity<Void> response = restTemplate.exchange(securityEndpoint, HttpMethod.GET, entity, Void.class);
			if (response.getStatusCode().is2xxSuccessful()) {
				return chain.filter(exchange);
			} else {
				exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
				return Mono.empty();
			}
		}
		return Mono.empty();
	}

}
