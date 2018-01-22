/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.validation.validator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.nagarro.testrunner.validation.exception.InvalidJSONException;
import com.nagarro.testrunner.validation.helper.json.JSONArrayValidator;
import com.nagarro.testrunner.validation.helper.json.JSONEnum;
import com.nagarro.testrunner.validation.helper.json.JSONObjectValidator;

/**
 * This is the helper class used by the validation Layer.
 * @author anujmehra
 *
 */
@Component("jsonValidatorHelper")
public class JSONValidatorHelper {

	/**
	 * Object reference for JSONArrayValidator.
	 */
	@Autowired
	private JSONArrayValidator  jsonArrayValidator;

	/**
	 * Object reference for JSONObjectValidator.
	 */
	@Autowired
	private JSONObjectValidator  jsonObjectValidator;


	/**
	 * This method return the JSONType based on the inputJSON string provided.
	 * @param jsonString String
	 * @return jsonType String
	 * @throws InvalidJSONException
	 */
	public String getJSONType(String jsonString) throws InvalidJSONException{

		String jsonType = null;

		if(!jsonObjectValidator.isValidJSONObject(jsonString))
		{
			if(!jsonArrayValidator.isValidJSONArray(jsonString)){
				throw new InvalidJSONException("Invalid JSON String");
			}else{
				jsonType = JSONEnum.JSONArray.toString();
			}

		}else{
			jsonType = JSONEnum.JSONObject.toString();
		}

		return jsonType;
	}

	
	/**
	 * 
	 * @param filePath String
	 * @return JsonNode
	 * @throws IOException
	 */
	public JsonNode loadResourceFromFile(String filePath)throws IOException
	{
		return JsonLoader.fromResource(filePath);
	}

	
	/**
	 * 
	 * @param json String
	 * @return JsonNode
	 * @throws IOException
	 */
	public JsonNode loadResourceFromStream(String json)throws IOException
	{
		return JsonLoader.fromString(json);
	}

	
	/**
	 * 
	 * @param fileName String
	 * @return fileData String
	 * @throws IOException
	 */
	public String readFileDataAsString(String fileName) throws IOException {

		final byte[] fileData = Files.readAllBytes(Paths.get(fileName));
		return new String(fileData);
	}
	

}
