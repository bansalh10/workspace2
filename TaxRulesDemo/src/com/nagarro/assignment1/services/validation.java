package com.nagarro.assignment1.services;

import java.util.regex.Pattern;

import com.nagarro.assignment1.data.ItemType;
import com.nagarro.assignment1.data.constants;

public class validation {

	public boolean validateName(String[] individual_tokens) {
		boolean nameValidated=true;
		if (!individual_tokens[0].equalsIgnoreCase("name")) {
			System.out.println("Name should be entered first");
			nameValidated= false;
		}
		return nameValidated;
	}

	public ItemType validateType(String[] individual_tokens) {
        ItemType type=ItemType.getType(individual_tokens[1]);
		if(type==null){
			System.out.println("Type is incorrect");
		}
		
		return type;
	}

	public boolean validatePrice(String[] individual_tokens) {
		String regex = constants.getRegex();

		return Pattern.matches(regex, individual_tokens[1]);
	}

	public boolean validateQuantity(String[] individual_tokens) {
		String regex = constants.getRegex();

		return Pattern.matches(regex, individual_tokens[1]);
	}

}
