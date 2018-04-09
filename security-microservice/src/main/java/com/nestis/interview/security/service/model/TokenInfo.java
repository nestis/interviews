package com.nestis.interview.security.service.model;

import lombok.Data;

/**
 * Defines a TokenInfo object.
 * @author nestis
 *
 */
@Data
public class TokenInfo {
	
	/**
	 * Test id relative to the token.
	 */
	private Integer testId;
	
	/**
	 * Test token.
	 */
	private String tokenId;
}
