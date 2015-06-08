package DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import DB.DBUtil;
import BO.*;

public class reportMethods<E> {
	
	//Handles all queries for movies;
	public List<Movies> getMoviesBySearchQuery(String query)
			throws SQLException {
		Connection conObj = new DBUtil().getCon();
		Statement st = conObj.createStatement();
		ResultSet rs = st.executeQuery(query);
		List<Movies> movieList = new ArrayList<Movies>();
		while (rs.next()) {
			movieList.add(new Movies(rs.getInt(1), rs.getString(2), rs
					.getInt(3), rs.getString(4), rs.getString(5), rs
					.getString(6)));
		}
		st.close();
		rs.close();
		conObj.close();
		return movieList;
	}
	
	@SuppressWarnings("unchecked")
	public void generateMovieReports() throws Exception
	{
		String title = "Errors in Movies Table";
		HTMLWriter<E> htmlWrite = new HTMLWriter<E>();
		String fileName = "Movies.html";
		
		//movies without stars
		String purpose1 = "List of movies without stars";
		String query1 = "select * From movies where movies.id NOT IN (Select movie_id from stars_in_movies);";
		List<Movies> movieList1 = this.getMoviesBySearchQuery(query1);
		htmlWrite.writeIntoFile(fileName, (ArrayList<E>) movieList1, purpose1, 4, false);
		
		//Movies without genres
		String purpose2 = "List of movies without genres";
		String query2 = "select * from movies where movies.id NOT IN (Select movie_id from genres_in_movies);";
		List<Movies> movieList2 = this.getMoviesBySearchQuery(query2);
		htmlWrite.writeIntoFile(fileName, (ArrayList<E>) movieList2, purpose2, 4, true);
		
		//Duplicate Movies
		String purpose3 = "List of duplicate movies";
		String query3 = "select distinct m.* FROM movies as m, movies as n where m.title = n.title COLLATE  utf8_general_ci AND m.year = n.year AND m.id != n.id GROUP BY m.title, m.id;";
		List<Movies> movieList3 = this.getMoviesBySearchQuery(query3);
		htmlWrite.writeIntoFile(fileName, (ArrayList<E>) movieList3, purpose3, 4, true);
	}
	
	//handle stars related queries.
	public List<Star> getStarsBySearchQuery(String query)
			throws SQLException {
		Connection conObj = new DBUtil().getCon();
		Statement st = conObj.createStatement();
		ResultSet rs = st.executeQuery(query);
		List<Star> starList = new ArrayList<Star>();
		while (rs.next()) {
			starList.add(new Star(rs.getInt(1), rs.getString(2), rs
					.getString(3), rs.getString(4), rs.getString(5)));
		}
		st.close();
		rs.close();
		conObj.close();
		return starList;
	}
	
	@SuppressWarnings("unchecked")
	public void generateStarsReports() throws Exception
	{
		String title = "Errors in Stars Table";
		HTMLWriter<E> htmlWrite = new HTMLWriter<E>();
		String fileName = "Stars.html";
		
		//stars without movies
		String purpose1 = "List of stars without movies";
		String query1 = "Select distinct * from stars where stars.id NOT IN (Select star_id from stars_in_movies);";
		List<Star> starList1 = this.getStarsBySearchQuery(query1);
		htmlWrite.writeIntoFile(fileName, (ArrayList<E>) starList1, purpose1, 4, false);
		
		//Stars with no first name OR last name
		String purpose2 = "Stars with no first name OR last name.";
		String query2 = "select * from stars where first_name = '' OR last_name = '';";
		List<Star> starList2 = this.getStarsBySearchQuery(query2);
		htmlWrite.writeIntoFile(fileName, (ArrayList<E>) starList2, purpose2, 4, true);
		
		//Duplicate Stars
		String purpose3 = "List of duplicate Stars";
		String query3 = "select distinct s.* from stars as s, stars as t where s.first_name = t.first_name COLLATE utf8_general_ci AND s.last_name = t.last_name COLLATE utf8_general_ci AND s.id != t.id GROUP by s.first_name, s.id;";
		List<Star> starList3 = this.getStarsBySearchQuery(query3);
		htmlWrite.writeIntoFile(fileName, (ArrayList<E>) starList3, purpose3, 4, true);
		
		//List of Stars with DOB > Today OR Year< 1900
		String purpose4 = "List of Stars with DOB > Today OR Year< 1900";
		String query4 = "Select * from stars where dob > NOW() OR YEAR(dob) < 1900;";
		List<Star> starList4 = this.getStarsBySearchQuery(query4);
		htmlWrite.writeIntoFile(fileName, (ArrayList<E>) starList4, purpose4, 4, true);
	}
	
	//handle genre related queries
	public List<genre> getGenresBySearchQuery(String query)
			throws SQLException {
		Connection conObj = new DBUtil().getCon();
		Statement st = conObj.createStatement();
		ResultSet rs = st.executeQuery(query);
		List<genre> genreList = new ArrayList<genre>();
		while (rs.next()) {
			genreList.add(new genre(rs.getInt(1), rs.getString(2)));
		}
		st.close();
		rs.close();
		conObj.close();
		return genreList;
	}
	
	@SuppressWarnings("unchecked")
	public void generateGenreReports() throws Exception
	{
		String title = "Errors in Genres Table";
		HTMLWriter<E> htmlWrite = new HTMLWriter<E>();
		String fileName = "Genres.html";
		
		//Genres wihout any movie
		String purpose1 = "Genres wihout any movie";
		String query1 = "Select distinct id, name from genres where genres.id NOT IN (Select genre_id from genres_in_movies);";
		List<genre> genereList1 = this.getGenresBySearchQuery(query1);
		htmlWrite.writeIntoFile(fileName, (ArrayList<E>) genereList1, purpose1, 2, false);
		
		//Duplicate genres
		String purpose2 = "Duplicate genres";
		String query2 = "select distinct g.id, g.name from genres as g, genres as h where g.id != h.id AND g.name = h.name COLLATE utf8_general_ci ORDER BY g.name;";
		List<genre> genreList2 = this.getGenresBySearchQuery(query2);
		htmlWrite.writeIntoFile(fileName, (ArrayList<E>) genreList2, purpose2, 2, true);
	}
	
	//handle customers related queries
	public List<customer> getcustomerBySearchQuery(String query)
			throws SQLException {
		Connection conObj = new DBUtil().getCon();
		Statement st = conObj.createStatement();
		ResultSet rs = st.executeQuery(query);
		List<customer> customerList = new ArrayList<customer>();
		while (rs.next()) {
			customerList.add(new customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
		}
		st.close();
		rs.close();
		conObj.close();
		return customerList;
	}
	
	@SuppressWarnings("unchecked")
	public void generateCustomerReports() throws Exception
	{
		String title = "Errors in Customer Table";
		HTMLWriter<E> htmlWrite = new HTMLWriter<E>();
		String fileName = "Customers.html";
		
		//Customers email without @ sign
		String purpose1 = "Customers email without @ sign";
		String query1 = "select * from customers where email NOT LIKE '%@%';";
		List<customer> custList1 = this.getcustomerBySearchQuery(query1);
		htmlWrite.writeIntoFile(fileName, (ArrayList<E>) custList1, purpose1, 5, false);
		
	}
	
	//handle creditcards related queries
	public List<creditcards> getCreditcardsBySearchQuery(String query)
			throws SQLException {
		Connection conObj = new DBUtil().getCon();
		Statement st = conObj.createStatement();
		ResultSet rs = st.executeQuery(query);
		List<creditcards> creditcardsList = new ArrayList<creditcards>();
		while (rs.next()) {
			creditcardsList.add(new creditcards(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
		}
		st.close();
		rs.close();
		conObj.close();
		return creditcardsList;
	}

	@SuppressWarnings("unchecked")
	public void generateCreditCardsReports() throws Exception
	{
		String title = "Errors in Creditcards Table";
		HTMLWriter<E> htmlWrite = new HTMLWriter<E>();
		String fileName = "creditCards.html";
		
		//Expired customer credit card. Report expired credit cards only if they belong to existing customers.
		String purpose1 = "Expired customer credit card";
		String query1 = "select distinct cd.id, cd.first_name, cd.last_name, cd.expiration from creditcards as cd join customers on cd.id = customers.cc_id where cd.expiration < NOW();";
		List<creditcards> custList1 = this.getCreditcardsBySearchQuery(query1);
		htmlWrite.writeIntoFile(fileName, (ArrayList<E>) custList1, purpose1, 4, false);
		
	}
	
	//Handle Sales related queries..
	public List<sales> getSalesBySearchQuery(String query)
			throws SQLException {
		Connection conObj = new DBUtil().getCon();
		Statement st = conObj.createStatement();
		ResultSet rs = st.executeQuery(query);
		List<sales> salesList = new ArrayList<sales>();
		while (rs.next()) {
			salesList.add(new sales(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt	(4)));
		}
		st.close();
		rs.close();
		conObj.close();
		return salesList;
	}
	
	@SuppressWarnings("unchecked")
	public void generateSalesReports() throws Exception
	{
		String title = "Errors in Sales Table";
		HTMLWriter<E> htmlWrite = new HTMLWriter<E>();
		String fileName = "Customers.html";
		
		//Sales with future date
		String purpose1 = "Sales with future date";
		String query1 = "select * from sales where sale_date > NOW();";
		List<sales> saleList1 = this.getSalesBySearchQuery(query1);
		htmlWrite.writeIntoFile(fileName, (ArrayList<E>) saleList1, purpose1, 4, false);
		
	}
	
	@SuppressWarnings("static-access")
	public String generateReport()
	{
		//Generate all reports.
		try {
			
			HTMLWriter<E> html = new HTMLWriter<E>();
			
			DateFormat dateFormat = new SimpleDateFormat("MM_dd_yyyy HH.mm.ss");
			Calendar cal = Calendar.getInstance();
			String dateForm = dateFormat.format(cal.getTime());
			String folderName = "Reports_" + dateForm;
			
			html.setFolderName(folderName);
			this.generateMovieReports();
			this.generateCreditCardsReports();
			this.generateCustomerReports();
			this.generateGenreReports();
			this.generateSalesReports();
			this.generateStarsReports();
			
			String reportPath = html.getreportPath();
			System.out.println("Written into all files at " + reportPath);
			return reportPath;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
