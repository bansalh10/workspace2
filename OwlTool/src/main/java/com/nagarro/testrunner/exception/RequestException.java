/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.exception;

/**
 * The Class RequestException.
 *
 * @author anujmehra
 */
public class RequestException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3273151628423829004L;

	/**
	 * Instantiates a new request exception.
	 */
	public RequestException() {
		super();
	}

	/**
	 * Instantiates a new request exception.
	 *
	 * @param message the message
	 */
	public RequestException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new request exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public RequestException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new request exception.
	 *
	 * @param cause the cause
	 */
	public RequestException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new request exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	protected RequestException(String message, Throwable cause,
			boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
