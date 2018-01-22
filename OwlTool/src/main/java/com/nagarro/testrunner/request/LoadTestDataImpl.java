/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.request;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.testrunner.exception.RequestException;
import com.nagarro.testrunner.request.dto.EnvironmentDetailsDO;
import com.nagarro.testrunner.request.dto.ResourcesDO;
import com.nagarro.testrunner.request.dto.TestDataDO;
import com.nagarro.testrunner.request.reader.EmailReader;
import com.nagarro.testrunner.request.reader.EnvironmentReader;
import com.nagarro.testrunner.request.reader.RequestReader;

/**
 * 
 * @author anujmehra
 *
 */
@Component("loadTestData")
public class LoadTestDataImpl implements LoadTestData{


	/**
	 * Reference for EnvironmentReader
	 */
	@Autowired
	private EnvironmentReader environmentReader;

	/**
	 * Reference for EmailReader
	 */
	@Autowired
	private EmailReader emailReader;

	/**
	 * Reference for RequestReader
	 */
	@Autowired
	private RequestReader requestReader;

	/**
	 * 
	 * @return testDataDO TestDataDO
	 */
	@Override
	public TestDataDO loadData(){

		TestDataDO testDataDO = new TestDataDO();

		try {
			testDataDO.setEmails(emailReader.read());
             List<EnvironmentDetailsDO> environmentList = environmentReader.read();
             if(!environmentList.isEmpty()){
            	 testDataDO.setEnvironmentDetails(environmentList.get(0));
             }
			

			ResourcesDO resourcesDO = new ResourcesDO();
			resourcesDO.setRequests(requestReader.read());

			testDataDO.setResource(resourcesDO);
		} catch (RequestException requestException) {
			//TODO:: handle exception here...
			requestException.printStackTrace();
		}


		return testDataDO;
	}

}//end of class LoadTestDataImpl
