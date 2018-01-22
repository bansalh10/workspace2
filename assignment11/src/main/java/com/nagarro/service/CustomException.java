package com.nagarro.service;

public class CustomException extends Exception {
	/**
	 * serial id for custom exception
	 */
	private static final long serialVersionUID = 4481175792658370188L;
	private String msg;

	public CustomException(String msg) {
		this.msg = msg;
	}

	public void display() {
		System.out.println(msg);
	}

}
