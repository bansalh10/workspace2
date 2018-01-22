package com.nagarro.assignment3.services;

import com.nagarro.assignment3.data.Constants;
import com.nagarro.assignment3.services.Implementations.HibernateOp;
import com.nagarro.assignment3.services.Implementations.JDBCop;

public class Manager {
	private static DataOperations operation = null;

	static public void setDataOperation(final String databaseConnection) {

		if (databaseConnection.equalsIgnoreCase(Constants.jdbcMethod)) {
			operation = new JDBCop();
		}
		if (databaseConnection.equalsIgnoreCase(Constants.hibernateMethod)) {
			operation = new HibernateOp();
		}
	}

	static public DataOperations getDataOperation() {
		return operation;

	}
}
