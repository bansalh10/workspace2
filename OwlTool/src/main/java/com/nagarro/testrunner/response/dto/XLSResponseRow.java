/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.response.dto;

import com.nagarro.testrunner.client.helper.HTTPMethodType;
import com.nagarro.testrunner.response.helper.ResponseStatus;

/**
 * 
 * @author anujmehra
 *
 */
public class XLSResponseRow {

	/**
	 * 
	 */
	private long requestid;
	
	/**
	 * 
	 */
	private String requestDescription;
	
	/**
	 * 
	 */
	private String operationName;
	
	/**
	 * 
	 */
	private String expectedresponse;
	
	/**
	 * 
	 */
	private String actualResponse;
	
	/**
	 * 
	 */
	private String authorization;
	
	/**
	 * 
	 */
	private String httpResponseCode;
	
	/**
	 * 
	 */
	private HTTPMethodType httpMethodType;
	
	/**
	 * 
	 */
	private ErrorDO error;
	
	private String validateValues;
	
	private String expectedJsonResponseData;
	
	private String warning;

	/**
	 * @return the requestid
	 */
	public long getRequestid() {
		return requestid;
	}

	/**
	 * @param requestid the requestid to set
	 */
	public void setRequestid(long requestid) {
		this.requestid = requestid;
	}

	/**
	 * @return the resourceName
	 */
	public String getOperationName() {
		return operationName;
	}

	/**
	 * @param resourceName the resourceName to set
	 */
	public void setOperationName(String operationName) {
		this.operationName = operationName;
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
	 * @return the expectedresponse
	 */
	public String getExpectedresponse() {
		return expectedresponse;
	}

	/**
	 * @param expectedresponse the expectedresponse to set
	 */
	public void setExpectedresponse(String expectedresponse) {
		this.expectedresponse = expectedresponse;
	}

	/**
	 * @return the actualResponse
	 */
	public String getActualResponse() {
		return actualResponse;
	}

	/**
	 * @param actualResponse the actualResponse to set
	 */
	public void setActualResponse(String actualResponse) {
		this.actualResponse = actualResponse;
	}

	/**
	 * @return the error
	 */
	public ErrorDO getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(ErrorDO error) {
		this.error = error;
	}

	/**
	 * @return the status
	 */
	public ResponseStatus getStatus() {
		if(null != this.error && null!=this.error.getErrorMessage()){
			return ResponseStatus.Fail;
		}else{
			return ResponseStatus.Pass;
		}
	}

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	public String getHttpResponseCode() {
		return httpResponseCode;
	}

	public void setHttpResponseCode(String httpResponseCode) {
		this.httpResponseCode = httpResponseCode;
	}

	public HTTPMethodType getHttpMethodType() {
		return httpMethodType;
	}

	public void setHttpMethodType(HTTPMethodType httpMethodType) {
		this.httpMethodType = httpMethodType;
	}

	public String getValidateValues() {
		return validateValues;
	}

	public void setValidateValues(String validateValues) {
		this.validateValues = validateValues;
	}

	public String getExpectedJsonResponseData() {
		return expectedJsonResponseData;
	}

	public void setExpectedJsonResponseData(String expectedJsonResponseData) {
		this.expectedJsonResponseData = expectedJsonResponseData;
	}

	public String getWarning() {
		return warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}

}
