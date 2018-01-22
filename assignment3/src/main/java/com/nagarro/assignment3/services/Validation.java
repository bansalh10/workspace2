package com.nagarro.assignment3.services;

import com.nagarro.assignment3.data.Constants;

public class Validation {
	private static Validation validate=null;
	
      public static Validation getValidate() {
    	  if(validate==null){
    		  validate=new Validation();
    	  }
		return validate;
	}
	public boolean validFlightClass(String flightClass){
    	  boolean status=false;
    	  for(Constants.flightClass f: Constants.flightClass.values()){
    		  if(flightClass.equalsIgnoreCase(f.toString())){
    			  status=true;
    			  break;
    		  }
    	  }
		return status;
      }
      public boolean validPreference(String fPreference){
    	  boolean status=false;
    	  for(Constants.preference p:Constants.preference.values()){
    		  if(fPreference.equalsIgnoreCase(p.toString())){
    			  status=true;
    			  break;
    		  }
    	  }
		return status;
    	  
      }
}
