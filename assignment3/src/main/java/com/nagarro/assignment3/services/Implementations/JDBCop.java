package com.nagarro.assignment3.services.Implementations;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nagarro.assignment3.data.Constants;
import com.nagarro.assignment3.data.Flight;
import com.nagarro.assignment3.data.FlightClass;
import com.nagarro.assignment3.data.Util;
import com.nagarro.assignment3.services.CustomException;
import com.nagarro.assignment3.services.DataOperations;
/**
 * This class is used to perform JDBC related operations.
 * @author himanshubansal
 *
 */
public class JDBCop implements DataOperations {
	/**
	 * store the flight in database .
	 */
	public void storeDatainDb(final Flight flight, final String flightClass) {
		int flightClassId;
		try (Connection connection = DriverManager.getConnection(Constants.url, Constants.username,
				Constants.password);) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			pstmt = connection.prepareStatement(Constants.jdbcgetfClassQuery);
			pstmt.setString(1, flightClass);
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				pstmt = connection.prepareStatement(Constants.jdbcinsertfClassQuery);
				pstmt.setString(1, flightClass);
				pstmt.executeUpdate();
			}
			pstmt = connection.prepareStatement(Constants.jdbcgetFlightIdQuery);
			pstmt.setString(1, flightClass);
			rs = pstmt.executeQuery();
			rs.next();
			flightClassId = rs.getInt(1);
			pstmt = connection.prepareStatement(Constants.jdbcinsertFlightQuery);
			pstmt.setString(1, flight.getFlightNumber());
			pstmt.setString(2, flight.getDepLoc());
			pstmt.setString(3, flight.getArrLoc());
			pstmt.setString(4, flight.getSeatAvailability());
			pstmt.setDate(5, flight.getValidTill());
			pstmt.setDouble(6, flight.getFlightDuration());
			pstmt.setInt(7, flight.getFlightTime());
			pstmt.setInt(8, flight.getFare());
			pstmt.setInt(9, flightClassId);

			pstmt.executeUpdate();
			pstmt.close();
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * get flights from database and return them in a list.
	 * @throws CustomException 
	 */
	public List<Flight> getDataFomDb(final String depLoc, final String arrLoc, final String flightDate,
			final String flightClass, final String outputPreference) throws CustomException {
		Date date = Util.convertStringToDate(flightDate);
		StringBuilder sbQuery = new StringBuilder(Constants.jdbcGetFlightQuery);
		if (outputPreference.equalsIgnoreCase(Constants.outputPreferenceFD)) {
			sbQuery.append(Constants.jdbcFDsortQuery);
		}
		List<Flight> listofFlights = null;
		try (Connection connection = DriverManager.getConnection(Constants.url, Constants.username, Constants.password);
				PreparedStatement pstmt = connection.prepareStatement(sbQuery.toString());

		) {
			pstmt.setString(1, depLoc);
			pstmt.setString(2, arrLoc);
			pstmt.setDate(3, date);
			pstmt.setString(4, flightClass);
			ResultSet rs = pstmt.executeQuery();
			listofFlights = new ArrayList<>();
			while (rs.next()) {
				Flight flight = new Flight(rs.getString(Constants.fNumber), rs.getString(Constants.depLoc),
						rs.getString(Constants.arrLoc), rs.getDate(Constants.validTill), rs.getInt(Constants.fTime),
						rs.getDouble(Constants.fDuration), rs.getInt(Constants.fare),
						rs.getString(Constants.seatavailablity));
				FlightClass flightclass = new FlightClass(rs.getString(12));
				flight.setFlightclass(flightclass);
				listofFlights.add(flight);
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listofFlights;

	}

}
