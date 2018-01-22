/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
		package com.nagarro.testrunner.request.dto;

/**
 * The Enum XLSEnum.
 *
 * @author anujmehra
 */
public enum XLSEnum {

	/** The y. */
	Y("true"), 
	
	/** The n. */
	N("false"),
	
	/** The environments. */
	ENVIRONMENT("ENVIRONMENT"),
	
	/** The operation. */
	RESOURCES("RESOURCES"),
	
	/** The email. */
	EMAIL("EMAIL");
	
	/** The xls code. */
	private String xlsCode;

	/**
	 * Instantiates a new XLS enum.
	 *
	 * @param s the s
	 */
	private XLSEnum(String s) {
		xlsCode = s;
	}

	/**
	 * Gets the xls code.
	 *
	 * @return the xlsCode
	 */
	public String getXlsCode() {
		return xlsCode;
	}

}
