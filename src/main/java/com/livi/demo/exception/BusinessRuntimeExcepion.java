package com.livi.demo.exception;

/**
 * Throw this class to give bad request 400
 * 
 * @author favorchu
 *
 */
public class BusinessRuntimeExcepion extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BusinessRuntimeExcepion(String message) {
		super(message);

	}

	public BusinessRuntimeExcepion by(Throwable cause) {
		super.initCause(cause);
		return this;
	}
}
