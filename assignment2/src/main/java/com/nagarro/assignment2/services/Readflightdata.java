package com.nagarro.assignment2.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.nagarro.assignment2.data.FlightDataDs;
import com.nagarro.assignment2.flightSystem.Instances;

public class Readflightdata {

	FlightDataDs DataMap ;

	public void setFileData(File fileLoc) {
		String line;
		String[] tokens;
		FileReader reader = null;
		BufferedReader bufferreader = null;
		try {
			reader = new FileReader(fileLoc);
			bufferreader = new BufferedReader(reader);
			line = bufferreader.readLine();
			while ((line = bufferreader.readLine()) != null) {
				tokens = line.split("\\|");
				DataMap= Instances.dataMapInstance();
				DataMap.saveDataInMap(tokens);
			}

		} catch (FileNotFoundException e) {
			System.out.println("hi");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (bufferreader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
