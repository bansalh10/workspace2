package com.nagarro.testrunner.validation.validator;

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

import com.nagarro.testrunner.request.dto.ResponseDO;
import com.nagarro.testrunner.response.dto.ErrorDO;
import com.nagarro.testrunner.validation.exception.InvalidJSONException;
import com.nagarro.testrunner.validation.helper.json.JSONEnum;

@RunWith(MockitoJUnitRunner.class)
public class JSONValidatorTest {

	@InjectMocks
	private JSONValidator jsonValidator;


	@Mock
	private JSONValidatorHelper jsonValidatorHelper;

	private String expectedJSONResponseSchema = "{\"type\":\"object\", \"properties\": {\"countryIso\": {\"id\":\"countryIso\",\"type\":\"string\",\"maxLength\":2,\"pattern\":\"[A-Z][A-Z]\"},\"name\": {\"id\":\"name\",\"type\":\"string\"}},\"required\":[\"name\",\"countryIso\"]}";

	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void testValidate_Negative_ResponseCode(){

		ResponseDO responseDO = new ResponseDO();
		responseDO.setResponseCode(404);
		responseDO.setJsonReponse("{\"errors\":\"errorCode\":\"Invalid_Request\"}");

		try {
			Mockito.when(jsonValidatorHelper.getJSONType("")).thenReturn(JSONEnum.JSONObject.toString());
			ErrorDO errorDO = jsonValidator.validate(responseDO, expectedJSONResponseSchema);

			Assert.assertNotNull(errorDO);
			Assert.assertEquals( errorDO.getErrorMessage(), "***** Error response received *****");
		} catch (InvalidJSONException e) {
			Assert.fail();
		}


	}


	@Test
	public void testValidate_Negative_ErrorResponse(){

		ResponseDO responseDO = new ResponseDO();
		responseDO.setResponseCode(200);
		responseDO.setJsonReponse("{\"errors\":\"errorCode\":\"Invalid_Request\"}");

		try {
			Mockito.when(jsonValidatorHelper.getJSONType("")).thenReturn(JSONEnum.JSONObject.toString());
			ErrorDO errorDO = jsonValidator.validate(responseDO, expectedJSONResponseSchema);

			Assert.assertNotNull(errorDO);
			Assert.assertEquals( errorDO.getErrorMessage(), "***** Error response received *****");
		} catch (InvalidJSONException e) {
			Assert.fail();
		}

	}

	@Test
	public void testValidate_Negative_InvalidJSONResponse(){

		ResponseDO responseDO = new ResponseDO();
		responseDO.setResponseCode(200);
		responseDO.setJsonReponse("{\"errors\":\"errorCode\":\"Invalid_Request\" , }");

		String actualResponseJSONStream = "{}";
		try {
			Mockito.when(jsonValidatorHelper.getJSONType(actualResponseJSONStream)).thenThrow(new InvalidJSONException());
			ErrorDO errorDO = jsonValidator.validate(responseDO, expectedJSONResponseSchema);
			Assert.assertEquals(errorDO.getErrorMessage(),"***** Error response received *****");
			
		} catch (InvalidJSONException e) {
			Assert.assertEquals(InvalidJSONException.class, e.getClass());
		}
		

		
	}

	@After
	public void cleanUp(){

	}

}//end of Test class JSONValidatorTest