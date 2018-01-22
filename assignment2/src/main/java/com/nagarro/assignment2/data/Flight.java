package com.nagarro.assignment2.data;

public class Flight {
	private String flightNumber, depLoc, arrLoc, validTill;
	private double flightDuration;
	private int fare;

	public Flight(String flightNumber, String depLoc, String arrLoc, String validTill, int flightTime,
			double flightDuration, int fare, String seatAvailability, String flightClass) {
		this.flightNumber = flightNumber;
		this.depLoc = depLoc;
		this.arrLoc = arrLoc;
		this.validTill = validTill;
		this.flightDuration = flightDuration;
		this.fare = fare;
	}

	public void showFlightData() {
		System.out.println("Fno. " + flightNumber + " deplocation " + depLoc + " Arrival " + arrLoc + " Valid Till "
				+ validTill + " Flight Duration " + flightDuration + " fare " + fare);
	}

	public int getFare() {
		return fare;
	}

	public double getFlightDuration() {
		return flightDuration;
	}

	public String getLastDate() {
		return validTill;
	}
}
