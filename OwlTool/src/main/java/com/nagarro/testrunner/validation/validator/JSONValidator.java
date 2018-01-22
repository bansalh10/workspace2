/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.validation.validator;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.nagarro.testrunner.client.helper.HTTPMethodType;
import com.nagarro.testrunner.request.dto.ResponseDO;
import com.nagarro.testrunner.response.dto.ErrorDO;
import com.nagarro.testrunner.validation.exception.InvalidJSONException;

/**
 * This is the validation class for the application.
 * 
 * @author anujmehra
 *
 */
@Component("jsonValidator")
public class JSONValidator {

	/**
	 * Object reference for JSONValidatorHelper.
	 */
	@Autowired
	private JSONValidatorHelper jsonValidatorHelper;

	/**
	 * Object reference for JsonSchemaFactory.
	 */
	private JsonSchemaFactory factory = null;

	/**
	 * Location on file system, in which json schema files have been placed.
	 */
	@Value("${json.testing.response.schema.location}")
	private String jsonSchemaLocation;

	/**
	 * 
	 */
	@Value("${http.put.no.response.body.success.code}")
	private Integer HTTP_PUT_NO_RESPONSE_BODY_SUCCESS_CODE;

	/**
	 * 
	 */
	@Value("${http.post.no.response.body.success.code}")
	private Integer HTTP_POST_NO_RESPONSE_BODY_SUCCESS_CODE;

	/**
	 * 
	 */
	@Value("${http.delete.no.response.body.success.code}")
	private Integer HTTP_DELETE_NO_RESPONSE_BODY_SUCCESS_CODE;

	/**
	 * Logger Object.
	 */
	private static final Logger LOG = Logger.getLogger(JSONValidator.class);

	/**
	 * 
	 */
	@PostConstruct
	private void init() {
		factory = JsonSchemaFactory.byDefault();
	}

	/**
	 * This method does following validations: 1. Is expected JSON Schema
	 * provided in .json file is of correct format, and also whether it is
	 * JSONObject or JSONArray. 2. Is actual JSON stream received is of correct
	 * format, and also whether it is JSONObject or JSONArray. 3. Format of
	 * Actual JSON = Expected JSON schema 4. Comparison of Actual JSON received
	 * against the expected JSON schema provided.
	 * 
	 * @param actualResponseJSONStream
	 *            String
	 * @param expectedResponseJSONStream
	 *            String
	 * @return errorDO ErrorDO
	 * @throws applicationException
	 *             ApplicationException
	 */
	public ErrorDO validate(ResponseDO responseDO, String expectedResponseJSONStream) {

		ErrorDO errorDO = null;

		String actualResponseJSONType = null;

		String expectedResponseJSONType = null;

		String expectedJSONSchema = null;
		final String actualResponseJSONStream = responseDO.getJsonReponse();

		if (null != actualResponseJSONStream) {
			// Validate if Actual Response is a valid JSON or not
			try {
				actualResponseJSONType = jsonValidatorHelper.getJSONType(actualResponseJSONStream);
			} catch (InvalidJSONException invalidJSONException) {
				errorDO = this.populateError("Invalid JSON Response Received: " + actualResponseJSONStream);
			}

			// Validate if Error response is received from backend.
			if (null == errorDO) {
				if (responseDO.isError()) {
					errorDO = this.populateError("***** Error response received *****");
				}

				if (actualResponseJSONStream.replace(" ", "").startsWith("{\"errors\":")) {
					errorDO = this.populateError("***** Error response received *****");
				}
			}

			if (null != expectedResponseJSONStream && expectedResponseJSONStream.trim().length() > 0) {
				// Validate if Expected JSON Schema is a valid JSON or not
				if (null == errorDO) {
					try {
						String projectLocation = new File("").getAbsolutePath().replace("\\", "/");

						// First fetch the schema from the file
						expectedJSONSchema = jsonValidatorHelper.readFileDataAsString(
								projectLocation + jsonSchemaLocation + expectedResponseJSONStream);

						// Now validate that schema
						expectedResponseJSONType = jsonValidatorHelper.getJSONType(expectedJSONSchema);

					} catch (IOException e) {
						errorDO = this.populateError(
								"IOException Occured : Schema File not found : " + expectedResponseJSONStream);
					} catch (InvalidJSONException invalidJSONException) {
						System.out.println(invalidJSONException.getMessage());
						errorDO = this.populateError("Invalid JSON Expected Response: " + expectedResponseJSONStream);
					}
				}

				// Validate if Actual Response is as per Expected schema or not
				if (null == errorDO) {
					if (!actualResponseJSONType.equals(expectedResponseJSONType)) {

						errorDO = this.populateError("Response JSON is not of expected format");
					} else {
						// **********Compare the Actual and Expected JSONs
						// **********

						try {
							JsonNode jsonSchema = jsonValidatorHelper.loadResourceFromStream(expectedJSONSchema);

							JsonNode actualResponse = jsonValidatorHelper
									.loadResourceFromStream(actualResponseJSONStream);

							JsonSchema schema = factory.getJsonSchema(jsonSchema);

							ProcessingReport processingReport = schema.validate(actualResponse);

							// compareJsons();
							if (processingReport.toString().contains(": failure")) {
								errorDO = this.populateError(processingReport.toString().substring(
										processingReport.toString().indexOf("error: "),
										processingReport.toString().indexOf("---  END MESSAGES  ---")));
							}
						} catch (IOException e) {
							errorDO = this.populateError(
									"IOException Occured : Schema File not found : " + expectedResponseJSONStream);
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				}
			}
		} else {
			// If there is no response body in response.
			// In this case we need to validate the HTTP response code received.
			if (responseDO.getHttpMethodType().equals(HTTPMethodType.PUT)) {

				if (null != expectedResponseJSONStream && expectedResponseJSONStream.trim().length() > 0) {
					errorDO = this.populateError("Response JSON is not of expected format");
				} else {
					if (!(HTTP_PUT_NO_RESPONSE_BODY_SUCCESS_CODE == responseDO.getResponseCode())) {
						errorDO = this.populateError("*** HTTP Response Code is Incorrect ***");
					}
				}

			} else if (responseDO.getHttpMethodType().equals(HTTPMethodType.POST)) {

				if (null != expectedResponseJSONStream && expectedResponseJSONStream.trim().length() > 0) {
					errorDO = this.populateError("Response JSON is not of expected format");
				} else {
					if (!(HTTP_POST_NO_RESPONSE_BODY_SUCCESS_CODE == responseDO.getResponseCode())) {
						errorDO = this.populateError("*** HTTP Response Code is Incorrect ***");
					}
				}

			} else if (responseDO.getHttpMethodType().equals(HTTPMethodType.DELETE)) {
				if (null != expectedResponseJSONStream && expectedResponseJSONStream.trim().length() > 0) {
					errorDO = this.populateError("Response JSON is not of expected format");
				} else {
					if (!(HTTP_DELETE_NO_RESPONSE_BODY_SUCCESS_CODE == responseDO.getResponseCode())) {
						errorDO = this.populateError("*** HTTP Response Code is Incorrect ***");
					}
				}
			}
		}

		logValidationResult(errorDO);
		return errorDO;
	}// end of method validate

	public ErrorDO validateActualResponseWithExpected(String expectedJson, String actualJson, String[] attributes) {
		ErrorDO errorDo = null;
		try {
			if (expectedJson == null) {
				errorDo = new ErrorDO();
				errorDo.setWarning("No Expected Response found");
			} else if (compareJsons(expectedJson, actualJson, attributes)) {
				System.out.println("Validated expected and actual response...");
			} else {
				errorDo = this.populateError("Actual Response  is different from the expected");
			}
		} catch (JsonProcessingException e) {
			errorDo = this.populateError("Invalid Expected Json Response");
		} catch (IOException e) {

			e.printStackTrace();
		}
		return errorDo;
	}

	// public boolean compareJsons(String expectedJson, String actualJson,
	// String[] attributes)
	// throws JsonProcessingException, IOException {
	// boolean matched = true;
	// // read a json string as a tree for parsing json data
	// JsonNode expectedJnode = new ObjectMapper().readTree(expectedJson);
	// JsonNode actualJnode = new ObjectMapper().readTree(actualJson);
	//
	// if (attributes!=null) {
	// for (String attribute : attributes) {
	// // give a list of object nodes that have key equal to attribute
	// List<JsonNode> listOfNodesToBeComparedExpected =
	// expectedJnode.findValues(attribute);
	// List<JsonNode> listOfNodesToBeComparedActual =
	// actualJnode.findValues(attribute);
	// if (listOfNodesToBeComparedExpected.size() ==
	// listOfNodesToBeComparedActual.size()) {
	// for (int i = 0; i < listOfNodesToBeComparedExpected.size(); i++) {
	// // compare two json node and their subtrees
	// if
	// (listOfNodesToBeComparedActual.get(i).equals(listOfNodesToBeComparedExpected.get(i)))
	// {
	// continue;
	// } else {
	// matched = false;
	// break;
	// }
	// }
	// } else {
	// matched = false;
	// }
	// }
	// } else {
	// matched= expectedJnode.equals(actualJnode);
	// }
	// return matched;
	//
	// }

	public boolean compareJsons(String expectedJson, String actualJson, String[] attributes)
			throws JsonProcessingException, IOException {
		boolean matched = true;
		// read a json string as a tree for parsing json data
		JsonNode actualJnode = new ObjectMapper().readTree(actualJson);
		String[] expectedValues = expectedJson.split(",");

		if (attributes != null) {
			if (expectedValues.length == attributes.length) {
				int i = 0;
				for (String attribute : attributes) {
					// give an object node that have key equal to attribute
					JsonNode targetNode = actualJnode.findValue(attribute);
					if (!expectedValues[i++].trim().equalsIgnoreCase(getValueFromJsonNode(targetNode))) {
						System.out.println("IN THE LOOP");
						matched = false;
						break;
					}
				}
			} else {
				matched = false;
			}
		}
		return matched;

	}

	private String getValueFromJsonNode(JsonNode jnode) {
		String value;
		if (JsonNodeType.STRING.equals(jnode.getNodeType())) {
			value = jnode.asText().trim();
		} else if (JsonNodeType.NUMBER.equals(jnode.getNodeType())) {
			value = jnode.numberValue().toString().trim();
		} else if (JsonNodeType.BOOLEAN.equals(jnode.getNodeType())) {
			value = String.valueOf(jnode.asBoolean()).trim();
		} else {
			value = null;
		}
		return value;

	}

	private void traverseArrayNode(JsonNode jnode) {

		for (JsonNode node : jnode) {
			traverseNodes(node);
		}
	}

	private void traverseNodes(JsonNode jnode) {
		if (JsonNodeType.ARRAY.equals(jnode.getNodeType())) {
			traverseArrayNode(jnode);
		}
		if (JsonNodeType.STRING.equals(jnode.getNodeType())) {
			System.out.println(jnode.asText());
		}
		if (JsonNodeType.NUMBER.equals(jnode.getNodeType())) {
			System.out.println(jnode.numberValue());
		}
		if (JsonNodeType.OBJECT.equals(jnode.getNodeType())) {
			traverseObjectNode(jnode);
		}
		if (JsonNodeType.BOOLEAN.equals(jnode.getNodeType())) {
			System.out.println("boolean");

			System.out.println(jnode.asBoolean());
		}
	}

	private void traverseObjectNode(JsonNode jnode) {

		Iterator<JsonNode> i = jnode.iterator();
		while (i.hasNext()) {
			jnode = i.next();
			traverseNodes(jnode);

		}

	}

	/**
	 * 
	 * @param errorDO
	 *            ErrorDO
	 */
	private void logValidationResult(ErrorDO errorDO) {
		if (null != errorDO) {
			LOG.error("Validation FAILED with message : " + errorDO);
		} else {
			LOG.error("Validation PASSED");
		}
	}

	/**
	 * 
	 * @param errorMessage
	 *            String
	 * @return errorDO ErrorDO
	 */
	private ErrorDO populateError(String errorMessage) {

		final List<String> errorMessagesList = Arrays.asList(errorMessage.split("error: "));

		final StringBuffer errorMsgFinal = new StringBuffer();

		final ErrorDO errorDO = new ErrorDO();

		int counter = 1;
		for (String errorMsg : errorMessagesList) {
			if (errorMsg.trim().length() > 0 && !errorMsg.trim().contains("***** Error response received *****")) {
				final String lines[] = errorMsg.split("\\r?\\n");

				errorMsgFinal.append("\n");
				errorMsgFinal.append("Error:-" + counter + ":");
				counter++;

				errorMsgFinal.append("\n");
				errorMsgFinal.append("Error message: " + lines[0]);

				for (String data : lines) {
					if (data.trim().startsWith("found:") || data.trim().startsWith("expected:")
							|| data.trim().startsWith("value:") || data.trim().startsWith("enum:")
							|| data.trim().startsWith("instance:") || data.trim().startsWith("required:")
							|| data.trim().startsWith("missing:")) {
						errorMsgFinal.append("\n");
						errorMsgFinal.append(data.trim());
					} else if (data.trim().startsWith("reports:")) {
						errorMsgFinal.append("\n");
						errorMsgFinal.append(data.trim().substring(0, data.indexOf(":[")));

						String s1 = data.trim().substring(data.trim().indexOf("\"found\":"), data.trim().length());

						errorMsgFinal.append("\n");

						errorMsgFinal.append(s1.substring(0, s1.indexOf(",")));

						errorMsgFinal.append("\n");
						errorMsgFinal.append(s1.substring(s1.indexOf("\"expected\""), s1.indexOf("}],")));
					}
				}
				errorMsgFinal.append("\n");

			}

		}

		if (errorMsgFinal.toString().trim().length() > 0) {
			errorDO.setErrorMessage(errorMsgFinal.toString());
		} else {
			errorDO.setErrorMessage(errorMessage);
		}

		return errorDO;
	}// end of method populateError

}
