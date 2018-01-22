/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.validation.helper.json;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 * This is the Utility class that is used to test the validity of the JSONString, if it is a valid JSONObject or not.
 * @author anujmehra
 *
 */
@Component("jsonObjectValidator")
public class JSONObjectValidator {

	/**
	 * This method test the validity of the input jsonString provided, if it is a valid JSONObject or not.
	 * @param jsonString String
	 * @return status BOOLEAN
	 */
	@SuppressWarnings("unused")
	public Boolean isValidJSONObject(String jsonString){

		boolean status = Boolean.TRUE;
		
		try{
			final JSONObject jsonObject = new JSONObject(jsonString);
		}catch(JSONException jsonException){
			status = Boolean.FALSE;
		}

		return status;
	}//end of method 'isValidJSONObject'

}//end of class 'JSONObjectValidator'
