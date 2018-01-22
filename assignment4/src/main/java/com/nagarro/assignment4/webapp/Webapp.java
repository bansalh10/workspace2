package com.nagarro.assignment4.webapp;

import org.hibernate.Session;

import com.nagarro.assignment4.data.User;
import com.nagarro.assignment4.services.HibernateUtilities;

public class Webapp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Session session=HibernateUtilities.getSessionfactory().openSession();
        User user=new User();
        session.beginTransaction();
        user.setUsername("Himanshu");
        user.setPassword("Himanshu");
        user.setEmailId("bansalh10@gmail.com");
        session.save(user);
        session.getTransaction().commit();
        session.close();
	}

}
