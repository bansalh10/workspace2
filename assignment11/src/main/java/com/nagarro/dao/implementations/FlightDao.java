package com.nagarro.dao.implementations;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.nagarro.Constants.Constants;
import com.nagarro.dao.IFlightDao;
import com.nagarro.model.Flight;
import com.nagarro.model.FlightClass;
import com.nagarro.service.CustomException;
import com.nagarro.utilities.HibernateUtilities;
/**
 * This class is used to perform Hibernate related operations.
 * @author himanshubansal
 *
 */
@Repository
public class FlightDao implements IFlightDao {
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
	public List<Flight> getDataFomDb(final String depLoc, final String arrLoc, final Date flightDate,
			final String flightClass, final String outputPreference) throws CustomException {
		Session session = HibernateUtilities.getSessionFactory().openSession();
		StringBuilder sbQuery = new StringBuilder(Constants.hibernateflightQuery);
		if (outputPreference.equalsIgnoreCase(Constants.outputPreferenceFD)) {
			sbQuery.append(Constants.hibernateFDsortQuery);
		}
		session.beginTransaction();
		Query query = session.createQuery(sbQuery.toString()).setParameter(Constants.depLoc, depLoc)
				.setParameter(Constants.arrLoc, arrLoc).setParameter(Constants.date, flightDate)
				.setParameter(Constants.fClass, flightClass);
		List<Flight> listofFlights = query.getResultList();
		session.getTransaction().commit();
		session.close();
		return listofFlights;

	}
@Override
public void cleanDb() {
	Session session = HibernateUtilities.getSessionFactory().openSession();
	String query="delete from Flight where 1=1";
	session.beginTransaction();
	 session.createQuery(query).executeUpdate();
	 session.getTransaction().commit();
		session.close();
}
	
}
