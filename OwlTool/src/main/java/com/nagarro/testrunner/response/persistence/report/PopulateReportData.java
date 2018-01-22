/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.response.persistence.report;

import com.nagarro.testrunner.exception.ApplicationException;
import com.nagarro.testrunner.response.dto.XLSResponseDO;
import com.nagarro.testrunner.response.dto.XLSResponseRow;

/**
 * The Interface PopulateReportData.
 */
public interface PopulateReportData {

	/**
	 * Populate report do.
	 *
	 * @param responseDO the response do
	 * @return the report do
	 * @throws ApplicationException the application exception
	 */
	public XLSResponseDO populateReportDO(XLSResponseRow responseRow) throws ApplicationException; 
}
