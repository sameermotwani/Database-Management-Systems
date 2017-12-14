package com.db;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManage {
	
	

	private static Connection connection;

	private DatabaseManage() {
		// does nothing
	}

	public static Connection getConnection() throws SQLException,
			ClassNotFoundException {
		if (connection == null) {

			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/test", "root", "root@123");

		}
		return connection;

	}

	public static void closeConnection() throws SQLException {
		if (connection != null) {
			connection.close();
			connection = null;
		}
	}
}
