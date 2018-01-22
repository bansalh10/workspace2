package com.nagarro.assignment2.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHandler {
	public static Date convertStringToDate(String validTill) {
		Date flightdate = null;
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		try {
			flightdate = ft.parse(validTill);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flightdate;
	}

	public static boolean dateComparison(Date a, Date b) {
		boolean status = false;
		if (a.compareTo(b) <= 0) {
			status = true;
		}

		return status;

	}
}