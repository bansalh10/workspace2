package com.nagarro.service;

import com.nagarro.model.User;

public interface IUserService {
	public boolean verifyUser(String username, String password);
	public void addUser(User user);
}
