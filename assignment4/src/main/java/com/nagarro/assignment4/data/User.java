package com.nagarro.assignment4.data;

public class User {
	private int id;
	private String username;
	private String password;
	private String emailId;
	private int totalsize;

	public User(final String username, final String password, final String emailId) {
		this.username = username;
		this.password = password;
		this.emailId = emailId;
		this.totalsize = 0;
	}

	public User() {

	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(final String emailId) {
		this.emailId = emailId;
	}

	public int getTotalsize() {
		return totalsize;
	}

	public void setTotalsize(final int totalsize) {
		this.totalsize = totalsize;
	}

}
