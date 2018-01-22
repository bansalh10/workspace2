package com.nagarro.dao.implementations;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.nagarro.dao.IUserDao;
import com.nagarro.model.FlightClass;
import com.nagarro.model.User;
import com.nagarro.utilities.HibernateUtilities;
@Repository
public class UserDao implements IUserDao  {
    
     
	@Override
	public boolean verifyUser(String username, String password) {
		List<FlightClass> validUsers;
		boolean status=true;
		Session session = HibernateUtilities.getSessionFactory().openSession();
		session.beginTransaction();

		validUsers = session.createQuery("select user from User as user where user.username=:username and user.password=:password")
				.setParameter("username", username).setParameter("password", password).getResultList();
		if (validUsers.isEmpty()) {
		status=false;
		}
		session.getTransaction().commit();
		session.close();
		System.out.println("THe status is ...."+status);
		return status;
	}

	@Override
	public void addUser(User user) {
		Session session = HibernateUtilities.getSessionFactory().openSession();
		session.beginTransaction();
        session.save(user);
		session.getTransaction().commit();
		session.close();
		
	}
}
