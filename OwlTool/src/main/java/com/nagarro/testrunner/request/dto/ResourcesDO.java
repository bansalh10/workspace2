/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.request.dto;

import java.util.List;

/**
 * This class contains all the data for an operation.
 * 
 * @author anujmehra
 */
public class ResourcesDO {

	/** The requests. */
	private List<RequestDO> requests;


	/**
	 * @return the requests
	 */
	public List<RequestDO> getRequests() {
		return requests;
	}

	/**
	 * @param requests the requests to set
	 */
	public void setRequests(List<RequestDO> requests) {
		this.requests = requests;
	}
	
	
	
}
