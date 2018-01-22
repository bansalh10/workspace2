/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.response.persistence.report;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * General implementation class for excel report generation to support its sub classes
 *  
 * @author anujmehra
 *
 */
public abstract class AbstractReportGenerator implements ReportGenerator {

	/** The workbook. */
	private static XSSFWorkbook  workbook = new XSSFWorkbook();
	
	/** The Constant SUCCESS. */
	public final static String PASS = "PASS";
	
	/** The Constant FAILURE. */
	public final static String FAIL = "FAIL";

	/**
	 * Creates the blank sheet.
	 *
	 * @param sheetName the sheet name
	 * @return the XSSF sheet
	 * @see com.nagarro.testrunner.manager.report.ReportGenerator#createBlankExcel(java.lang.String)
	 */
	@Override
	public XSSFSheet createBlankSheet(String sheetName) 
	{
		return workbook.createSheet(sheetName);
	}

	/**
	 * Gets the workbook.
	 *
	 * @return workbook
	 */
	public XSSFWorkbook getWorkbook()
	{
		return workbook;
	}
	
	/**
	 * Sets style for header.
	 *
	 * @return cellStyle for header
	 */
	public CellStyle createCellStyleForHeader()
	{
		XSSFCellStyle style  = getWorkbook().createCellStyle();
		
		Font headerFont = getWorkbook().createFont();
        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont.setFontHeightInPoints((short) 12);
        
        style.setFont(headerFont);
		
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFillBackgroundColor(IndexedColors.SKY_BLUE.index);
		style.setFillForegroundColor(IndexedColors.SKY_BLUE.index);
		
		return 	createGeneralStyleForCell(style);
		
	}

	 /**
 	 *  Sets style for cells.
 	 *
 	 * @return cellStyle
 	 */
	public CellStyle createCellStyleForCell()
	{
		XSSFCellStyle style  = getWorkbook().createCellStyle();
		return createGeneralStyleForCell(style);
	}
	
	/**
	 *  Sets thin border for cells.
	 *
	 * @param style the style
	 * @return cellStyle
	 */
	private CellStyle createGeneralStyleForCell(XSSFCellStyle style)
	{	
		// set alignment and borders
		style.setAlignment(CellStyle.ALIGN_LEFT);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		
		return style;
	}

}