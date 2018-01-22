package com.nagarro.testrunner.validation.helper.json;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class JSONArrayValidatorTest {

	@InjectMocks
	private JSONArrayValidator jsonArrayValidator;
	
	/**
	 * 
	 */
	@Before
	public  void setUp(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testIsValidJSONArray_Positive(){
		
		String inputJJSONArray = "[{\"first\" : \"John\",\"last\" : \"Smith\"}, {\"first\" : \"Jane\",\"last\" : \"Doe\"}]";
		
		boolean isValid = jsonArrayValidator.isValidJSONArray(inputJJSONArray);
		
		Assert.assertEquals(Boolean.TRUE, isValid);
	}
	
	
	@Test
	public void testIsValidJSONArray_Negative(){
		
		String inputJJSONArray = "{\"first\" : \"John\",\"last\" : \"Smith\"}";
		
		boolean isValid = jsonArrayValidator.isValidJSONArray(inputJJSONArray);
		
		Assert.assertEquals(Boolean.FALSE, isValid);
	}
	
	@Test
	public void testIsValidJSONArray_Negative_InvalidJSON(){
		
		String inputJJSONArray = "{\"first\" : \"John\",\"last\" : \"Smith\", }";
		
		boolean isValid = jsonArrayValidator.isValidJSONArray(inputJJSONArray);
		
		Assert.assertEquals(Boolean.FALSE, isValid);
	}
	
	
	/**
	 * 
	 */
	@After
	public void cleanUp(){

	}
}
