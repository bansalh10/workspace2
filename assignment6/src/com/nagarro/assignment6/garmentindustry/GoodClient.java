package com.nagarro.assignment6.garmentindustry;

import com.nagarro.assignment6.data.Constants;
import com.nagarro.assignment6.data.Garment;
import com.nagarro.assignment6.services.DataHandler;

public class GoodClient {

	public static void main(String[] args) {
		Output.getOutput().showmsg(Constants.choiceMsg);
		String garmentType=Input.getInput().getSelection();
        Garment garment=DataHandler.gethandler().getGarment(garmentType);
        if(garment!=null){
        	garment.print();
        }
        else{
        	Output.getOutput().showmsg(Constants.invalidMsg);
        }
	}

}
