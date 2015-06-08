package DB;

import java.sql.*;

public class DBUtil {

	public Connection getconnection() {

		// JDBC driver name and database URL
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost:3306/moviedb";

		// Database credentials
		final String USER = "cs244";
		final String PASS = "saurebh12";
		Connection connect = null;

		try {
			Class.forName(JDBC_DRIVER).newInstance();
			// System.out.println("Connecting to database...");
			connect = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connect;

	}

	public static boolean getconnection(String id, String pwd) {

		String url = "jdbc:mysql://127.0.0.1:3306/moviedb";

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(url, id, pwd);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static void closeconnection(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
