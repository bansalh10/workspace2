package com.nagarro.service;

import java.sql.Date;
import java.util.List;

import com.nagarro.model.Flight;

public interface IFlightService {
	public void storeDatainDb(final Flight flight, final String flightClass);

	public List<Flight> getDataFomDb(final String depLoc, final String arrLoc, final Date flightDate,
			final String flightClass, final String outputPreference);

	public void cleanDb();
}
