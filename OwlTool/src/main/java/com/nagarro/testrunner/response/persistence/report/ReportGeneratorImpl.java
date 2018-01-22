/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.response.persistence.report;

import static java.util.Arrays.asList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nagarro.testrunner.response.dto.ErrorDO;
import com.nagarro.testrunner.response.dto.XLSResponseDO;
import com.nagarro.testrunner.response.dto.XLSResponseRow;

/**
 * This class generates excel report for soap based web services.
 *
 * @author anujmehra
 */
@Component("reportGenerator")
public class ReportGeneratorImpl extends AbstractReportGenerator {

	/**
	 * 
	 */
	@Value("${rest.testing.report.qualified.path}")
	private String reportQualifiedName;
	
	// header list for report
	/**
	 * The Constant HEADERS.
	 */
	private final static List<String> HEADERS = asList("RequestId","RequestDescription","HTTP Type","Authorization","OperationName", "HTTP Response Code","Status","Error","ExpectedSchema", "ActualResponse","ValidateValues","ExpectedResponse","Warning");
	
	/**
	 * Logger Object.
	 */
	private static final Logger LOG = Logger.getLogger(ReportGeneratorImpl.class);

	/**
	 * Write.
	 *
	 * @param reportList the report list
	 * @param currentTimeStamp the current time stamp
	 * @return the string
	 * @see com.nagarro.testrunner.manager.report.ReportGenerator#write(java.util.List)
	 */
	@Override
	public String write(XLSResponseDO responses, String outputReportName) {

		final List<XLSResponseRow> responseDOList = responses.getXlsResponseRows();

		
		final XSSFSheet sheet = this.createBlankSheet("RestFul Webservice Response");
		int rownum = 0;

		// creates header as first row
		writeHeader(sheet, rownum++);

		// write response details
		for (final XLSResponseRow response : responseDOList) {
			Row row = sheet.createRow(rownum++);
			setCellValues(response, row);
		}

		// Auto size the column widths
        for(int columnPosition = 0; columnPosition< HEADERS.size(); columnPosition++) {
             sheet.autoSizeColumn((short) (columnPosition));
        }
        String projectLocation = new File("").getAbsolutePath().replace("\\", "/");
		String reportLocation=projectLocation + reportQualifiedName + outputReportName;
		try {
			
			FileOutputStream out = new FileOutputStream(new File(reportLocation));
			getWorkbook().write(out);
			out.close();
			LOG.info("Excel written successfully..");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
 
 
  
		return reportLocation;
	}

	/**
	 * Sets cell values for response data.
	 *
	 * @param response the report
	 * @param row the row
	 */
	private void setCellValues(XLSResponseRow response, Row row) {

		int cellnum = 0;
				
		createFormattedCell(row, cellnum++, String.valueOf(response.getRequestid()));
		
		createFormattedCell(row, cellnum++, response.getRequestDescription());
		
		createFormattedCell(row, cellnum++, response.getHttpMethodType().toString());
		
		createFormattedCell(row, cellnum++, response.getAuthorization());
		
		createFormattedCell(row, cellnum++, response.getOperationName());
		
		createFormattedCell(row, cellnum++, response.getHttpResponseCode());
		
		setStatus(row, cellnum++, response.getError());
		
		if(null != response.getError()){
			createFormattedCell(row, cellnum++, response.getError().getErrorMessage());
		}else{
			createFormattedCell(row, cellnum++, "");
		}
		
		createFormattedCell(row, cellnum++, response.getExpectedresponse());
		createFormattedCell(row, cellnum++, response.getActualResponse());
		createFormattedCell(row, cellnum++, response.getValidateValues());
		createFormattedCell(row, cellnum++, response.getExpectedJsonResponseData());
		createFormattedCell(row, cellnum++, response.getWarning());
	}	


	/**
	 * Set status column based on success or failure of the test.
	 *
	 * @param row the row
	 * @param cellnum the cellnum
	 * @param error the error
	 */
	private void setStatus(Row row, int cellnum, ErrorDO error) {

		if (error==null || error.getErrorMessage() == null)
		{		
			createStatusCell(row, cellnum, PASS); 
		}
		else 
		{
			createStatusCell(row, cellnum, FAIL);
		}

	}
	
	/**
	 * Creates and format 'Status' cell.
	 *
	 * @param row the row
	 * @param cellnum the cellnum
	 * @param value the value
	 */

	private void createStatusCell(Row row, int cellnum, String value) {

		final Cell cell = row.createCell(cellnum);
		final CellStyle style = createCellStyleForCell();
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		
		if(value.equalsIgnoreCase(PASS))
		{
			style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
		}
		else
		{
			style.setFillForegroundColor(IndexedColors.RED.getIndex());
			style.setFillBackgroundColor(IndexedColors.RED.getIndex());
		}
		
		cell.setCellStyle(style);
		cell.setCellValue(value);
	}
	
	/**
	 * Creates a cell with style and populates it with response data.
	 *
	 * @param row the row
	 * @param cellnum the cellnum
	 * @param value the value
	 */
	private void createFormattedCell(Row row, int cellnum, String value) {
		final Cell cell = row.createCell(cellnum);
		cell.setCellStyle(createCellStyleForCell());
		cell.setCellValue(value);
	}

	/**
	 * Writes header in first row of the sheet.
	 *
	 * @param sheet the sheet
	 * @param rownum the rownum
	 */
	private void writeHeader(XSSFSheet sheet, Integer rownum) {

		int cellnum = 0;
		final Row row = sheet.createRow(rownum);

		while (cellnum < HEADERS.size()) {
			Cell cell = row.createCell(cellnum);
			cell.setCellStyle(createCellStyleForHeader());
			cell.setCellValue(HEADERS.get(cellnum++));
		}
	}

}