package com.nagarro.assignment2.data;

import java.util.Comparator;

public class FlightComparator {
	static final Comparator<Flight> sortBasedOnFare = new Comparator<Flight>() {
		public int compare(Flight firstFlight, Flight secondFlight) {
			return Integer.compare(firstFlight.getFare(), secondFlight.getFare());
		}
	};
	static final Comparator<Flight> sortBasedOnFlightDuration = new Comparator<Flight>() {
		public int compare(Flight firstFlight, Flight secondFlight) {
			int value = 0;
			if (firstFlight.getFare() == secondFlight.getFare()) {
				value = Double.compare(firstFlight.getFlightDuration(), secondFlight.getFlightDuration());
			}
			return value;
		}
	};
}
