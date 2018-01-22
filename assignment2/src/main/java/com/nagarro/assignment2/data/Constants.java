package com.nagarro.assignment2.data;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Constants {
	static public File directory = new File("./src/main/java/resources");
	static long timerPeriod = 60000;
	static Map<String, ArrayList<Flight>> flightMap = new HashMap<String, ArrayList<Flight>>();

	public static long getTimerPeriod() {
		return timerPeriod;
	}

	public static void setTimerPeriod(long timer) {
		timerPeriod = timer;
	}

	public static Map<String, ArrayList<Flight>> setMap() {
		return flightMap;
	}
}
