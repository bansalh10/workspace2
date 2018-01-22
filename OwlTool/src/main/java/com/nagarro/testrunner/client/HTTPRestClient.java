/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.client;

import java.util.Map;

import javax.ws.rs.core.MediaType;

import com.nagarro.testrunner.exception.ApplicationException;

/**
 * 
 * @author anujmehra
 *
 * @param <T>
 */
public interface HTTPRestClient<T> {

	/**
	 * 
	 * @param restURI
	 * @param resourceURIPath
	 * @param consumesMediaType
	 * @param returnsMediaType
	 * @param inputJSON
	 * @param headerParameters
	 * @return <T>
	 * @throws ApplicationException
	 */
	public T makeWSCall(String restURI, String resourceURIPath ,MediaType consumesMediaType, MediaType returnsMediaType, String inputJSON, Map<String,String> headerParameters) throws ApplicationException;

	
}
