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
public class JSONObjectValidatorTest{

	@InjectMocks
	private JSONObjectValidator jsonObjectValidator;

	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testIsValidJSONObject_Positive(){

		String inputJJSONArray = "{\"first\" : \"John\",\"last\" : \"Smith\"}}";

		boolean isValid = jsonObjectValidator.isValidJSONObject(inputJJSONArray);

		Assert.assertEquals(Boolean.TRUE, isValid);
	}

	@Test
	public void testIsValidJSONObject_Negative(){
		String inputJJSONArray = "[{\"first\" : \"John\",\"last\" : \"Smith\"}, {\"first\" : \"Jane\",\"last\" : \"Doe\"}]";

		boolean isValid = jsonObjectValidator.isValidJSONObject(inputJJSONArray);

		Assert.assertEquals(Boolean.FALSE, isValid);
	}

	@Test
	public void testIsValidJSONObject_Negative_InvalidJSON(){

		String inputJJSONArray = "{\"first\" : \"John\",\"last\" : \"Smith\", \"middle\":}}";

		boolean isValid = jsonObjectValidator.isValidJSONObject(inputJJSONArray);

		Assert.assertEquals(Boolean.FALSE, isValid);
	}

	@After
	public void cleanUp(){

	}
}
