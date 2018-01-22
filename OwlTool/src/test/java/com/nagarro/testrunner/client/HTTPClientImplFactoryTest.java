package com.nagarro.testrunner.client;

import org.apache.poi.ss.formula.functions.T;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.nagarro.testrunner.client.helper.HTTPMethodType;

@RunWith(MockitoJUnitRunner.class)
public class HTTPClientImplFactoryTest {

	@InjectMocks
	private HTTPClientImplFactory<T> httpClientImplFactory;
	
	@Mock
	private HTTPRestClient<T> httpGetRestClient;
	
	@Mock
	private HTTPRestClient<T> httpPutRestClient;
	
	@Mock
	private HTTPRestClient<T> httpPostRestClient;
	
	@Mock
	private HTTPRestClient<T> httpDeleteRestClient;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetHTTPClient_Get(){
	
		HTTPRestClient<T> obj = httpClientImplFactory.getHTTPClient(HTTPMethodType.GET);
		
		Assert.assertEquals(obj.getClass(), httpGetRestClient.getClass());
	}
	
	
	@Test
	public void testGetHTTPClient_Put(){
	
		HTTPRestClient<T> obj = httpClientImplFactory.getHTTPClient(HTTPMethodType.PUT);
		
		Assert.assertEquals(obj.getClass(), httpPutRestClient.getClass());
	}
	
	@Test
	public void testGetHTTPClient_Post(){
	
		HTTPRestClient<T> obj = httpClientImplFactory.getHTTPClient(HTTPMethodType.POST);
		
		Assert.assertEquals(obj.getClass(), httpPostRestClient.getClass());
	}
	
	@Test
	public void testGetHTTPClient_Delete(){
	
		HTTPRestClient<T> obj = httpClientImplFactory.getHTTPClient(HTTPMethodType.DELETE);
		
		Assert.assertEquals(obj.getClass(), httpDeleteRestClient.getClass());
	}
}
