package com.nagarro.assignment10.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.nagarro.assignment10.data.Constants;

public class Util {
	private static Util util = null;

	private Connection conn = null;

	public static Util getUtil() {
		if (util == null) {
			util = new Util();
		}
		return util;
	}

	public Connection getConnection() throws SQLException {
		if (conn == null || conn.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(Constants.url, Constants.username, Constants.password);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
	}
}
