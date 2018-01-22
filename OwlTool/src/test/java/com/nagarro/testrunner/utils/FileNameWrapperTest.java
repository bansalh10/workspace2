package com.nagarro.testrunner.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FileNameWrapperTest {

	@InjectMocks
	private FileNameWrapper fileNameWrapper;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testRead(){
		fileNameWrapper.setFileName("FileName");
		String fileName = fileNameWrapper.getFileName();
		
		Assert.assertEquals(fileName, "FileName");
		
		String var = fileNameWrapper.toString();
		
		Assert.assertEquals(var, "FileNameWrapper [name=FileName]");
	}
	
}
