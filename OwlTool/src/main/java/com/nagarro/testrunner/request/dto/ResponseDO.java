package com.nagarro.testrunner.request.dto;

import com.nagarro.testrunner.client.helper.HTTPMethodType;

/**
 * 
 * @author anujmehra
 *
 */
public class ResponseDO {

	private int responseCode;
	
	private String jsonReponse; 
	
	private HTTPMethodType httpMethodType;
	
	/**
	 * @return the responseCode
	 */
	public int getResponseCode() {
		return responseCode;
	}
	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	/**
	 * @return the jsonReponse
	 */
	public String getJsonReponse() {
		return jsonReponse;
	}
	/**
	 * @param jsonReponse the jsonReponse to set
	 */
	public void setJsonReponse(String jsonReponse) {
		this.jsonReponse = jsonReponse;
	}
	
	public boolean isError(){
		return responseCode >=400 && responseCode < 500;
	}
	
	public HTTPMethodType getHttpMethodType() {
		return httpMethodType;
	}
	
	public void setHttpMethodType(HTTPMethodType httpMethodType) {
		this.httpMethodType = httpMethodType;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResponseDO [responseCode=");
		builder.append(responseCode);
		builder.append(", jsonReponse=");
		builder.append(jsonReponse);
		builder.append("]");
		return builder.toString();
	}
}
