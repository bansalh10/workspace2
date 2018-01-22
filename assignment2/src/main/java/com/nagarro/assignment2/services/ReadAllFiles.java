package com.nagarro.assignment2.services;

import java.io.File;

import com.nagarro.assignment2.data.Constants;
import com.nagarro.assignment2.flightSystem.Instances;

public class ReadAllFiles {

	Readflightdata readflight;

	public void run() {
		// FlightDataDs.flightsMap.clear();
		readflight = Instances.readflightInstance();
		for (File file : Constants.directory.listFiles()) {

			readflight.setFileData(file);
		}

	}
}
