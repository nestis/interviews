package com.nestis.interview.security.service;

import com.nestis.interview.security.service.model.TokenInfo;

/**
 * Defines the interface for TokenService
 * @author nestis
 *
 */
public interface TokenService {

	/**
	 * Returns the token info for a given token.
	 * @param token Token to get its info.
	 * @return TokenInfo object.
	 */
	TokenInfo getTokenInfo(String token);
}
