package com.nagarro.assignment1.itemAmountCalculator;

import com.nagarro.assignment1.data.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		
		Item item;
		ArrayList<Item> items = new ArrayList<>();
		Scanner input = new Scanner(System.in);
		String status = "y";
		inputChecker check = new inputChecker();
		do {
			System.out.println("Enter the item details");
			String inputData = input.nextLine();

			item=check.ValidItemCreation(inputData);
			if(item!=null){
				items.add(item);
			}
			
			System.out.println("do you want to enter more?(y/n)");
			status = input.nextLine();

		} while (status.equalsIgnoreCase("y"));

		System.out.println("do you want to see all the items?(y/n)");
		status = input.nextLine();
		if (status.equals("y")) {
			if (items.isEmpty()) {
				System.out.println("No items added");
			} else {
				for (Item listitem : items) {
					listitem.showDetails();
				}
			}
		}

	}

}
