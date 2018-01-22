/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.manager;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.testrunner.client.HTTPClientImplFactory;
import com.nagarro.testrunner.exception.ApplicationException;
import com.nagarro.testrunner.request.dto.EnvironmentDetailsDO;
import com.nagarro.testrunner.request.dto.RequestDO;
import com.nagarro.testrunner.request.dto.ResponseDO;
import com.nagarro.testrunner.response.dto.ErrorDO;
import com.nagarro.testrunner.response.dto.XLSResponseRow;
import com.nagarro.testrunner.validation.validator.JSONValidator;

/**
 * This is the manager class for this application. This class performs following
 * tasks: 1. Make a call to the RestClient which makes HTTP call to the API
 * under test 2. Make a call to the validation layer, to validate the response
 * received.
 * 
 * @author anujmehra
 *
 */
@Component("jsonRestManager")
public class JSONRestManagerImpl implements JSONRestManager {

	/**
	 * Factory class for HTTPClient.
	 */
	@Autowired
	private HTTPClientImplFactory<ResponseDO> httpClientImplFactory;

	/**
	 * Reference Object for JSONValidator.
	 */
	@Autowired
	private JSONValidator jsonValidator;

	/**
	 * Logger object.
	 */
	private static final Logger LOG = Logger.getLogger(JSONRestManagerImpl.class);

	/**
	 * 
	 * @param requestDO
	 *            RequestDO
	 * @param environmentDetailsDO
	 *            EnvironmentDetailsDO
	 * @return xlsResponseRow XLSResponseRow
	 */
	@Override
	public XLSResponseRow callRestWS(RequestDO requestDO, EnvironmentDetailsDO environmentDetailsDO) {

		ErrorDO error = null;
		XLSResponseRow xlsResponseRow = null;
		ResponseDO responseDO = null;

		try {

			LOG.info("Making Web-Service call for Resource: " + requestDO.getOperationName() + " at Uri: "
					+ requestDO.getOperationURI());

			responseDO = this.makeWSCall(requestDO, environmentDetailsDO);
			// responseReceived = responseDO.getJsonReponse();

			LOG.info("Validating received response with the schema: " + requestDO.getExpectedJsonResponse());
			error = jsonValidator.validate(responseDO, requestDO.getExpectedJsonResponse());
			if (error == null) {
				LOG.info("Validating expected response with actual response");
				String[] attributes;
				if(requestDO.getValidateValues()==null){
					attributes=null;
				}else{
					attributes=requestDO.getValidateValues().split(",");
				}
				error = jsonValidator.validateActualResponseWithExpected(requestDO.getExpectedJsonData(),
						responseDO.getJsonReponse(), attributes);
				if(error==null || error.getErrorMessage()==null){
	            	 LOG.info("Validation Passed");
	             }
	             else{
	            	 LOG.info("Validation Failed");
	             }
			}
             
			xlsResponseRow = this.populateResponseRow(requestDO, error, responseDO);

		} catch (ApplicationException applicationException) {
			error = new ErrorDO();
			error.setErrorMessage(applicationException.getMessage());

			xlsResponseRow = this.populateResponseRow(requestDO, error, responseDO);
		} catch (Exception e) {
			error = new ErrorDO();
			error.setErrorMessage(e.getMessage());

			xlsResponseRow = this.populateResponseRow(requestDO, error, responseDO);
		}

		return xlsResponseRow;
	}

	/**
	 * 
	 * @param requestDO
	 *            RequestDO
	 * @param environmentDetailsDO
	 *            EnvironmentDetailsDO
	 * @return responseDO ResponseDO
	 * @throws ApplicationException
	 */
	private ResponseDO makeWSCall(RequestDO requestDO, EnvironmentDetailsDO environmentDetailsDO)
			throws ApplicationException {

		Map<String, String> headerParameters = new HashMap<String, String>();
		ResponseDO response = null;

		if (null != requestDO.getAuthorization()) {
			headerParameters.put("Authorization", "bearer " + requestDO.getAuthorization());
		}

		if (null != environmentDetailsDO.getWebApiKey()) {
			headerParameters.put("WEB-API-key", environmentDetailsDO.getWebApiKey());
		}

		response = httpClientImplFactory.getHTTPClient(requestDO.getHttpMethodType()).makeWSCall(
				environmentDetailsDO.getResourceURI(), requestDO.getOperationURI(),
				null != requestDO.getInputMediaType() ? MediaType.valueOf(requestDO.getInputMediaType()) : null,
				MediaType.valueOf(requestDO.getOutputMediaType()), requestDO.getInputJSON(), headerParameters);

		LOG.info("Server Response: " + response.getJsonReponse());
		return response;
	}

	/**
	 * @param requestDO
	 *            RequestDO
	 * @param error
	 *            ErrorDO
	 * @param actualResponse
	 *            String
	 * @return xlsResponseRow XLSResponseRow
	 */
	private XLSResponseRow populateResponseRow(RequestDO requestDO, ErrorDO error, ResponseDO responseDO) {

		XLSResponseRow xlsResponseRow = new XLSResponseRow();
		xlsResponseRow.setError(error);

		if (null != responseDO) {

			if (null != responseDO.getJsonReponse()) {
				xlsResponseRow.setActualResponse(responseDO.getJsonReponse());
			}

			if (responseDO.getResponseCode() > 0) {
				xlsResponseRow.setHttpResponseCode(Integer.toString(responseDO.getResponseCode()));
			}

		}

		xlsResponseRow.setExpectedresponse(requestDO.getExpectedJsonResponse());

		xlsResponseRow.setRequestDescription(requestDO.getRequestDescription());

		xlsResponseRow.setRequestid((new Double(requestDO.getRequestId())).longValue());

		xlsResponseRow.setOperationName(requestDO.getOperationName());

		xlsResponseRow.setAuthorization(requestDO.getAuthorization());

		xlsResponseRow.setHttpMethodType(requestDO.getHttpMethodType());
		
		xlsResponseRow.setExpectedJsonResponseData(requestDO.getExpectedJsonData());
		
		xlsResponseRow.setValidateValues(requestDO.getValidateValues());
		
		xlsResponseRow.setWarning(error.getWarning());

		return xlsResponseRow;
	}// end of method populateResponseRow

}
