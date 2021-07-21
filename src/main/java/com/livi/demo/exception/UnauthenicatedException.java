package com.livi.demo.exception;

public class UnauthenicatedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnauthenicatedException(String message) {
		super(message);

	}

	public UnauthenicatedException by(Throwable cause) {
		super.initCause(cause);
		return this;
	}
}
