package com.livi.demo.exception;

/**
 * Throw this class to give a 401 unauthorized
 * 
 * @author favorchu
 *
 */
public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnauthorizedException(String message) {
		super(message);

	}

	public UnauthorizedException by(Throwable cause) {
		super.initCause(cause);
		return this;
	}
}
