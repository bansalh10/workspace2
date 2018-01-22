package com.nagarro.assignment3.services;

import com.nagarro.assignment3.flightsystem.Output;

public class CustomException extends Exception{
	private String msg;
	public CustomException(String msg) {
		this.msg=msg;
	}
	public void display(){
		Output.getOutput().showMsg(msg);
	}

}
