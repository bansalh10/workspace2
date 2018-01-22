package com.nagarro.assignment3.services;

import java.util.List;

import com.nagarro.assignment3.data.Flight;

public interface DataOperations {
	public void storeDatainDb(final Flight flight, final String flightClass);

	public List<Flight> getDataFomDb(final String depLoc, final String arrLoc, final String flightDate,
			final String flightClass, final String outputPreference) throws CustomException;
}
