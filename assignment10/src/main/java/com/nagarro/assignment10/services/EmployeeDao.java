package com.nagarro.assignment10.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nagarro.assignment10.data.Product;

public class EmployeeDao {
	private static EmployeeDao operation = null;

	public static EmployeeDao getOperation() {
		if (operation == null) {
			operation = new EmployeeDao();
		}
		return operation;
	}

	public List<Product> getEmployess() {
		List<Product> employees = null;
		Product employee;
		String query = "select * from employees";
		try (Connection conn = Util.getUtil().getConnection();
				PreparedStatement psmt = conn.prepareStatement(query);
				ResultSet rs = psmt.executeQuery()) {
			if (rs != null) {
				employees = new ArrayList<>();
			}
			while (rs.next()) {
				employee = new Product(rs.getInt("EMPLOYEECODE"), rs.getString("NAME"), rs.getString("LOCATION"),
						rs.getString("EMAIL"), rs.getDate("DOB"));
				employees.add(employee);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employees;
	}

	public boolean addEmployee(Product employee) {
		boolean added = true;
		String query = "insert into employees values(?,?,?,?,?)";
		try (Connection conn = Util.getUtil().getConnection(); PreparedStatement psmt = conn.prepareStatement(query)) {
			psmt.setInt(1, employee.getEmployeeCode());
			psmt.setString(2, employee.getEmployeeName());
			psmt.setString(3, employee.getLocation());
			psmt.setString(4, employee.getEmail());
			psmt.setDate(5, employee.getDob());
			int status = psmt.executeUpdate();
			if (status == 0) {
				added = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			added = false;
		}
		return added;
	}

	public Product getEmployeeById(String employeeCode) {
		String query = "select * from employees where EMPLOYEECODE=?";
		int eCode = Integer.parseInt(employeeCode);
		Product employee = null;
		try (Connection conn = Util.getUtil().getConnection(); PreparedStatement psmt = conn.prepareStatement(query)) {
			psmt.setInt(1, eCode);
			ResultSet rs = psmt.executeQuery();
			if (rs != null) {
				rs.next();
				employee = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employee;
	}

	public boolean updateEmployee(Product employee) {
		boolean updated = true;
		String query = "update employees set NAME=?,LOCATION=?,EMAIL=?,DOB=? where EMPLOYEECODE=?";
		try (Connection conn = Util.getUtil().getConnection(); PreparedStatement psmt = conn.prepareStatement(query)) {
			psmt.setInt(5, employee.getEmployeeCode());
			psmt.setString(1, employee.getEmployeeName());
			psmt.setString(2, employee.getLocation());
			psmt.setString(3, employee.getEmail());
			psmt.setDate(4, employee.getDob());
			int status = psmt.executeUpdate();
			if (status == 0) {
				updated = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			updated = false;
		}
		return updated;
	}
}
