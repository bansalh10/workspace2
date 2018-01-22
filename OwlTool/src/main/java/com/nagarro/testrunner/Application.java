/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner;

import com.nagarro.testrunner.config.ServiceRun;
import com.nagarro.testrunner.service.RestService;

/**
 * This is the Application java class. This class is called from the batch/shell script.
 * This class has following responsibilities:
 * 		1. Load spring configuration
 * 		2. Call application's service layer
 * 
 * @author anujmehra
 *
 */
public final class Application {

	/**
	 * Made this utility class.
	 */
	private Application(){
	}
	
	/**
	 * This is the main method for this stand alone application.
	 * This method is called from the batch/shell script.
	 * 
	 * @param args... String
	 */
	public static void main(String... args){
		
		String fileName =  null;
		
		if(args.length > 0)
	    {
			final String fileIdentifier = args[0];
			
			if(args.length >1){
				fileName = args[1];
			}
			
			System.out.println("Processing file provided in argument: "+fileName);
			
			if(fileName == null || !fileIdentifier.equals("-file")){
	           System.out.println("Proper Usage is: -file <FILE_NAME>");
	           System.exit(0);
	        }
	    }
		
		final RestService restService = ServiceRun.getExecutableService();		
		
		restService.execute(fileName);
	}//end of 'main' method.
	
	
}//end of class Application
