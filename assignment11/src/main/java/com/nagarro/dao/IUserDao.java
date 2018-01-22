package com.nagarro.dao;

import com.nagarro.model.User;

public interface IUserDao {
	 public boolean verifyUser(String username,String password);
	 public void addUser(User user);
}
