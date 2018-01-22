package com.nagarro.testrunner.manager;

import java.util.HashMap;
import java.util.Map;

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

import com.nagarro.testrunner.client.HTTPClientImplFactory;
import com.nagarro.testrunner.client.HTTPGetRestClientImpl;
import com.nagarro.testrunner.client.helper.HTTPMethodType;
import com.nagarro.testrunner.request.dto.EnvironmentDetailsDO;
import com.nagarro.testrunner.request.dto.RequestDO;
import com.nagarro.testrunner.request.dto.ResponseDO;
import com.nagarro.testrunner.response.dto.XLSResponseRow;
import com.nagarro.testrunner.validation.validator.JSONValidator;

@RunWith(MockitoJUnitRunner.class)
public class JSONRestManagerImplTest {

	@InjectMocks
	private JSONRestManagerImpl jsonRestManager;

	@Mock
	private HTTPClientImplFactory<ResponseDO> httpClientImplFactory;

	@Mock
	private JSONValidator jsonValidator;

	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void testCallRestWS_Positive_GET()
	{
		final RequestDO requestDO = new  RequestDO();
		requestDO.setOperationName("TestOperation");
		requestDO.setOperationURI("Test URI");
		requestDO.setAuthorization("authorization");
		requestDO.setOutputMediaType("application/json");
		requestDO.setHttpMethodType(HTTPMethodType.GET);
		requestDO.setExpectedJsonResponse("{}");
		requestDO.setInputJSON("{}");
		requestDO.setInputMediaType("application/json");
        requestDO.setExpectedJsonData("{}");
        requestDO.setValidateValues(null);

		final EnvironmentDetailsDO environmentDetailsDO = new EnvironmentDetailsDO();
		environmentDetailsDO.setWebApiKey("webApiKey");

		final Map<String,String> headerParameters = new HashMap<String,String>();
		if(null != requestDO.getAuthorization()){
			headerParameters.put("Authorization", "bearer "+requestDO.getAuthorization());
		}

		if(null != environmentDetailsDO.getWebApiKey()){
			headerParameters.put("WEB-API-key", environmentDetailsDO.getWebApiKey());
		}

		ResponseDO responseDO = new ResponseDO();
		responseDO.setJsonReponse("{}");
		responseDO.setResponseCode(200);

		HTTPGetRestClientImpl httpPGetRestClientImpl = new HTTPGetRestClientImpl();
		Mockito.when(httpClientImplFactory.getHTTPClient(requestDO.getHttpMethodType())).thenReturn(httpPGetRestClientImpl);

		Mockito.when(jsonValidator.validate(responseDO,requestDO.getExpectedJsonResponse())).thenReturn(null);

		XLSResponseRow xlsResponseRow = jsonRestManager.callRestWS(requestDO, environmentDetailsDO);

		Assert.assertNotNull(xlsResponseRow);

	}


	@Test
	public void testCallRestWS_Positive_PUT(){

		final RequestDO requestDO = new  RequestDO();
		requestDO.setOperationName("TestOperation");
		requestDO.setOperationURI("Test URI");
		requestDO.setAuthorization("authorization");
		requestDO.setOutputMediaType("application/json");
		requestDO.setHttpMethodType(HTTPMethodType.PUT);
		requestDO.setExpectedJsonResponse("{}");
		requestDO.setInputJSON("{}");
		requestDO.setInputMediaType("application/json");
		 requestDO.setExpectedJsonData("{}");
	        requestDO.setValidateValues(null);

		final EnvironmentDetailsDO environmentDetailsDO = new EnvironmentDetailsDO();
		environmentDetailsDO.setWebApiKey("webApiKey");

		final Map<String,String> headerParameters = new HashMap<String,String>();
		if(null != requestDO.getAuthorization()){
			headerParameters.put("Authorization", "bearer "+requestDO.getAuthorization());
		}


		if(null != environmentDetailsDO.getWebApiKey()){
			headerParameters.put("WEB-API-key", environmentDetailsDO.getWebApiKey());
		}

		ResponseDO responseDO = new ResponseDO();
		responseDO.setJsonReponse("{}");
		responseDO.setResponseCode(200);

		HTTPGetRestClientImpl httpPGetRestClientImpl = new HTTPGetRestClientImpl();
		Mockito.when(httpClientImplFactory.getHTTPClient(requestDO.getHttpMethodType())).thenReturn(httpPGetRestClientImpl);

		Mockito.when(jsonValidator.validate(responseDO,requestDO.getExpectedJsonResponse())).thenReturn(null);

		XLSResponseRow xlsResponseRow = jsonRestManager.callRestWS(requestDO, environmentDetailsDO);

		Assert.assertNotNull(xlsResponseRow);

	}


	@Test
	public void testCallRestWS_Positive_POST(){

		final RequestDO requestDO = new  RequestDO();
		requestDO.setOperationName("TestOperation");
		requestDO.setOperationURI("Test URI");
		requestDO.setAuthorization("authorization");
		requestDO.setOutputMediaType("application/json");
		requestDO.setHttpMethodType(HTTPMethodType.POST);
		requestDO.setExpectedJsonResponse("{}");
		requestDO.setInputJSON("{}");
		requestDO.setInputMediaType("application/json");
		 requestDO.setExpectedJsonData("{}");
	        requestDO.setValidateValues(null);

		final EnvironmentDetailsDO environmentDetailsDO = new EnvironmentDetailsDO();
		environmentDetailsDO.setWebApiKey("webApiKey");

		final Map<String,String> headerParameters = new HashMap<String,String>();
		if(null != requestDO.getAuthorization()){
			headerParameters.put("Authorization", "bearer "+requestDO.getAuthorization());
		}


		if(null != environmentDetailsDO.getWebApiKey()){
			headerParameters.put("WEB-API-key", environmentDetailsDO.getWebApiKey());
		}

		ResponseDO responseDO = new ResponseDO();
		responseDO.setJsonReponse("{}");
		responseDO.setResponseCode(200);

		HTTPGetRestClientImpl httpPGetRestClientImpl = new HTTPGetRestClientImpl();
		Mockito.when(httpClientImplFactory.getHTTPClient(requestDO.getHttpMethodType())).thenReturn(httpPGetRestClientImpl);

		Mockito.when(jsonValidator.validate(responseDO,requestDO.getExpectedJsonResponse())).thenReturn(null);

		XLSResponseRow xlsResponseRow = jsonRestManager.callRestWS(requestDO, environmentDetailsDO);

		Assert.assertNotNull(xlsResponseRow);

	}


	@Test
	public void testCallRestWS_Positive_DELETE(){

		final RequestDO requestDO = new  RequestDO();
		requestDO.setOperationName("TestOperation");
		requestDO.setOperationURI("Test URI");
		requestDO.setAuthorization("authorization");
		requestDO.setOutputMediaType("application/json");
		requestDO.setHttpMethodType(HTTPMethodType.DELETE);
		requestDO.setExpectedJsonResponse("{}");
		requestDO.setInputJSON("{}");
		requestDO.setInputMediaType("application/json");
		 requestDO.setExpectedJsonData("{}");
	        requestDO.setValidateValues(null);

		final EnvironmentDetailsDO environmentDetailsDO = new EnvironmentDetailsDO();
		environmentDetailsDO.setWebApiKey("webApiKey");

		final Map<String,String> headerParameters = new HashMap<String,String>();
		if(null != requestDO.getAuthorization()){
			headerParameters.put("Authorization", "bearer "+requestDO.getAuthorization());
		}


		if(null != environmentDetailsDO.getWebApiKey()){
			headerParameters.put("WEB-API-key", environmentDetailsDO.getWebApiKey());
		}

		ResponseDO responseDO = new ResponseDO();
		responseDO.setJsonReponse("{}");
		responseDO.setResponseCode(200);

		HTTPGetRestClientImpl httpPGetRestClientImpl = new HTTPGetRestClientImpl();
		Mockito.when(httpClientImplFactory.getHTTPClient(requestDO.getHttpMethodType())).thenReturn(httpPGetRestClientImpl);

		XLSResponseRow xlsResponseRow = jsonRestManager.callRestWS(requestDO, environmentDetailsDO);

		Assert.assertNotNull(xlsResponseRow);

	}



	@Test
	public void testCallRestWS_Negative_DELETE(){

		final RequestDO requestDO = new  RequestDO();
		requestDO.setOperationName("TestOperation");
		requestDO.setOperationURI("Test URI");
		requestDO.setAuthorization("authorization");
		requestDO.setOutputMediaType("application/json");
		requestDO.setHttpMethodType(HTTPMethodType.DELETE);
		requestDO.setExpectedJsonResponse("{}");
		requestDO.setInputJSON("{}");
		requestDO.setInputMediaType("application/json");
		 requestDO.setExpectedJsonData("{}");
	        requestDO.setValidateValues(null);

		final EnvironmentDetailsDO environmentDetailsDO = new EnvironmentDetailsDO();
		environmentDetailsDO.setWebApiKey("webApiKey");

		final Map<String,String> headerParameters = new HashMap<String,String>();
		if(null != requestDO.getAuthorization()){
			headerParameters.put("Authorization", "bearer "+requestDO.getAuthorization());
		}


		if(null != environmentDetailsDO.getWebApiKey()){
			headerParameters.put("WEB-API-key", environmentDetailsDO.getWebApiKey());
		}

		ResponseDO responseDO = new ResponseDO();
		responseDO.setJsonReponse("{}");
		responseDO.setResponseCode(200);

		XLSResponseRow xlsResponseRow = jsonRestManager.callRestWS(requestDO, environmentDetailsDO);

		Assert.assertNotNull(xlsResponseRow);
		

	}

	@After
	public void cleanUp(){

	}

}//end of Test class JSONRestManagerImplTest
