package com.nagarro.model;

import java.sql.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

/**
 * POJO class of Flight.
 * 
 * @author himanshubansal
 *
 */
@Component
public class Flight {
	private int id;

	private String flightNumber, seatAvailability;
	@NotEmpty
	@NotNull
	@NotBlank
	private String depLoc;
	@NotEmpty
	@NotNull
	@NotBlank
	private String arrLoc;
	@NotNull
	Date validTill;
	private double flightDuration;
	private int flightTime, fare;
	private FlightClass flightclass;

	public Flight() {

	};

	public Flight(String flightNumber, String depLoc, String arrLoc, Date validTill, int flightTime,
			double flightDuration, int fare, String seatAvailability) {
		this.flightNumber = flightNumber;
		this.depLoc = depLoc;
		this.arrLoc = arrLoc;
		this.validTill = validTill;
		this.flightTime = flightTime;
		this.flightDuration = flightDuration;
		this.fare = fare;
		this.seatAvailability = seatAvailability;
	}

	public int getFare() {
		return fare;
	}

	public double getFlightDuration() {
		return flightDuration;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getDepLoc() {
		return depLoc;
	}

	public void setDepLoc(String depLoc) {
		this.depLoc = depLoc;
	}

	public String getArrLoc() {
		return arrLoc;
	}

	public void setArrLoc(String arrLoc) {
		this.arrLoc = arrLoc;
	}

	public String getSeatAvailability() {
		return seatAvailability;
	}

	public void setSeatAvailability(String seatAvailability) {
		this.seatAvailability = seatAvailability;
	}

	public Date getValidTill() {
		return validTill;
	}

	public void setValidTill(Date validTill) {
		this.validTill = validTill;
	}

	public int getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(int flightTime) {
		this.flightTime = flightTime;
	}

	public void setFlightDuration(double flightDuration) {
		this.flightDuration = flightDuration;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}

	public FlightClass getFlightclass() {
		return flightclass;
	}

	public void setFlightclass(FlightClass flightclass) {
		this.flightclass = flightclass;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
