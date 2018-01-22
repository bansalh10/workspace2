package com.nagarro.model;

import javax.validation.constraints.NotNull;

public class FlightDetails {
	private Flight flight;
	private FlightClass flightClass;
	@NotNull
	private String outputPreference;

	public FlightDetails() {
		flight = new Flight();
		flightClass = new FlightClass();
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public FlightClass getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(FlightClass flightClass) {
		this.flightClass = flightClass;
	}

	public String getOutputPreference() {
		return outputPreference;
	}

	public void setOutputPreference(String outputPreference) {
		this.outputPreference = outputPreference;
	}
}
