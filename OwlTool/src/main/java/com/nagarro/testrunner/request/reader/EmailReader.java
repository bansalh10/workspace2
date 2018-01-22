/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.request.reader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.nagarro.testrunner.exception.RequestException;
import com.nagarro.testrunner.request.dto.EmailDO;
import com.nagarro.testrunner.request.dto.XLSEnum;

/**
 * This class reads email tab of the input excel.
 * 
 * @author anujmehra
 *
 */
@Component("emailReader")
public class EmailReader extends XLSWorkbookReader{
	
	/**
	 * Reads email from email tab of the input excel.
	 *
	 * @return list of emails
	 * @throws RequestException the request exception
	 */
	@Override
	public List<EmailDO> read() throws RequestException
	{
		
		List<EmailDO> emails= new ArrayList<EmailDO>();
		
		// load input excel workbook
		XSSFWorkbook wb = super.readWorkbook();
		
		// read environment sheet
		XSSFSheet sheet = wb.getSheet(XLSEnum.EMAIL.getXlsCode());
		emails = getEmails(sheet);
		
		return emails;
	}
	
	/**
	 * Populates email list.
	 *
	 * @param sheet the sheet
	 * @return list of emails
	 */
	private List<EmailDO> getEmails(XSSFSheet sheet) {
		
		List<EmailDO> emails = new ArrayList<EmailDO>();
		XSSFRow row; 
		Iterator<Row> rows = sheet.rowIterator();
			
		// skip header info
		if(rows.hasNext())
		{
			rows.next(); 
		}
		
		// iterate through each row
		while (rows.hasNext())
		{
			row=(XSSFRow) rows.next();
			emails.add(populateEmail(row));
		}
		
		return emails;
	}
	
	/**
	 * Reads a row of the email sheet.
	 *
	 * @param row the row
	 * @return the email do
	 */
	private EmailDO populateEmail(XSSFRow row) {
		
		EmailDO email = new EmailDO();
		Iterator<Cell> cellIterator = row.iterator();
		
		// set each cell data in EnvironmentDetailsDO		
		if (cellIterator.hasNext())
		{
			email.setRecipient(cellIterator.next().getStringCellValue());
			//email.setSubject(cellIterator.next().getStringCellValue());
		}
		
		return email;
	}

}
