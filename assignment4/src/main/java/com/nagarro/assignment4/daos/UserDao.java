package com.nagarro.assignment4.daos;

import java.util.List;

import org.hibernate.Session;

import com.nagarro.assignment4.data.Constants;
import com.nagarro.assignment4.data.User;
import com.nagarro.assignment4.services.HibernateUtilities;

/**
 * This class is used for operations related to user table in db.
 * 
 * @author himanshubansal
 *
 */
public class UserDao {
	/**
	 * Return the user whose id is passed as arguement.
	 * 
	 * @param id
	 * @return
	 */
	public User getUser(final int id) {
		Session session = HibernateUtilities.getSessionfactory().openSession();
		session.beginTransaction();
		User user = session.get(User.class, id);
		session.getTransaction().commit();
		session.close();
		return user;
	}

	/**
	 * returns userid if username and password matches in user table.Validates
	 * if it is authenticated user.
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public int validatedUserId(final String username, final String password) {
		int id = -1;
		Session session = HibernateUtilities.getSessionfactory().openSession();
		session.beginTransaction();
		List<User> validuser = session
				.createQuery("select user from User as user where user.username=:username and user.password=:password")
				.setParameter(Constants.Username.getName(), username)
				.setParameter(Constants.Password.getName(), password).getResultList();
		// get id here and pass to next page
		session.getTransaction().commit();
		session.close();
		if (!validuser.isEmpty()) {
			id = validuser.get(0).getId();
		}
		return id;
	}

	/**
	 * adds user entry to the user table if username is unique.returns true if
	 * user is added.
	 * 
	 * @param username
	 * @param password
	 * @param emailid
	 * @return
	 */
	public boolean addUser(final String username, final String password, final String emailid) {
		boolean useradded = false;
		Session session = HibernateUtilities.getSessionfactory().openSession();
		session.beginTransaction();
		List<User> existingUser = session.createQuery("select user from User as user where user.username=:username")
				.setParameter(Constants.Username.getName(), username).getResultList();
		if (existingUser.isEmpty()) {
			User user = new User(username, password, emailid);
			session.save(user);
			useradded = true;
		}

		session.getTransaction().commit();
		session.close();
		return useradded;

	}
}
