package com.nestis.interview.tests.service;

import com.nestis.interview.tests.entity.Token;

/**
 * Token Service interface.
 * @author nestis
 *
 */
public interface TokenService {
	
	/**
	 * Gets the TokenInfo entity for a given token
	 * @param token Token string.
	 * @return
	 */
	Token getToken(String token);
}
