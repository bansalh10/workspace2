package com.nagarro.assignment6.services;

import com.nagarro.assignment6.data.Constants;
import com.nagarro.assignment6.data.Garment;
import com.nagarro.assignment6.data.implementations.Shirt;
import com.nagarro.assignment6.data.implementations.Trouser;

public class DataHandler {
	private static DataHandler handler=null;
	public static DataHandler gethandler() {
		if(handler==null){
			handler=new DataHandler();
		}
		return handler;
	}
	public Garment getGarment(String garmenttype){
		Garment garment = null;
		if(garmenttype.equalsIgnoreCase(Constants.garmentTypes.shirt.toString())){
			garment=new Shirt();
		}
		if(garmenttype.equalsIgnoreCase(Constants.garmentTypes.trouser.toString())){
			garment=new Trouser();
		}
		return garment;
	}
}
