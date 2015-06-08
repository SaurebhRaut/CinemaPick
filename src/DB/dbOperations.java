package DB;
import BO.Movies;
import BO.Star;
import BO.cart;
import BO.customer;
import BO.genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class dbOperations {

	// Getting list of all movies by title, year , director
	public List<Movies> getMoviesBySearchQuery(String query)
			throws SQLException {
		Connection conObj = new DBUtil().getConnectionObj();
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

	//get list of all movies by the star details.
	public List<Movies> getMoviesByStarDetails(String fname, String lname) throws SQLException {
		String idQuery = "";
		List<Movies> resultList = new ArrayList<Movies>();

		if(fname.isEmpty() && lname.isEmpty()) return resultList;

		if (!fname.isEmpty() && !lname.isEmpty()) {
			idQuery = "SELECT id FROM stars where first_name LIKE '%" + fname + "%'"
					+ "AND last_name LIKE '" + lname + "'";
		} else if (!fname.isEmpty()) {
			idQuery = "SELECT id FROM stars where first_name LIKE '%" + fname + "%'";
		} else {
			idQuery = "SELECT id FROM stars where last_name LIKE '%" + lname +"%'";
		}
		//get id of the star
		Connection conObj = new DBUtil().getConnectionObj();
		Statement st = conObj.createStatement();
		/*System.out.println("------");
		System.out.println(idQuery);*/
		ResultSet idrs = st.executeQuery(idQuery);
		List<Integer> listOfIds = new ArrayList<Integer>();
		while (idrs.next()) {
			listOfIds.add(idrs.getInt("id"));
		}
		idrs.close();
		st.close();
		conObj.close();
		//get list of movies from stars_id
		List<Movies> listOfMovies = new ArrayList<Movies>();
		for (int n: listOfIds)
		{
		String query = "select m.id, m.title, m.year, m.director, m.banner_url, m.trailer_url from movies as m, stars_in_movies where m.id = stars_in_movies.movie_id AND stars_in_movies.star_id =" + n;
		listOfMovies.addAll(this.getMoviesBySearchQuery(query));
		}
		return listOfMovies;


	}

	public List<genre> getListofGenresBymovie_id (int id) throws SQLException
	{
		String query = "select id, name from genres_in_movies, genres where genre_id = id AND movie_id = " + id;
		Connection conObj = new DBUtil().getConnectionObj();
		Statement st = conObj.createStatement();

		ResultSet idrs = st.executeQuery(query);
		List<genre> genreList = new ArrayList<genre>();
		while (idrs.next()) {
			genre gen = new genre();
			gen.id = idrs.getInt("id");
			gen.name = idrs.getString("name");
			genreList.add(gen);
		}
		idrs.close();
		st.close();
		conObj.close();
		return genreList;
	}

	public List<Star> getListofStarsBymovie_id (int id) throws SQLException
	{
		String query = "select distinct stars.id, stars.first_name, stars.last_name from stars_in_movies, stars where stars.id = stars_in_movies.star_id AND stars_in_movies.movie_id = " + id;
		Connection conObj = new DBUtil().getConnectionObj();
		Statement st = conObj.createStatement();
		ResultSet idrs = st.executeQuery(query);
		List<Star> starList = new ArrayList<Star>();
		while (idrs.next()) {
			Star star = new Star();
			star.id = idrs.getInt(1);
			star.first_name = idrs.getString(2);
			star.last_name = idrs.getString(3);
			starList.add(star);
		}
		idrs.close();
		st.close();
		conObj.close();
		return starList;
	}

	public List<genre> getAllGenres() throws SQLException
	{
		String query = "select distinct id, name from genres order by name;";
		Connection conObj = new DBUtil().getConnectionObj();
		Statement st = conObj.createStatement();
		ResultSet idrs = st.executeQuery(query);
		List<genre> genreList = new ArrayList<genre>();
		while (idrs.next()) {
			genre gen = new genre();
			gen.id = idrs.getInt(1);
			gen.name = idrs.getString(2);
			genreList.add(gen);
		}
		idrs.close();
		st.close();
		conObj.close();
		return genreList;
	}

	public customer authentica(String user, String pass) throws SQLException
	{
		customer cust = null;
		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;
		psmt = conObj.prepareStatement("select count(*) as count, password as pwd from customers where email =?");
		psmt.setString(1, user);
		ResultSet rs = psmt.executeQuery();
		String passdb= "";
		int count =0;
		while (rs.next())
		{
			count = rs.getInt(1);
			if(count >0)
				passdb = rs.getString(2);
		}
		if (passdb.equals(pass))
		{
			psmt = conObj.prepareStatement("select id, first_name, last_name from customers where email =?");
			psmt.setString(1, user);
			rs = psmt.executeQuery();
			while(rs.next())
			{
				cust = new customer(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
		}
		psmt.close();
		conObj.close();
		return cust;
	}
	
	
	public List<cart> getCartFromUserID (int id) throws SQLException
	{
		List<cart> resultSet = new ArrayList<cart>();
		customer cust = null;
		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;
		psmt = conObj.prepareStatement("select title,year,director,sum(quantity), movie_id from cart as c, movies as m where c.movie_id=m.id AND  c.cust_id=? group by c.movie_id");
		psmt.setInt(1, id);
		ResultSet rs = psmt.executeQuery();
		while(rs.next())
		{
			cart c = new cart();
			c.title = rs.getString(1);
			c.year = rs.getInt(2);
			c.director = rs.getString(3);
			c.quantity = rs.getInt(4);
			c.movie_id = rs.getInt(5);
			resultSet.add(c);
		}
		
		psmt.close();
		rs.close();
		conObj.close();
		return resultSet;
		
	}
	
	public boolean validateCard(String id, String fName, String lName, String expiry) throws SQLException
	{
		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;
		psmt = conObj.prepareStatement("select id from creditcards where id=? and first_name=? and last_name=? and expiration=?;");
		psmt.setString(1, id);
		psmt.setString(2, fName);
		psmt.setString(3, lName);
		psmt.setString(4, expiry);		
		ResultSet rs = psmt.executeQuery();
		boolean result = false;
		if(rs.next())
		{
			result = true;
		}
		psmt.close();
		rs.close();
		conObj.close();
		return result;
	}
	
	public boolean clearCart (int id) throws SQLException
	{
		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;
		
		ArrayList<Integer> movieList = new ArrayList<Integer>();
		psmt = conObj.prepareStatement("select distinct(movie_id) from cart where cust_id=?");
		psmt.setInt(1, id);
		ResultSet rs = psmt.executeQuery();
		while(rs.next()){
			movieList.add(rs.getInt("movie_id"));
		}
		
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = new Date();
		for(int a : movieList){
			psmt = conObj.prepareStatement("insert into sales (customer_id,movie_id,sale_date) values(?,?,now())");
			psmt.setInt(1, id);
			psmt.setInt(2, a);
//			psmt.setDate(3, (java.sql.Date) date);
			psmt.executeUpdate();
		}
		
		
		psmt = conObj.prepareStatement("delete from cart where cust_id=?");
		psmt.setInt(1, id);
		int rowsAffected = psmt.executeUpdate();
		boolean res= false;
		if(rowsAffected>0)
			res = true;
		psmt.close();
		conObj.close();
		return res;
	}
	
	public boolean updateCartWithMovieID (int movie_id, int cust_id, int quant) throws SQLException
	{
		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;
		psmt = conObj.prepareStatement("update cart set quantity=? where cust_id=? and movie_id=?;");
		psmt.setInt(1, quant);
		psmt.setInt(2, cust_id);
		psmt.setInt(3, movie_id);
		int rowsAffected = psmt.executeUpdate();
		boolean res= false;
		if(rowsAffected>0)
			res = true;
		psmt.close();
		conObj.close();
		return res;
		
	}
	
	public boolean deleteMovieFromCart (int movie_id, int cust_id) throws SQLException
	{
		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;
		psmt = conObj.prepareStatement("delete from cart where movie_id=? and cust_id=?;");
		psmt.setInt(1, movie_id);
		psmt.setInt(2, cust_id);
		int rowsAffected = psmt.executeUpdate();
		boolean res= false;
		if(rowsAffected>0)
			res = true;
		psmt.close();
		conObj.close();
		return res;
		
	}
	
	public boolean insertToCart(int movie_id, int cust_id) throws SQLException
	{
		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;
		psmt = conObj.prepareStatement("insert into cart values (?,?,1)");
		psmt.setInt(2, movie_id);
		psmt.setInt(1, cust_id);
		int rowsAffected = psmt.executeUpdate();
		boolean res= false;
		if(rowsAffected>0)
			res = true;
		psmt.close();
		conObj.close();
		return res;
	}
	
	public Star getStarDetails (int id) throws SQLException
	{
		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;
		psmt = conObj.prepareStatement("select * from stars where id =?");
		psmt.setInt(1, id);
		ResultSet rs = psmt.executeQuery();
		Star star = new Star();
		while (rs.next())
		{
			star.id = rs.getInt(1);
			star.first_name = rs.getString(2);
			star.last_name = rs.getString(3);
			star.dob = rs.getString(4);
			star.photo_url = rs.getString(5);
		}
		rs.close();
		if(star!=null)
		{
		psmt = conObj.prepareStatement("select title,id from movies where id in (select movie_id from stars_in_movies where star_id =?)");
		psmt.setInt(1, id);
		ResultSet rst = psmt.executeQuery();
		while (rst.next())
		{
			star.movies.add(rst.getString(1));
			star.movies.add(rst.getString(2));
		}
		//System.out.println(star.movies.size());
		}
		psmt.close();
		conObj.close();
		if(star!= null)
			return star;
		else return null;
		
	}
	
	public Movies getMovieDetails (int id) throws SQLException
	{
		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;
		psmt = conObj.prepareStatement("select * from movies where id =?");
		psmt.setInt(1, id);
		ResultSet rs = psmt.executeQuery();
		Movies dispMovie = new Movies();
		while (rs.next())
		{
			dispMovie.id = rs.getInt(1);
			dispMovie.title = rs.getString(2);
			dispMovie.year = rs.getInt(3);
			dispMovie.director = rs.getString(4);
			dispMovie.banner_url = rs.getString(5);
			dispMovie.trailer_url = rs.getString(5);
			
		}
		psmt.close();
		conObj.close();
		if(dispMovie!= null)
			return dispMovie;
		else return null;
		
	}
}
