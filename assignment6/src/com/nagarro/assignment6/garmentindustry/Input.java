package com.nagarro.assignment6.garmentindustry;

import java.util.Scanner;

public class Input {
	private static Input input=null;
	private Scanner in = new Scanner(System.in);
	public static Input getInput() {
		if(input==null){
			input=new Input();
		}
		return input;
	}
    public String getSelection(){
    	String garmentType=in.nextLine();
		return garmentType;
    }
}
