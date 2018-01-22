package com.nagarro.assignment3.flightsystem;

import java.util.List;
import java.util.Scanner;

import com.nagarro.assignment3.data.Flight;
import com.nagarro.assignment3.services.CustomException;
import com.nagarro.assignment3.services.Manager;
import com.nagarro.assignment3.services.Validation;

/**
 * This class is used for getting input from user.
 * 
 * @author himanshubansal
 *
 */
public class Input {
	private static Input input = null;
	private Scanner in = new Scanner(System.in);

	public static Input getInput() {
		if (input == null) {
			input = new Input();
		}
		return input;
	}

	/**
	 * Get type of db connection required from user.
	 * 
	 * @return
	 */
	public String enterDbConnectivity() {
		String databaseConnection;
		databaseConnection = in.nextLine();
		return databaseConnection;
	}

	/**
	 * Get list of flights on the basis of requirements entered by user.
	 * 
	 * @return
	 * @throws CustomException
	 */
	public List<Flight> getPreferredFlights() throws CustomException {
		String depLoc = in.nextLine();
		String arrLoc = in.nextLine();
		String flightDate = in.nextLine();
		String flightClass = in.nextLine();
		String outputPreference = in.nextLine();
		List<Flight> flights = null;
		if (!Validation.getValidate().validFlightClass(flightClass)) {
			throw new CustomException("Invalid Flight Class");
		}
		if (!Validation.getValidate().validPreference(outputPreference)) {
			throw new CustomException("Invalid Output Preference");
		}
		flights = Manager.getDataOperation().getDataFomDb(depLoc, arrLoc, flightDate, flightClass, outputPreference);
		return flights;
	}

	/**
	 * get status from user if he wants to continue or not.
	 * 
	 * @return
	 */
	public String enterStatus() {
		String status;
		status = in.nextLine();
		return status;
	}

	/**
	 * close Scanner object used so no more input can be taken.
	 */
	public void closeScanner() {
		in.close();
	}
}
