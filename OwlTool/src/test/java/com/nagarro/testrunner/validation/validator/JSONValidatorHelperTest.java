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

import com.nagarro.testrunner.validation.exception.InvalidJSONException;
import com.nagarro.testrunner.validation.helper.json.JSONArrayValidator;
import com.nagarro.testrunner.validation.helper.json.JSONEnum;
import com.nagarro.testrunner.validation.helper.json.JSONObjectValidator;

@RunWith(MockitoJUnitRunner.class)
public class JSONValidatorHelperTest{

	@InjectMocks
	private JSONValidatorHelper jsonValidatorHelper;

	@Mock
	private JSONArrayValidator  jsonArrayValidator;

	@Mock
	private JSONObjectValidator  jsonObjectValidator;

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
	public void testGetJSONType_JSONArray_Positive(){

		try {
			Mockito.when(jsonObjectValidator.isValidJSONObject("")).thenReturn(Boolean.FALSE);
			Mockito.when(jsonArrayValidator.isValidJSONArray("")).thenReturn(Boolean.TRUE);

			String jsonType = jsonValidatorHelper.getJSONType("");

			Assert.assertEquals(JSONEnum.JSONArray.toString(),jsonType);
		} catch (InvalidJSONException e) {
			Assert.fail();
		}
	}

	/**
	 * 
	 */
	@Test
	public void testGetJSONType_JSONObject_Positive(){

		try {
			Mockito.when(jsonObjectValidator.isValidJSONObject("")).thenReturn(Boolean.TRUE);

			String jsonType = jsonValidatorHelper.getJSONType("");

			Assert.assertEquals(JSONEnum.JSONObject.toString(),jsonType);
		} catch (InvalidJSONException e) {
			Assert.fail();
		}
	}

	/**
	 * 
	 */
	@Test
	public void testGetJSONType_InvalidJSON(){

		String inputJSONArray = "{}";
		try {
			jsonValidatorHelper.getJSONType(inputJSONArray);
		} catch (Exception e) {

			Assert.assertEquals(InvalidJSONException.class, e.getClass());
		}
	}

	/**
	 * 
	 */
	@After
	public void cleanUp(){

	}

}
