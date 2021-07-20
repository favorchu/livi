package com.livi.demo.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String jwt;

	public JwtAuthenticationToken(String authToken) {
		super(null);
		this.jwt = authToken;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	public String getJwt() {
		return jwt;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

}
