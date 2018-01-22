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

import com.nagarro.testrunner.client.helper.HTTPMethodType;
import com.nagarro.testrunner.exception.RequestException;
import com.nagarro.testrunner.request.dto.RequestDO;
import com.nagarro.testrunner.request.dto.XLSEnum;

/**
 * Reads environment sheet of the input excel file.
 * 
 * @author anujmehra
 *
 */
@Component("requestReader")
public class RequestReader extends XLSWorkbookReader<RequestDO> {

	/**
	 * Read.
	 *
	 * @return list of environments
	 * @throws RequestException
	 *             the request exception
	 */
	public List<RequestDO> read() throws RequestException {

		List<RequestDO> requestDetails = new ArrayList<RequestDO>();

		// load input excel workbook
		XSSFWorkbook wb = super.readWorkbook();

		// read environment sheet
		XSSFSheet sheet = wb.getSheet(XLSEnum.RESOURCES.getXlsCode());
		requestDetails = this.getRequestDetails(sheet);

		return requestDetails;
	}

	/**
	 * Populates environment data list.
	 *
	 * @param sheet
	 *            the sheet
	 * @return list of environments
	 */
	private List<RequestDO> getRequestDetails(XSSFSheet sheet) {

		List<RequestDO> requestDetails = new ArrayList<RequestDO>();
		XSSFRow row;
		Iterator<Row> rows = sheet.rowIterator();

		// skip header info
		if (rows.hasNext()) {
			rows.next();
		}

		// iterate through each row
		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			RequestDO requestDO = this.setRequestRow(row);

			if (null != requestDO) {
				requestDetails.add(requestDO);
			}
		}

		return requestDetails;
	}

	/**
	 * Reads row data of the environment sheet
	 * 
	 *
	 * @param row
	 *            the row
	 * @return RequestDO
	 */
	private RequestDO setRequestRow(XSSFRow row) {

		RequestDO requestRow = new RequestDO();

		requestRow.setRequestId(row.getCell(0, Row.RETURN_BLANK_AS_NULL).getNumericCellValue());

		requestRow.setRequestDescription(this.getCellValue(row.getCell(1, Row.RETURN_BLANK_AS_NULL)));
		requestRow.setOperationName(this.getCellValue(row.getCell(2, Row.RETURN_BLANK_AS_NULL)));
		requestRow.setOperationURI(this.getCellValue(row.getCell(3, Row.RETURN_BLANK_AS_NULL)));

		requestRow.setHttpMethodType(HTTPMethodType.valueOf(this.getCellValue(row.getCell(4)).toUpperCase()));
		requestRow.setInputMediaType(this.getCellValue(row.getCell(5, Row.RETURN_BLANK_AS_NULL)));
		requestRow.setInputJSON(this.getCellValue(row.getCell(6, Row.RETURN_BLANK_AS_NULL)));

		requestRow.setOutputMediaType(this.getCellValue(row.getCell(7, Row.RETURN_BLANK_AS_NULL)));

		String expectedSchema = this.getCellValue(row.getCell(8, Row.RETURN_BLANK_AS_NULL));

		if (null != expectedSchema && expectedSchema.trim().length() > 0) {
			requestRow.setExpectedJsonResponse(requestRow.getHttpMethodType() + "/" + expectedSchema);
		} else {
			requestRow.setExpectedJsonResponse(expectedSchema);
		}

		requestRow.setAuthorization(this.getCellValue(row.getCell(9, Row.RETURN_BLANK_AS_NULL)));
		requestRow
				.setRun((this.getCellValue(row.getCell(10, Row.RETURN_BLANK_AS_NULL)).trim().toUpperCase().equals("Y"))
						? true : false);
		
			requestRow.setValidateValues(this.getCellValue(row.getCell(11, Row.RETURN_BLANK_AS_NULL)));
		
		requestRow.setExpectedJsonData(this.getCellValue(row.getCell(12, Row.RETURN_BLANK_AS_NULL)));
		return requestRow.isRun() ? requestRow : null;
	}

	/**
	 * 
	 * @param cell
	 * @return
	 */
	private String getCellValue(Cell cell) {

		if (cell == null) {
			return null;
		} else {
			return cell.getStringCellValue();
		}
	}

}
