package com.nagarro.service.implementations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.Constants.Constants;
import com.nagarro.model.Flight;
import com.nagarro.service.IFlightService;
import com.nagarro.service.IReadFlightData;
import com.nagarro.utilities.Util;

@Service
public class Readflightdata implements IReadFlightData {
	private static Readflightdata readflight = null;
	Flight flight;
	@Autowired
	IFlightService flightService;

	public static Readflightdata getReadflight() {
		if (readflight == null) {
			readflight = new Readflightdata();
		}
		return readflight;
	}

	public void getFileData(File fileLoc) {
		String line;
		String[] tokens;
		String flightClass;
		try (FileReader reader = new FileReader(fileLoc); BufferedReader bufferreader = new BufferedReader(reader)) {

			line = bufferreader.readLine();

			while ((line = bufferreader.readLine()) != null) {
				tokens = line.split(Constants.lineSplitter);
				flight = Util.createFlight(tokens);
				flightClass = tokens[8];
				System.out.println("manish:" + flightService);
				flightService.storeDatainDb(flight, flightClass);
			}

		} catch (FileNotFoundException e) {
			System.out.println("hi");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
