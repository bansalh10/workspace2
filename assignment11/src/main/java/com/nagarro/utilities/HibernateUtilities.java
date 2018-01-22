package com.nagarro.utilities;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.nagarro.Constants.Constants;

/**
 * This class provides creates session factory which can be used to connect to
 * db through hibernate.
 * 
 * @author himanshubansal
 *
 */
public class HibernateUtilities {
	private static SessionFactory sessionFactory;
	private static StandardServiceRegistry serviceRegistry;
	static {
		try {
			serviceRegistry = new StandardServiceRegistryBuilder().configure(Constants.configurationFile).build();
			Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
			sessionFactory = metadata.getSessionFactoryBuilder().build();

		} catch (HibernateException e) {
			System.out.println(e);
		}

	}

	/**
	 * 
	 * @return SessionFactory
	 */
	public static SessionFactory getSessionFactory() {

		return sessionFactory;
	}
}
