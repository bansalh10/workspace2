/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.response.dto;

import java.util.List;


/**
 * 
 * @author anujmehra
 *
 */
public class XLSResponseDO {

	/**
	 * 
	 */
	private List<XLSResponseRow> xlsResponseRows;

	/**
	 * @return the xlsResponseRows
	 */
	public List<XLSResponseRow> getXlsResponseRows() {
		return xlsResponseRows;
	}

	/**
	 * @param xlsResponseRows the xlsResponseRows to set
	 */
	public void setXlsResponseRows(List<XLSResponseRow> xlsResponseRows) {
		this.xlsResponseRows = xlsResponseRows;
	}
	
	
}
