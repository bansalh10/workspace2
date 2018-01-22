package com.nagarro.assignment3.flightsystem;

import java.util.List;

import com.nagarro.assignment3.data.Constants;
import com.nagarro.assignment3.data.Flight;

/**
 * This class is used to show output to user.
 * 
 * @author himanshubansal
 *
 */
public class Output {
	private static Output output = null;

	public static Output getOutput() {
		if (output == null) {
			output = new Output();
		}
		return output;
	}

	/**
	 * print message passed as argument.
	 * 
	 * @param msg
	 */
	public void showMsg(String msg) {
		System.out.println(msg);
	}

	/**
	 * Show details of all the flights in the list passed as argument.
	 * 
	 * @param flights
	 */
	public void showFlights(List<Flight> flights) {
		for (Flight flight : flights) {
			showMsg(Constants.fNumber + Constants.space + flight.getFlightNumber() + Constants.tabSpace
					+ Constants.arrLoc + Constants.space + flight.getArrLoc() + Constants.tabSpace + Constants.depLoc
					+ Constants.space + flight.getDepLoc() + Constants.tabSpace + Constants.fare + Constants.space
					+ flight.getFare() + Constants.tabSpace + Constants.fTime + Constants.space + flight.getFlightTime()
					+ Constants.tabSpace + Constants.fClass + Constants.space
					+ flight.getFlightclass().getFlightClass());
		}
	}
}
