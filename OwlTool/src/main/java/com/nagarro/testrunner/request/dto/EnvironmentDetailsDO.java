/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.request.dto;

/**
 * This class holds information about environment.
 *
 * @author anujmehra
 */
public class EnvironmentDetailsDO {

	/** The environment. */
	private String environment;
	
	private String resourceURI;
	
	/** The user id. */
	//private String authorization;
	
	/** The password. */
	private String webApiKey;
	
	/** The Run */
	private boolean run;
	

	/**
	 * @return the environment
	 */
	public String getEnvironment() {
		return environment;
	}

	/**
	 * @param environment the environment to set
	 */
	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	/**
	 * @return the resourceURI
	 */
	public String getResourceURI() {
		return resourceURI;
	}

	/**
	 * @param resourceURI the resourceURI to set
	 */
	public void setResourceURI(String resourceURI) {
		this.resourceURI = resourceURI;
	}

	/*public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}*/

	public String getWebApiKey() {
		return webApiKey;
	}

	public void setWebApiKey(String webApiKey) {
		this.webApiKey = webApiKey;
	}

	/**
	 * @return the run
	 */
	public boolean isRun() {
		return run;
	}

	/**
	 * @param run the run to set
	 */
	public void setRun(boolean run) {
		this.run = run;
	}


}
