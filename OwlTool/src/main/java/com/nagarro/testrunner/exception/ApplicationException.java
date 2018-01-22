/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.exception;

/**
 * The Class ApplicationException.
 *
 * @author anujmehra
 */
public class ApplicationException extends Exception {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1713399183083925725L;

	/**
	 * Instantiates a new application exception.
	 */
	public ApplicationException() {
		super();
	}

	/**
	 * Instantiates a new application exception.
	 *
	 * @param message the message
	 */
	public ApplicationException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new application exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new application exception.
	 *
	 * @param cause the cause
	 */
	public ApplicationException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new application exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	protected ApplicationException(String message, Throwable cause, boolean enableSuppression,boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
