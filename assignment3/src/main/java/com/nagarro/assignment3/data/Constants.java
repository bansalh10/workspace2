package com.nagarro.assignment3.data;

import java.io.File;

/**
 * This class contains constants which can be accessed using classname.
 * 
 * @author himanshubansal
 *
 */
public class Constants {
	public enum flightClass {
		E, B, EB;
	}

	public enum preference {
		F, FD;
	}

	public static final File directory = new File("./src/main/resources");
	public static final long timerPeriod = 60000;
	public static final String tabSpace = "\t";
	public static final String space = " ";
	public static final String lineSplitter = "\\|";
	public static final String username = "root";
	public static final String password = "root";
	public static final String url = "jdbc:mysql://localhost:3306/flight_data";
	public static final String yes = "y";
	public static final String no = "n";
	public static final String dateFormat = "dd-MM-yyyy";
	public static final String dbChoice = "Enter method to use for database:JDBC,Hibernate(J/H)";
	public static final String flightInfo = "Enter Departure location,Arrival Location,Flight Date(dd-mm-yyyy),Flight class(E,B,EB),Output Preference(F/FD)";
	public static final String continueMsg = "If you want to continue press any key except (n)";
	public static final String invalid = "Incorrect Operation";
	public static final String invalidInput = "Invalid Input";
	public static final String hibernateMethod = "h";
	public static final String jdbcMethod = "j";
	public static final String noFlights = "No flights available";
	public static final String configurationFile = "hibernate.cfg.xml";
	public static final String fNumber = "FLIGHTNUMBER";
	public static final String arrLoc = "ARRLOC";
	public static final String depLoc = "DEPLOC";
	public static final String validTill = "VALIDTILL";
	public static final String date = "DATE";
	public static final String fare = "FARE";
	public static final String fTime = "FLIGHTTIME";
	public static final String fClass = "FLIGHTCLASS";
	public static final String fDuration = "FLIGHTDURATION";
	public static final String seatavailablity = "SEATAVAILABILITY";
	public static final String hibernatefClassQuery = "select flightclass from FlightClass as flightclass where flightclass.flightClass=:FLIGHTCLASS";
	public static final String hibernateflightQuery = "select flight from Flight as flight where flight.depLoc=:DEPLOC and flight.arrLoc=:ARRLOC and flight.validTill>=:DATE and (flight.flightclass.flightClass='EB' or flight.flightclass.flightClass=:FLIGHTCLASS) order by flight.fare";
	public static final String jdbcgetfClassQuery = "select FLIGHTCLASS from flightclass as flightclass where flightclass.FLIGHTCLASS=?";
	public static final String jdbcinsertfClassQuery = "insert into flightclass(FLIGHTCLASS) values(?)";
	public static final String jdbcgetFlightIdQuery = "select ID from flightclass as flightclass where flightclass.FLIGHTCLASS=?";
	public static final String jdbcinsertFlightQuery = "insert into flights(FLIGHTNUMBER,DEPLOC,ARRLOC,SEATAVAILABILITY,VALIDTILL,FLIGHTDURATION,FLIGHTTIME,FARE,FLIGHTCLASS) values(?,?,?,?,?,?,?,?,?) ";
	public static final String jdbcGetFlightQuery = "select * from flights as flight,flightclass as flightclass where flight.FLIGHTCLASS=flightclass.ID and flight.DEPLOC=? and flight.ARRLOC=? and flight.VALIDTILL>=? and (flightclass.FLIGHTCLASS='EB' or flightclass.FLIGHTCLASS=?) order by flight.FARE";
	public static final String outputPreferenceFD = "fd";
	public static final String jdbcFDsortQuery = ",flight.FLIGHTDURATION";
	public static final String hibernateFDsortQuery = ",flight.flightDuration";
}
