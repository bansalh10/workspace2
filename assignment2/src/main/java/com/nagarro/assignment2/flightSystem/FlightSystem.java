package com.nagarro.assignment2.flightSystem;

import java.util.Scanner;

import com.nagarro.assignment2.data.FlightDataDs;
import com.nagarro.assignment2.services.CheckNewFile;
import com.nagarro.assignment2.services.ReadAllFiles;

public class FlightSystem {
	public static void main(String[] args) {

		String depLoc, arrLoc, flightDate, flightClass, outputPreference;
		String status = "y";
		ReadAllFiles readallfiles = new ReadAllFiles();

		readallfiles.run();
		CheckNewFile checknewfile = new CheckNewFile();
		checknewfile.loadNewfile();
		Scanner input = new Scanner(System.in);
		while (status.equalsIgnoreCase("y")) {
			System.out.println("Enter Departure location,Arrival Location,Flight Date,Flight class,Output Preference");

			depLoc = input.nextLine();
			arrLoc = input.nextLine();
			flightDate = input.nextLine();
			flightClass = input.nextLine();
			outputPreference = input.nextLine();
			FlightDataDs.getFlights(depLoc, arrLoc, flightDate, flightClass, outputPreference);
			System.out.println("Do you want to continue(y/n)");
			status = input.nextLine();
		}
		checknewfile.stopTimerThread();
	}

}
