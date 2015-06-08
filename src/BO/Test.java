package BO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DB.dbOperations;
public class Test {

	public static Connection getConnection() throws Exception {
		String driver = "org.gjt.mm.mysql.Driver";
		String url = "jdbc:mysql://localhost:3306/moviedb";
		String username = "root";
		String password = "Wednesday@1";

		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}
	public static void main(String[] args) throws Exception {
		String id = "001";
		String fileName = "C:/Users/suhas/Desktop/Course Work/Q3/WebApps/Project 3/dblp-data-big/dblp_document.txt";

		FileInputStream fis = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			File file = new File(fileName);
			fis = new FileInputStream(file);
			pstmt = conn.prepareStatement("LOAD DATA LOCAL INFILE ? INTO TABLE tbl_dblp_document " +
					"COLUMNS (title, start_page, end_page, year, volume, number, url, ee, cdrom, cite, crossref, isbn, series, editor_id, booktitle_id, publisher_id) " +
					"TERMINATED BY ';' LINES TERMINATED BY '\n';");
			pstmt.setString(1, fileName);
//			pstmt.setString(2, "genre");
//			pstmt.setInt(3, 2011);
//			pstmt.setString(4, "url");
			pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			pstmt.close();
			fis.close();
			conn.close();
		}
	}
}