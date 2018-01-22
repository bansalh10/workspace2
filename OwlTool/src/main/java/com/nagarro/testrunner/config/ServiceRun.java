/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.nagarro.testrunner.service.RestService;

/**
 * 
 * @author anujmehra
 *
 */
public final class ServiceRun {

	/**
	 * Making ServiceRun as Utility class.
	 */
	private ServiceRun(){
		
	}
	
	/**
	 * 
	 * @return restService RestService
	 */
	public static RestService getExecutableService(){

		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringBeanConfig.class);
		return (RestService)applicationContext.getBean("restService");
	}
	
}
