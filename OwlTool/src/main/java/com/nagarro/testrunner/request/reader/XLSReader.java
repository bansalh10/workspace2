/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.request.reader;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nagarro.testrunner.exception.RequestException;

/**
 * This interface reads data from Excel file.
 *
 * @author anujmehra
 * @param <T> the generic type
 */
public interface XLSReader<T> {
	
	/**
	 * Read workbook.
	 *
	 * @return XSSFWorkbook wb
	 * @throws RequestException the request exception
	 */
	public XSSFWorkbook readWorkbook() throws RequestException;
	
	/**
	 * Read.
	 *
	 * @return the list
	 * @throws RequestException the request exception
	 */
	public List<T> read() throws RequestException;
	
}
