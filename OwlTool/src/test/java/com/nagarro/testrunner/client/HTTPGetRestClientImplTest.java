package com.nagarro.testrunner.client;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.nagarro.testrunner.exception.ApplicationException;

@RunWith(MockitoJUnitRunner.class)
public class HTTPGetRestClientImplTest {

	@InjectMocks
	private HTTPGetRestClientImpl httpGetRestClient;

	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(httpGetRestClient, "HTTP_PROXY_HOST", "http://someurl");
		ReflectionTestUtils.setField(httpGetRestClient, "HTTP_PROXY_PORT", 8080);
		ReflectionTestUtils.setField(httpGetRestClient, "HTTP_PROXY_PROTOCOL", "HTTP");
	}


	@Test
	public void testMakeWSCall_Negative(){
		try {
			Map<String,String> headerParameters = new HashMap<String,String>();
			headerParameters.put("key1", "value1");
			headerParameters.put("key2", "value2");
			
			httpGetRestClient.makeWSCall("restURI", "resourceURIPath", MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_JSON_TYPE, null, headerParameters);
			
			
		} catch (Exception e) {
			Assert.assertEquals(ApplicationException.class, e.getClass());;
		}
		
	}
}
