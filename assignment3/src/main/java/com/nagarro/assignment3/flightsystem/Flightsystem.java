package com.nagarro.assignment3.flightsystem;

import java.util.List;

import com.nagarro.assignment3.data.Constants;
import com.nagarro.assignment3.data.Flight;
import com.nagarro.assignment3.services.CheckNewFile;
import com.nagarro.assignment3.services.CustomException;
import com.nagarro.assignment3.services.Manager;
import com.nagarro.assignment3.services.ReadAllFiles;

public class Flightsystem {
	public static void main(String[] args) {
		List<Flight> flights = null;
		String status = Constants.yes;
		String databaseConnection;
		Output.getOutput().showMsg(Constants.dbChoice);
		databaseConnection = Input.getInput().enterDbConnectivity();
		Manager.setDataOperation(databaseConnection);
		if (Manager.getDataOperation() != null) {
			ReadAllFiles.getReadallfiles().run();
			CheckNewFile.getCheckfile().loadNewfile();
			while (!status.equalsIgnoreCase(Constants.no)) {
				Output.getOutput().showMsg(Constants.flightInfo);
				try {
					flights = Input.getInput().getPreferredFlights();
				} catch (CustomException e) {
					// TODO Auto-generated catch block
					e.display();
				}
				if (flights != null) {
					if (!flights.isEmpty()) {
						Output.getOutput().showFlights(flights);
					} else {
						Output.getOutput().showMsg(Constants.noFlights);
					}
				} 

				Output.getOutput().showMsg(Constants.continueMsg);
				status = Input.getInput().enterStatus();
			}
			Input.getInput().closeScanner();
			CheckNewFile.getCheckfile().stopTimerThread();
			if (databaseConnection.equalsIgnoreCase(Constants.hibernateMethod)) {
				HibernateUtilities.getSessionFactory().close();
			}
		} else {
			Output.getOutput().showMsg(Constants.invalid);
		}

	}

}
