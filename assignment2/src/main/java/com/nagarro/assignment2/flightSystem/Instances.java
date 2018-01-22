package com.nagarro.assignment2.flightSystem;

import com.nagarro.assignment2.data.FlightDataDs;
import com.nagarro.assignment2.services.Readflightdata;

public class Instances {
	static Readflightdata readflight = new Readflightdata();
	static FlightDataDs DataMap = new FlightDataDs();

	static public Readflightdata readflightInstance() {
		return readflight;

	}

	static public FlightDataDs dataMapInstance() {
		return DataMap;

	}
}
