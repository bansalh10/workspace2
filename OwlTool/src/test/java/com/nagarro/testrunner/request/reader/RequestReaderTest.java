package com.nagarro.testrunner.request.reader;

import java.util.List;

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
import com.nagarro.testrunner.request.dto.RequestDO;
import com.nagarro.testrunner.utils.FileNameWrapper;

@RunWith(MockitoJUnitRunner.class)
public class RequestReaderTest {
	
	@InjectMocks
	private RequestReader requestReader;
	
	@Mock
	private FileNameWrapper fileNameWrapper;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(requestReader, "dataRunXlsLocation", "\\input");
	}

	
	@Test
	public void testRead(){
		try {
			Mockito.when(fileNameWrapper.hadValidFileName()).thenReturn(Boolean.TRUE);
			Mockito.when(fileNameWrapper.getFileName()).thenReturn("\\RestDataRun.xlsx");
			List<RequestDO> requestDetails = requestReader.read();
			
			Assert.assertNotNull(requestDetails);
		} catch (RequestException e) {
			Assert.fail();
		}
	}
	
}
