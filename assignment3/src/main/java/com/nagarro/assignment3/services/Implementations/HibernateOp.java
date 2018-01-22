package com.nagarro.assignment3.services.Implementations;

import java.sql.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.nagarro.assignment3.data.Util;
import com.nagarro.assignment3.data.Constants;
import com.nagarro.assignment3.data.Flight;
import com.nagarro.assignment3.data.FlightClass;
import com.nagarro.assignment3.flightsystem.HibernateUtilities;
import com.nagarro.assignment3.services.CustomException;
import com.nagarro.assignment3.services.DataOperations;
/**
 * This class is used to perform Hibernate related operations.
 * @author himanshubansal
 *
 */
public class HibernateOp implements DataOperations {
/**
 * store the flight in database .
 */
	public void storeDatainDb(final Flight flight, final String flightClass) {
		List<FlightClass> listofFlightClass;
		Session session = HibernateUtilities.getSessionFactory().openSession();
		session.beginTransaction();
		listofFlightClass = session.createQuery(Constants.hibernatefClassQuery)
				.setParameter(Constants.fClass, flightClass).getResultList();
		if (listofFlightClass.isEmpty()) {
			flight.setFlightclass(new FlightClass(flightClass));
		} else {
			flight.setFlightclass(listofFlightClass.get(0));
		}
		session.save(flight);
		session.getTransaction().commit();
		session.close();
	}
/**
 * get flights from database and return them in a list.
 * @throws CustomException 
 */
	public List<Flight> getDataFomDb(final String depLoc, final String arrLoc, final String flightDate,
			final String flightClass, final String outputPreference) throws CustomException {
		Session session = HibernateUtilities.getSessionFactory().openSession();
		Date date = Util.convertStringToDate(flightDate);
		StringBuilder sbQuery = new StringBuilder(Constants.hibernateflightQuery);
		if (outputPreference.equalsIgnoreCase(Constants.outputPreferenceFD)) {
			sbQuery.append(Constants.hibernateFDsortQuery);
		}
		session.beginTransaction();
		Query query = session.createQuery(sbQuery.toString()).setParameter(Constants.depLoc, depLoc)
				.setParameter(Constants.arrLoc, arrLoc).setParameter(Constants.date, date)
				.setParameter(Constants.fClass, flightClass);
		List<Flight> listofFlights = query.getResultList();
		session.getTransaction().commit();
		session.close();
		return listofFlights;

	}
}
