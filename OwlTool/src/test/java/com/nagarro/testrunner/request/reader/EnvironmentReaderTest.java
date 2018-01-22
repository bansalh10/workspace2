/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.request.reader;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.nagarro.testrunner.exception.RequestException;
import com.nagarro.testrunner.request.dto.EnvironmentDetailsDO;
import com.nagarro.testrunner.utils.FileNameWrapper;

@RunWith(MockitoJUnitRunner.class)
public class EnvironmentReaderTest {

	@InjectMocks
	private EnvironmentReader environmentReader;
	
	@Mock
	private FileNameWrapper fileNameWrapper;
	
	@Mock
	private XLSWorkbookReader<T> xlsWorkbookReader;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(environmentReader, "dataRunXlsLocation", "\\input");
	}
	
	@Test
	public void testRead(){
		try {
			Mockito.when(fileNameWrapper.hadValidFileName()).thenReturn(Boolean.TRUE);
			Mockito.when(fileNameWrapper.getFileName()).thenReturn("\\RestDataRun.xlsx");
			List<EnvironmentDetailsDO> envDetails = environmentReader.read();
			
			Assert.assertNotNull(envDetails);
		} catch (RequestException e) {
			Assert.fail();
		}
	}
	
}
