package com.shashi.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtil {
	private static Connection conn;

	public DBUtil() {
	}

	public static Connection provideConnection() {

		try {
			if (conn == null || conn.isClosed()) {
				ResourceBundle rb = ResourceBundle.getBundle("application");
				String connectionString = rb.getString("db.connectionString");
				String driverName = rb.getString("db.driverName");
				String username = rb.getString("db.username");
				String password = rb.getString("db.password");

				System.out.println("DB Connection Properties:");
				System.out.println("Connection String: " + connectionString);
				System.out.println("Driver Name: " + driverName);
				System.out.println("Username: " + username);
				// Do not print password for security reasons

				try {
					Class.forName(driverName);
				} catch (ClassNotFoundException e) {
					System.err.println("JDBC Driver class not found: " + e.getMessage());
					e.printStackTrace();
					throw new RuntimeException(e);
				}
				conn = DriverManager.getConnection(connectionString, username, password);

				System.out.println("Database connection established successfully.");
			}
		} catch (SQLException e) {
			System.err.println("Failed to create database connection: " + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return conn;
	}

	public static void closeConnection(Connection con) {
		/*
		 * try { if (con != null && !con.isClosed()) {
		 * 
		 * con.close(); } } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
	}

	public static void closeConnection(ResultSet rs) {
		try {
			if (rs != null && !rs.isClosed()) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void closeConnection(PreparedStatement ps) {
		try {
			if (ps != null && !ps.isClosed()) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
