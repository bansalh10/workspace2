/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.manager;

import com.nagarro.testrunner.request.dto.EnvironmentDetailsDO;
import com.nagarro.testrunner.request.dto.RequestDO;
import com.nagarro.testrunner.response.dto.XLSResponseRow;

/**
 * This is the contract for manager layer
 * @author anujmehra
 *
 */
public interface JSONRestManager {

	/**
	 * 
	 * @param requestDO RequestDO
	 * @param environmentDetailsDO EnvironmentDetailsDO
	 * @return xlsesponseRow XLSResponseRow
	 */
	public abstract XLSResponseRow callRestWS(RequestDO requestDO, EnvironmentDetailsDO environmentDetailsDO);
	
	/*public abstract  ResponseDO makeWSCall(RequestDO requestDO, EnvironmentDetailsDO environmentDetailsDO) throws ApplicationException;
	
	public abstract XLSResponseRow populateResponseRow(RequestDO requestDO, ErrorDO error, String actualResponse);
	
	
		*/
	
}
