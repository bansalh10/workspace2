package com.nagarro.assignment2.data;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author himanshubansal
 *
 */
public class FlightDataDs {
	// Map<String, ArrayList<Flight>> flightsMap = Constants.setMap();
	ArrayList<Flight> listOfFlights;
	Flight flight;
	ArrayList<String> keys;

	public synchronized void saveDataInMap(String[] tokens) {
		keys = new ArrayList<String>();
		keys = keyCreater(tokens[1], tokens[2], tokens[8]);
		flight = createFlight(tokens);
		for (String key : keys) {
			addFlightInMap(key);
		}
	}

	/**
	 * @param key
	 */
	private void addFlightInMap(String key) {
		if (Constants.setMap().containsKey(key)) {
			listOfFlights = Constants.setMap().get(key);
		} else {
			listOfFlights = new ArrayList<Flight>();
		}
		listOfFlights.add(flight);
		Constants.setMap().put(key, listOfFlights);
	}

	public Flight createFlight(String[] tokens) {
		int flightTime = Integer.parseInt(tokens[4]);
		double flightDuration = Double.parseDouble(tokens[5]);
		int fare = Integer.parseInt(tokens[6]);
		Flight flight = new Flight(tokens[0], tokens[1], tokens[2], tokens[3], flightTime, flightDuration, fare,
				tokens[7], tokens[8]);
		return flight;
	}

	public ArrayList<String> keyCreater(String depLoc, String arrLoc, String flightClass) {
		ArrayList<String> keys = new ArrayList<String>();
		String miniKey = depLoc + arrLoc;
		if (flightClass.equalsIgnoreCase("EB")) {
			keys.add(miniKey + "E");
			keys.add(miniKey + "B");
		} else {
			keys.add(miniKey + flightClass);
		}
		return keys;
	}

	public static void getFlights(String depLoc, String arrLoc, String flightDate, String flightClass,
			String outputPreference) {
		String key = depLoc + arrLoc + flightClass;
		if (Constants.setMap().containsKey(key)) {
			ArrayList<Flight> flights = new ArrayList<Flight>();
			flights = Constants.setMap().get(key);
			Collections.sort(flights, FlightComparator.sortBasedOnFare);
			if (outputPreference.equalsIgnoreCase("fd")) {
				Collections.sort(flights, FlightComparator.sortBasedOnFlightDuration);
			}
			for (Flight flight : flights) {
				if (DateHandler.dateComparison(DateHandler.convertStringToDate(flightDate),
						DateHandler.convertStringToDate(flight.getLastDate()))) {
					flight.showFlightData();
				}
			}
		}
	}
}
