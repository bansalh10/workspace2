package com.nagarro.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.dao.IUserDao;
import com.nagarro.model.User;
import com.nagarro.service.IUserService;

@Service
public class UserService implements IUserService {
	@Autowired
	private IUserDao userDao;

	public boolean verifyUser(String username, String password) {
		return userDao.verifyUser(username, password);
	}

	@Override
	public void addUser(User user) {
		
		userDao.addUser(user);
	}
}
