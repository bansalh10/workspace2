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
import com.nagarro.testrunner.request.dto.EnvironmentDetailsDO;
import com.nagarro.testrunner.request.dto.XLSEnum;


/**
 * Reads environment sheet of the input excel file.
 * 
 * @author anujmehra
 *
 */
@Component("environmentReader")
public class EnvironmentReader extends XLSWorkbookReader<EnvironmentDetailsDO>{

	/**
	 * Read.
	 *
	 * @return list of environments
	 * @throws RequestException the request exception
	 */
	public List<EnvironmentDetailsDO> read() throws RequestException
	{

		List<EnvironmentDetailsDO> envDetails = new ArrayList<EnvironmentDetailsDO>();

		// load input excel workbook
		XSSFWorkbook wb = super.readWorkbook();

		// read environment sheet
		XSSFSheet sheet = wb.getSheet(XLSEnum.ENVIRONMENT.getXlsCode());
		envDetails = getEnviromentDetails(sheet);

		return envDetails;
	}

	/**
	 * Populates environment data list.
	 *
	 * @param sheet the sheet
	 * @return list of environments
	 */
	private List<EnvironmentDetailsDO> getEnviromentDetails(XSSFSheet sheet) {

		List<EnvironmentDetailsDO> envDetails = new ArrayList<EnvironmentDetailsDO>();
		
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
			row= (XSSFRow) rows.next();
			
			EnvironmentDetailsDO environmentDetailsDO = setEnvironmentRow(row);
			
			if(null != environmentDetailsDO){
				envDetails.add(environmentDetailsDO);
			}
					
			
		}

		return envDetails;
	}

	/**
	 * Reads row data of the environment sheet
	 *   
	 *
	 * @param row the row
	 * @return EnvironmentDetailsDO
	 */
	private EnvironmentDetailsDO setEnvironmentRow(XSSFRow row) {

		EnvironmentDetailsDO envRow = new EnvironmentDetailsDO();
		Iterator<Cell> cellIterator = row.iterator();

		// set each cell data in EnvironmentDetailsDO		
		if (cellIterator.hasNext())
		{
			envRow.setEnvironment(cellIterator.next().getStringCellValue().trim());
			envRow.setResourceURI(cellIterator.next().getStringCellValue().trim());
			//envRow.setAuthorization(cellIterator.next().getStringCellValue().trim());
			envRow.setWebApiKey(cellIterator.next().getStringCellValue().trim());
			envRow.setRun((cellIterator.next().getStringCellValue().trim().toUpperCase().equals("Y")) ? true : false);
		}

		return envRow.isRun() ? envRow : null;
	}

}
