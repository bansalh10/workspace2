package com.nagarro.dao;

import java.sql.Date;
import java.util.List;

import com.nagarro.model.Flight;
import com.nagarro.service.CustomException;

public interface IFlightDao {
	public void storeDatainDb(final Flight flight, final String flightClass);

	public List<Flight> getDataFomDb(final String depLoc, final String arrLoc, final Date flightDate,
			final String flightClass, final String outputPreference) throws CustomException;
	public void cleanDb();
}
