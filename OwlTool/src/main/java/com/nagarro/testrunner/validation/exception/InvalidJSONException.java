/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.validation.exception;

public class InvalidJSONException extends Exception{


	private static final long serialVersionUID = 380763993238781016L;

	public InvalidJSONException() {
		super();
	}

	public InvalidJSONException(String message) {
		super(message);
	}

	public InvalidJSONException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidJSONException(Throwable cause) {
		super(cause);
	}

	protected InvalidJSONException(String message, Throwable cause,
			boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
