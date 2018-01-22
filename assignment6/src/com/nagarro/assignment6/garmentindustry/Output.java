package com.nagarro.assignment6.garmentindustry;

public class Output {
	private static Output output=null;
	public static Output getOutput() {
		if(output==null){
			output=new Output();
		}
		return output;
	}
	public void showmsg(String msg){
		System.out.println(msg);
	}
}
