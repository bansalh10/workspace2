package com.nagarro.assignment10.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	private static UserDao userDao = null;

	public static UserDao getUserDao() {
		if (userDao == null) {
			userDao = new UserDao();
		}
		return userDao;
	}

	public String getvalidUsername(String userId, String password) {
		String username = null;
		String query = "select * from users where USERID=? and PASSWORD=?";
		try (Connection conn = Util.getUtil().getConnection(); PreparedStatement psmt = conn.prepareStatement(query)) {
			psmt.setString(1, userId);
			psmt.setString(2, password);
			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				username = rs.getString("NAME");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return username;
	}
}
