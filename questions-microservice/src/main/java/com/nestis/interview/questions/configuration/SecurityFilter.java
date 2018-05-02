package com.nestis.interview.questions.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@Component
public class SecurityFilter implements WebFilter {
	
	private final Logger log = LoggerFactory.getLogger(SecurityFilter.class);
	
	private RestTemplate restTemplate;
	
	@Value("${security.endpoint}")
	private String securityEndpoint;

	@Value("${security.tokenHeader}")
	private String tokenHeader;
	
	/**
	 * Class constructor.
	 * @param restTemplate
	 */
	public SecurityFilter(@Autowired RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		// Check if the request has a token header.
		if (exchange.getRequest().getHeaders().containsKey(tokenHeader)) {
			
			// Create a HttpEntity containing the token header value.
			HttpEntity<String> entity = new HttpEntity<>("parameters", exchange.getRequest().getHeaders());
			try {
				// Dispatch a request to security to validate the actual token.
				ResponseEntity<Void> response = restTemplate.exchange(securityEndpoint, HttpMethod.GET, entity, Void.class);
				if (response.getStatusCode().is2xxSuccessful()) {
					// If 200, ok, go through
					return chain.filter(exchange);
				} else {
					// Any other case, return 403
					exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
					return Mono.empty();
				}
			} catch(HttpClientErrorException ex) {
				log.debug(ex.getMessage());
				exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
				return Mono.empty();
			} catch (RestClientException ex) {
				log.debug(ex.getMessage());
				exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
				return Mono.empty();
			}
		}
		// If there is no header, return 403
		exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
		return Mono.empty();
	}

}