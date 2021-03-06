package com.nagarro.assignment3.data;
/**
 * Pojo class of FlightClass.
 * @author himanshubansal
 *
 */
public class FlightClass {
	private int id;
	private Flight flight;
	private String flightClass;

	public FlightClass() {

	}

	public FlightClass(String flightClass) {
		this.flightClass = flightClass;
	}

	public String getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

}
