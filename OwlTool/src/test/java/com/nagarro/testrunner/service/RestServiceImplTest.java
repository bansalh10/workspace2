package com.nagarro.testrunner.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.nagarro.testrunner.manager.JSONRestManager;
import com.nagarro.testrunner.request.LoadTestData;
import com.nagarro.testrunner.request.dto.EnvironmentDetailsDO;
import com.nagarro.testrunner.request.dto.RequestDO;
import com.nagarro.testrunner.request.dto.ResourcesDO;
import com.nagarro.testrunner.request.dto.TestDataDO;
import com.nagarro.testrunner.response.dto.XLSResponseRow;
import com.nagarro.testrunner.response.persistence.report.ReportGenerator;
import com.nagarro.testrunner.utils.FileNameWrapper;

@RunWith(MockitoJUnitRunner.class)
public class RestServiceImplTest {


	//Class under test.
	@InjectMocks
	private RestServiceImpl restService;

	@Mock
	private JSONRestManager jsonRestManager;

	@Mock
	private LoadTestData loadTestData;

	@Mock
	private ReportGenerator reportGenerator;

	@Mock
	private FileNameWrapper inputTestCaseFile;

	/**
	 * 
	 */
	@Before
	public  void setUp(){
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * 
	 */
	@Test
	public void testExecute_Negative(){

		try{
			// specify mock behave when method called
			Mockito.when(loadTestData.loadData()).thenReturn(null);

			restService.execute("");	
		}catch(Exception npe){
			Assert.assertEquals(NullPointerException.class, npe.getClass());
		}

	}


	/**
	 * 
	 */
	@Test
	public void testExecute_Positive(){

		TestDataDO testDataDO = new TestDataDO();

		EnvironmentDetailsDO environmentDetails = new EnvironmentDetailsDO();
		testDataDO.setEnvironmentDetails(environmentDetails);

		ResourcesDO resource = new ResourcesDO();
		List<RequestDO> requests = new ArrayList<RequestDO>();
		RequestDO requestDO = new RequestDO();
		requestDO.setOutputMediaType(MediaType.APPLICATION_JSON);
		requests.add(requestDO);
		resource.setRequests(requests);
		testDataDO.setResource(resource);

		// specify mock behave when method called
		Mockito.when(loadTestData.loadData()).thenReturn(testDataDO);

		Mockito.when(jsonRestManager.callRestWS(requestDO, environmentDetails)).thenReturn(new XLSResponseRow());
		
		restService.execute("");
	}


	/**
	 * 
	 */
	@After
	public void cleanUp(){

	}


}//end of Test class RestServiceImplTest
