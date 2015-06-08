package DB;

import java.sql.*;
import java.util.*;
import BO.Customer;
import BO.Star;

public class DBMethods {

	public static ArrayList<Customer> customer_List() {
		try {
			DBUtil db = new DBUtil();
			Connection con = db.getconnection();
			Statement stmt;
			stmt = con.createStatement();
			// Statements allow to issue SQL queries to the database
			String sql = "SELECT * FROM customers";
			ResultSet rs = stmt.executeQuery(sql);
			ArrayList<Customer> customer_List = new ArrayList<Customer>();
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString("id"));
				String First_name = rs.getString("First_name");
				String Last_name = rs.getString("Last_name");
				String cc_id = rs.getString("cc_id");
				String address = rs.getString("address");
				String email = rs.getString("email");
				String password = rs.getString("password");
				customer_List.add(new Customer(id, First_name, Last_name,
						cc_id, address, email, password));
			}
			DBUtil.closeconnection(con);
			return customer_List;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void DeleteCustomerEntry(int id) {
		DBUtil db = new DBUtil();
		Connection con = db.getconnection();
		Statement stmt;
		try {
			stmt = con.createStatement();
			String sql = "delete from customers where id=" + id + ";";
			stmt.executeUpdate(sql);
			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Statements allow to issue SQL queries to the database

	}

	public static boolean authentication(String id, String password) {
		boolean flag = DBUtil.getconnection(id, password);
		if (flag == true) {
			// DBUtil.closeconnection(con);
			System.out.println("Sucessfull");
			return true;
		} else
			return false;

	}

	public int insertCustomer(Customer cust) {
		java.sql.CallableStatement csmt = null;
		Connection conn = null;
		int row = 0;
		try {
			DBUtil db = new DBUtil();
			conn = db.getconnection();
			String sqlstat = "{call insertCustomer (?, ?, ?, ? ,? ,?)}";
			csmt = conn.prepareCall(sqlstat);
			csmt.setString(1, cust.first_name);
			csmt.setString(2, cust.last_name);
			csmt.setString(3, cust.address);
			csmt.setString(4, cust.email);
			csmt.setString(5, cust.password);
			csmt.registerOutParameter(6, java.sql.Types.INTEGER);
			csmt.executeUpdate();
			row = csmt.getInt(6);
			System.out.println("Cust_ID" + row);
			return row;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				// closes the database connection
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return row;
	}

	public int insertStar(Star sta) {
		int last_inserted_id = 0;
		Connection conn = null;
		try {
			DBUtil db = new DBUtil();
			conn = db.getconnection();
			PreparedStatement psmt = null;
			psmt = conn
					.prepareStatement(
							"insert into stars (first_name, last_name, dob, photo_url) values (?,?,?,?);",
							Statement.RETURN_GENERATED_KEYS);
			psmt.setString(1, sta.first_name);
			psmt.setString(2, sta.last_name);
			psmt.setString(3, sta.dob);
			psmt.setString(4, sta.photo_url);
			psmt.executeUpdate();

			// Statement stat = null;
			// stat = conn.createStatement();
			// ResultSet rs1 = stat.executeQuery("select LAST_INSERT_ID();");
			// int lastId = Integer.parseInt(rs1.getString(1));
			ResultSet rs1 = psmt.getGeneratedKeys();
			if (rs1.next()) {
				last_inserted_id = rs1.getInt(1);
			}
			psmt.close();
			return last_inserted_id;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				// closes the database connection
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return 0;
	}

}
