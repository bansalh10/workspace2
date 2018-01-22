package com.nagarro.assignment1.itemAmountCalculator;

import com.nagarro.assignment1.data.Item;
import com.nagarro.assignment1.data.ItemType;
import com.nagarro.assignment1.services.*;

public class inputChecker {

	Item item;
	ItemType getItemtype;
	validation validate = new validation();

	public Item ValidItemCreation(String inputData) {
		String name = null;

		double price = 0;
		double quantity = 0;
		String[] tokensSeparatedWithMinus;
		String[] individual_tokens;
		boolean priceAdded = false;
		boolean typeAdded = false;
		boolean quantityAdded = false;

		boolean validName = true, validPrice = true, validQuantity = true, validType = true;
		item = null;
		tokensSeparatedWithMinus = inputData.split("-");
		for (int i = 1; i < tokensSeparatedWithMinus.length; i++) {
			tokensSeparatedWithMinus[i] = tokensSeparatedWithMinus[i].replaceAll("\\s+", " ");
			tokensSeparatedWithMinus[i] = tokensSeparatedWithMinus[i].trim();
			individual_tokens = tokensSeparatedWithMinus[i].split("\\s");
			if (individual_tokens.length < 2) {
				System.out.println("Invalid input as it should include " + individual_tokens[0]);
				return item;
			}
			if (i == 1) {
				if (validate.validateName(individual_tokens)) {
					name = individual_tokens[1];
				} else {
					validName = false;
				}
			} else if (individual_tokens[0].equalsIgnoreCase("type")) {
				typeAdded = true;
				getItemtype = validate.validateType(individual_tokens);
				if (getItemtype == null) {
					validType = false;
				}
			} else if (individual_tokens[0].equalsIgnoreCase("price")) {
				priceAdded = true;
				if (validate.validatePrice(individual_tokens)) {

					price = Double.parseDouble(individual_tokens[1]);
				} else {
					System.out.println("Price invalid");
					validPrice = false;
				}
			} else if (individual_tokens[0].equalsIgnoreCase("quantity")) {
				quantityAdded = true;
				if (validate.validateQuantity(individual_tokens)) {
					quantity = Double.parseDouble(individual_tokens[1]);
				} else {
					System.out.println("Quantity invalid");
					validQuantity = false;
				}
			}

		}
		if (!quantityAdded) {
			quantity = 1;
		}
		if (!priceAdded || !typeAdded) {
			System.out.println("Price and type are must");
		}

		if (priceAdded && typeAdded && validName && validPrice && validQuantity && validType) {

			System.out.println("System is working");
			item = getItemtype.ItemCreation(name, getItemtype, price, quantity);
			item.amountCalculator();

			item.showDetails();

		}
		return item;
	}

}
