package com.nagarro.testrunner.request;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.nagarro.testrunner.exception.RequestException;
import com.nagarro.testrunner.request.dto.EnvironmentDetailsDO;
import com.nagarro.testrunner.request.reader.EmailReader;
import com.nagarro.testrunner.request.reader.EnvironmentReader;
import com.nagarro.testrunner.request.reader.RequestReader;

@RunWith(MockitoJUnitRunner.class)
public class LoadTestDataImplTest {

	@InjectMocks
	private LoadTestDataImpl loadTestData;
	
	@Mock
	private EnvironmentReader environmentReader;

	@Mock
	private EmailReader emailReader;

	
	@Mock
	private RequestReader requestReader;

	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testLoadData(){
		
		List<EnvironmentDetailsDO> environmentDetailsDOList = new LinkedList<EnvironmentDetailsDO>();
		EnvironmentDetailsDO environmentDetailsDO = new EnvironmentDetailsDO();
		environmentDetailsDOList.add(environmentDetailsDO);
		
		try {
			Mockito.when(environmentReader.read()).thenReturn(environmentDetailsDOList);
		} catch (RequestException e) {
			Assert.fail();
		}
		loadTestData.loadData();
	}
	
}
