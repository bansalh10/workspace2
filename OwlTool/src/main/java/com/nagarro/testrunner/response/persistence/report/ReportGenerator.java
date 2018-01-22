/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.response.persistence.report;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.nagarro.testrunner.response.dto.XLSResponseDO;

/**
 * This class is generalization for writing report .
 *
 * @author anujmehra
 */
public interface ReportGenerator {
	
	/**
	 * Writes a report with response data.
	 *
	 * @param reportList the report list
	 * @param currentTimeStamp the current time stamp
	 * @return the string
	 */
	public String write(XLSResponseDO reportList, String outputReportName);
	
	/**
	 * Creates a blank excel sheet.
	 *
	 * @param sheetName the sheet name
	 * @return the XSSF sheet
	 */
	public XSSFSheet createBlankSheet(String sheetName);

}
