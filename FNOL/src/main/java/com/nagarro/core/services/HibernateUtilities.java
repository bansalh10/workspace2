package com.nagarro.core.services;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * provides connection to db with help of hibernate.
 * 
 * @author himanshubansal
 *
 */
public class HibernateUtilities {
	private static SessionFactory sessionFactory;
	private static StandardServiceRegistry serviceregistry;
	static {
		try {
			serviceregistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
			Metadata metadata = new MetadataSources(serviceregistry).getMetadataBuilder().build();
			sessionFactory = metadata.getSessionFactoryBuilder().build();
		} catch (HibernateException e) {
			System.out.println(e);
		}
	}

	public static SessionFactory getSessionfactory() {
		return sessionFactory;
	}

}
