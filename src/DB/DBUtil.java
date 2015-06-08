package DB;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import java.sql.*;
import javax.naming.InitialContext;
import javax.naming.Context;

public class DBUtil {

	/*	public Connection getConnectionObj() {
		// JDBC driver name and database URL
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost:3306/moviedb";

		// Database credentials
		final String USER = "root";
		final String PASS = "user";
		Connection connect = null;

		try {
			Class.forName(JDBC_DRIVER).newInstance();
			// System.out.println("Connecting to database...");
			connect = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connect;
	}*/

	public Connection getConnectionObj() {
		/*	String loginUser = "user";
		String loginPasswd = "root";
		String loginUrl = "jdbc:mysql://localhost:3306/moviedb";*/
		// Output stream to STDOUT
		Connection dbcon = null;
		try {
			// the following few lines are for connection pooling
			// Obtain our environment naming context

			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			if (envCtx == null)
				System.out.println("envCtx is null");

			// Look up our data source
			DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");
			if (ds == null)
				System.out.println("Data Source is null");

			dbcon = ds.getConnection();
			/*	if (dbcon != null)
				System.out.println("Got the object! ********* ");*/


		} catch (Exception E) {
			E.printStackTrace();
		}
		return dbcon;
	}

	
}
