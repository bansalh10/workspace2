/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.request.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nagarro.testrunner.exception.RequestException;
import com.nagarro.testrunner.utils.FileNameWrapper;

/**
 * 
 * @author anujmehra
 *
 * @param <T>
 */
@Component("xlsWorkbookReader")
public abstract class XLSWorkbookReader<T> implements XLSReader<T>{

	/** The xssf workbook. */
	public XSSFWorkbook xssfWorkbook = null;

	/**
	 * 
	 */
	@Value("${datarun.xls.qualified.path}")
	private String dataRunXlsLocation;

	/**
	 * 
	 */
	@Value("${input.testsuite.excel.name}")
	private String testSuiteExcelName;
	
	/**
	 * 
	 */
	@Autowired
	private FileNameWrapper fileNameWrapper;

	/**
	 * 
	 */
	private static final Logger LOG = Logger.getLogger(XLSWorkbookReader.class);

	/* (non-Javadoc)
	 * @see com.nagarro.testrunner.manager.request.reader.xls.XLSReader#readWorkbook()
	 */
	public XSSFWorkbook readWorkbook() throws RequestException
	{
		// Override file name present in properties file if user specifies as run time argument
		if(fileNameWrapper.hadValidFileName()){
			testSuiteExcelName = fileNameWrapper.getFileName();
			LOG.info("Overriding testcase file name present in configs.");
		}
		
		LOG.info("Using testSuiteExcelName : "+testSuiteExcelName);
		
		if(null == xssfWorkbook){
			InputStream excelFileToRead = null;
			try {
				String projectLocation = new File("").getAbsolutePath().replace("\\", "/");

				excelFileToRead = new FileInputStream(projectLocation + dataRunXlsLocation + testSuiteExcelName);

				xssfWorkbook = new XSSFWorkbook(excelFileToRead); 

			} catch (FileNotFoundException fileNotFoundException) {
				throw new RequestException(fileNotFoundException.getMessage());
			} catch (IOException ioException) {
				throw new RequestException(ioException.getMessage());
			}
		}

		return xssfWorkbook;
	}
}
