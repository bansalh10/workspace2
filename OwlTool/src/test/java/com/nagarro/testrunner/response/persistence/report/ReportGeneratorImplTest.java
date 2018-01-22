package com.nagarro.testrunner.response.persistence.report;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.nagarro.testrunner.client.helper.HTTPMethodType;
import com.nagarro.testrunner.response.dto.XLSResponseDO;
import com.nagarro.testrunner.response.dto.XLSResponseRow;

@RunWith(MockitoJUnitRunner.class)
public class ReportGeneratorImplTest {

	@InjectMocks
	private ReportGeneratorImpl reportGenerator;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(reportGenerator, "reportQualifiedName", "OutputFileFullyQualifiedPath");
	}
	
	@Test
	public void testWrite(){
		
		try{
			final XLSResponseDO responses = new XLSResponseDO();
			
			final List<XLSResponseRow> xlsResponseRows = new ArrayList<XLSResponseRow>();
			
			XLSResponseRow xlsResponseRow = new XLSResponseRow();
			xlsResponseRow.setActualResponse("{}");
			xlsResponseRow.setAuthorization("tg");
			xlsResponseRow.setError(null);
			xlsResponseRow.setExpectedresponse("Test.json");
			xlsResponseRow.setHttpResponseCode("200");
			xlsResponseRow.setOperationName("TestOperation");
			xlsResponseRow.setRequestDescription("RequestDescription");
			xlsResponseRow.setRequestid(1);
			xlsResponseRow.setHttpMethodType(HTTPMethodType.GET);
			xlsResponseRow.setExpectedJsonResponseData("{}");
			xlsResponseRow.setValidateValues("");
			xlsResponseRow.setWarning("");
			xlsResponseRows.add(xlsResponseRow);
			responses.setXlsResponseRows(xlsResponseRows);
			final String outputReportName = "junitOutput";
			
			String reportName = reportGenerator.write(responses, outputReportName);
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail();
		}
		
	}
}
