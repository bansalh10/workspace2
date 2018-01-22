/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.exception;

/**
 * The Class ResponseException.
 *
 * @author anujmehra
 */
public class ResponseException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2591092819081540753L;

	/**
	 * Instantiates a new response exception.
	 */
	public ResponseException() {
		super();
	}

	/**
	 * Instantiates a new response exception.
	 *
	 * @param message the message
	 */
	public ResponseException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new response exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public ResponseException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new response exception.
	 *
	 * @param cause the cause
	 */
	public ResponseException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new response exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	protected ResponseException(String message, Throwable cause,
			boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
