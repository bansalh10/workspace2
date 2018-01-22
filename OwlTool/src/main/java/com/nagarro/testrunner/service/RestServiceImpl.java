/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.nagarro.testrunner.manager.JSONRestManager;
import com.nagarro.testrunner.request.LoadTestData;
import com.nagarro.testrunner.request.dto.EnvironmentDetailsDO;
import com.nagarro.testrunner.request.dto.RequestDO;
import com.nagarro.testrunner.request.dto.TestDataDO;
import com.nagarro.testrunner.response.dto.XLSResponseDO;
import com.nagarro.testrunner.response.dto.XLSResponseRow;
import com.nagarro.testrunner.response.helper.ResponseStatus;
import com.nagarro.testrunner.response.persistence.email.SendEmail;
import com.nagarro.testrunner.response.persistence.report.ReportGenerator;
import com.nagarro.testrunner.utils.BarChartGenerator;
import com.nagarro.testrunner.utils.FileNameWrapper;

/**
 * This is the service class for this application. This class is responsible to
 * perform following tasks: 1. Load TestSuite excel sheet and populate
 * corresponding POJO's 2. Make a call to the Manager layer of the application
 * 3. Make a call to the ReportGenerator that generate's response Excel sheet.
 * 
 * @author anujmehra
 *
 */
@Service("restService")
public class RestServiceImpl implements RestService {

	/**
	 * Reference for JSONRestManager.
	 */
	@Autowired
	private JSONRestManager jsonRestManager;

	/**
	 * Reference for LoadTestData
	 */
	@Autowired
	private LoadTestData loadTestData;

	/**
	 * Reference for ReportGenerator
	 */
	@Autowired
	private ReportGenerator reportGenerator;

	@Autowired
	private SendEmail emailNotifier;

	/**
	 * Reference for FileNameWrapper.
	 */
	@Autowired
	private FileNameWrapper inputTestCaseFile;

	@Autowired
	private TemplateEngine htmlTemplateEngine;
	

	@Autowired
	BarChartGenerator barChartGenerator;

	/**
	 * Constant read from property file. Represents the name of the input
	 * Markdown file that was used to generate the JSON schemas. This name is
	 * used at the time of final report generation.
	 */
	@Value("${input.markdown.file.name}")
	private String INPUT_MARKDOWN_FILE_NAME;

	private static final String HTML_TEMPLATE_NAME = "html/emailHtmlFile";

	/**
	 * Logger Object.
	 */
	private static final Logger LOG = Logger.getLogger(RestServiceImpl.class);

	/**
	 * This is the service method from which the testing starts.
	 * 
	 * @param testSuiteFileName
	 *            String
	 */
	public void execute(String testSuiteFileName) {
		inputTestCaseFile.setFileName(testSuiteFileName);

		final String outputReportName = INPUT_MARKDOWN_FILE_NAME + "_Report_"
				+ this.getCurrentTimestamp().toString().replace(" ", "_").replace(":", ".") + ".xlsx";

		final XLSResponseDO xlsResponseDO = new XLSResponseDO();

		final List<XLSResponseRow> getXlsResponseRows = new ArrayList<XLSResponseRow>();

		final TestDataDO testDataDO = loadTestData.loadData();

		final EnvironmentDetailsDO environmentDetailsDO = testDataDO.getEnvironmentDetails();
		if (environmentDetailsDO != null) {
			for (final RequestDO requestDO : testDataDO.getResource().getRequests()) {
				if (requestDO.getOutputMediaType().equalsIgnoreCase(MediaType.APPLICATION_JSON)) {
					XLSResponseRow xlsResponseRow = jsonRestManager.callRestWS(requestDO, environmentDetailsDO);
					getXlsResponseRows.add(xlsResponseRow);
				}
			}

			xlsResponseDO.setXlsResponseRows(getXlsResponseRows);

			// Reporting Generation
			// this.generateReport(xlsResponseDO, outputReportName);
			String reportLocation = this.generateReport(xlsResponseDO, outputReportName);
			 String htmlFromReport=this.generateHtmlOfReport(getXlsResponseRows);
			// Email
			this.doEmail(xlsResponseDO, testDataDO, reportLocation,htmlFromReport);
		}
	}// end of method callRestWS

	/**
	 * 
	 * @param xlsResponseDO
	 *            XLSResponseDO
	 * @param outputReportName
	 *            String
	 * @return reportQualifiedName String
	 */
	private String generateReport(XLSResponseDO xlsResponseDO, String outputReportName) {

		// Do Reporting here.
		LOG.info("Received: " + xlsResponseDO.getXlsResponseRows().size() + " response(s)");
		return reportGenerator.write(xlsResponseDO, outputReportName);
	}

	private void doEmail(XLSResponseDO xlsResponseDO, TestDataDO testDataDO, String reportLocation, String htmlFromReport) {
		int totalRuns = 0;
		int successRuns = 0;
		int failureRuns = 0;
		for (XLSResponseRow xlsResponseRow : xlsResponseDO.getXlsResponseRows()) {
			totalRuns++;
			if (xlsResponseRow.getStatus().equals(ResponseStatus.Pass)) {
				successRuns++;
			} else {
				failureRuns++;
			}
		}

		String content = "Total Runs:- " + totalRuns + ",Successful Runs:- " + successRuns + ",Failure Runs:- "
				+ failureRuns;
        //Generate BarChart for test runs
		byte[] imageBytes=null;
		try {
			 imageBytes = barChartGenerator.createSimpleBarChart(totalRuns, successRuns, failureRuns);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// DO email.
		emailNotifier.notificationWithAttachment(testDataDO.getEmails(), reportLocation, content,htmlFromReport,imageBytes);
	}

	private String generateHtmlOfReport(List<XLSResponseRow> getXlsResponseRows) {
		String htmlContent;
		final Context ctx = new Context();
		ctx.setVariable("responses", getXlsResponseRows);
		htmlContent = this.htmlTemplateEngine.process(HTML_TEMPLATE_NAME, ctx);
		return htmlContent;
	}

	/**
	 * Gets the current timestamp.
	 *
	 * @return the current timestamp
	 */
	private String getCurrentTimestamp() {
		return (new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
	}

}
