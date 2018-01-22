/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.validation.helper.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Component;

/**
 * This is the Utility class that is used to test the validity of the JSONString, if it is a valid JSONArray or not.
 * @author anujmehra
 *
 */
@Component("jsonArrayValidator")
public class JSONArrayValidator {


	/**
	 * This method test the validity of the input jsonString provided, if it is a valid JSONArray or not.
	 * @param jsonString String
	 * @return status BOOLEAN
	 */
	@SuppressWarnings("unused")
	public Boolean isValidJSONArray(String jsonString){

		Boolean status = Boolean.TRUE;

		try{
			final JSONArray jsonArray = new JSONArray(jsonString);
		}catch(JSONException jsonException){
			status = Boolean.FALSE;
		}

		return status;
	}//end of method 'isValidJSONArray'
	
	
}//end of class 'JSONArrayValidator'.
