/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.testrunner.client.helper.HTTPMethodType;

/**
 * 
 * @author anujmehra
 *
 */
@Component("httpClientImplFactory")
public class HTTPClientImplFactory<T> {

	/**
	 * 
	 */
	@Autowired
	private HTTPRestClient<T> httpGetRestClient;
	
	/**
	 * 
	 */
	@Autowired
	private HTTPRestClient<T> httpPutRestClient;
	
	/**
	 * 
	 */
	@Autowired
	private HTTPRestClient<T> httpPostRestClient;
	
	/**
	 * 
	 */
	@Autowired
	private HTTPRestClient<T> httpDeleteRestClient;
	
	
	public HTTPRestClient<T> getHTTPClient(HTTPMethodType httpMethodType){
		
		HTTPRestClient<T> restClient = null;
		
		if(httpMethodType.equals(HTTPMethodType.GET)){
			restClient = httpGetRestClient;
		}else if(httpMethodType.equals(HTTPMethodType.PUT)){
			restClient = httpPutRestClient;
		}else if(httpMethodType.equals(HTTPMethodType.POST)){
			restClient = httpPostRestClient;
		}else if(httpMethodType.equals(HTTPMethodType.DELETE)){
			restClient = httpDeleteRestClient;
		}
		
		return restClient;
	}
	
}
