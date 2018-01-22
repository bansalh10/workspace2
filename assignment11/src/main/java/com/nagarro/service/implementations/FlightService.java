package com.nagarro.service.implementations;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.dao.IFlightDao;
import com.nagarro.model.Flight;
import com.nagarro.service.CustomException;
import com.nagarro.service.IFlightService;

@Service
public class FlightService implements IFlightService {
	@Autowired
	private IFlightDao flightDao;

	public void storeDatainDb(final Flight flight, final String flightClass) {
		flightDao.storeDatainDb(flight, flightClass);
	};

	public List<Flight> getDataFomDb(final String depLoc, final String arrLoc, final Date flightDate,
			final String flightClass, final String outputPreference) {
		List<Flight> list = null;
		try {
			list = flightDao.getDataFomDb(depLoc, arrLoc, flightDate, flightClass, outputPreference);
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void cleanDb() {
		flightDao.cleanDb();

	}
}
