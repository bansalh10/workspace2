/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.request.dto;

import com.nagarro.testrunner.client.helper.HTTPMethodType;


/**
 * This class contains all the request and parameter data for an operation.
 * 
 * @author anujmehra
 *
 */
public class RequestDO {
	
	/** The request id. */
	private double requestId;
	
	/** The request description. */
	private String requestDescription;
	
	private String operationName;
	
	/** The operationUri */
	private String operationURI;
	
	/** The httpMethodType */
	private HTTPMethodType httpMethodType;
	
	/** The inputJSON */
	private String inputJSON;
	
	/** The InputDataType (Json/XML) */
	private String inputMediaType;
	

	/** The InputDataType (Json/XML) */
	private String outputMediaType;
	
	/** The expectedJsonResponse*/
	private String expectedJsonResponse;

	private String authorization;
	
	private boolean run;
	/**
	 * Values to be validated in Actualresponse and expectedJsonData
	 */
	private String validateValues;
	
	/**
	 * The expected Json data that should be in actual response
	 */
	private String expectedJsonData;
	
	/**
	 * @return the requestId
	 */
	public double getRequestId() {
		return requestId;
	}

	/**
	 * @param requestId the requestId to set
	 */
	public void setRequestId(double requestId) {
		this.requestId = requestId;
	}

	/**
	 * @return the requestDescription
	 */
	public String getRequestDescription() {
		return requestDescription;
	}

	/**
	 * @param requestDescription the requestDescription to set
	 */
	public void setRequestDescription(String requestDescription) {
		this.requestDescription = requestDescription;
	}
	

	/**
	 * @return the operationName
	 */
	public String getOperationName() {
		return operationName;
	}

	/**
	 * @param operationName the operationName to set
	 */
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	/**
	 * @return the operationURI
	 */
	public String getOperationURI() {
		return operationURI;
	}

	/**
	 * @param operationURI the operationURI to set
	 */
	public void setOperationURI(String operationURI) {
		this.operationURI = operationURI;
	}
	
	
	public String getInputMediaType() {
		return inputMediaType;
	}

	public void setInputMediaType(String inputMediaType) {
		this.inputMediaType = inputMediaType;
	}


	/**
	 * @return the expectedJsonResponse
	 */
	public String getExpectedJsonResponse() {
		return expectedJsonResponse;
	}

	/**
	 * @param expectedJsonResponse the expectedJsonResponse to set
	 */
	public void setExpectedJsonResponse(String expectedJsonResponse) {
		this.expectedJsonResponse = expectedJsonResponse;
	}

	public String getOutputMediaType() {
		return outputMediaType;
	}

	public void setOutputMediaType(String outputMediaType) {
		this.outputMediaType = outputMediaType;
	}

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}


	public HTTPMethodType getHttpMethodType() {
		return httpMethodType;
	}

	public void setHttpMethodType(HTTPMethodType httpMethodType) {
		this.httpMethodType = httpMethodType;
	}

	public String getInputJSON() {
		return inputJSON;
	}

	public void setInputJSON(String inputJSON) {
		this.inputJSON = inputJSON;
	}


	public String getExpectedJsonData() {
		return expectedJsonData;
	}

	public void setExpectedJsonData(String expectedJsonData) {
		this.expectedJsonData = expectedJsonData;
	}

	/**
	 * @return the validateValues
	 */
	public String getValidateValues() {
		return validateValues;
	}

	/**
	 * @param validateValues the validateValues to set
	 */
	public void setValidateValues(String validateValues) {
		this.validateValues = validateValues;
	}





	
}
