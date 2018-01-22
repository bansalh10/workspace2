package com.nagarro.assignment3.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.nagarro.assignment3.data.Constants;
import com.nagarro.assignment3.data.Flight;
import com.nagarro.assignment3.data.Util;

public class Readflightdata {
	private static Readflightdata readflight = null;
	Flight flight;

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
				Manager.getDataOperation().storeDatainDb(flight, flightClass);
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
