package com.nagarro.assignment3.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.nagarro.assignment3.services.CustomException;

import java.sql.Date;
/**
 * This class provides some functionalities like dateConversion and FlightCreation.
 * @author himanshubansal
 *
 */
public class Util {
	/**
	 * Convert String date into sql date.
	 * @param validTill
	 * @return java.sql.date
	 * @throws CustomException 
	 */
	public static Date convertStringToDate(final String validTill) throws CustomException {
		java.util.Date date = null;
		SimpleDateFormat ft = new SimpleDateFormat(Constants.dateFormat);
		ft.setLenient(false);
		Date flightdate = null;
		try {
			date = ft.parse(validTill);
			flightdate = new Date(date.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new CustomException("Invalid Date");
			//e.printStackTrace();
		}

		return flightdate;
	}
/**
 * Creates Flight object and return it.
 * @param tokens
 * @return Flight
 */
	public static Flight createFlight(final String[] tokens) {
		int flightTime = Integer.parseInt(tokens[4]);
		double flightDuration = Double.parseDouble(tokens[5]);
		int fare = Integer.parseInt(tokens[6]);
		Flight flight = null;
		Date validTill = null;
		try {
			validTill = convertStringToDate(tokens[3]);
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		flight = new Flight(tokens[0], tokens[1], tokens[2], validTill, flightTime, flightDuration, fare, tokens[7]);
		return flight;
	}
}