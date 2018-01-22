/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.request;

import com.nagarro.testrunner.request.dto.TestDataDO;

/**
 * 
 * @author anujmehra
 *
 */
public interface LoadTestData {

	/**
	 * 
	 * @return
	 */
	public TestDataDO loadData();
	
}
