package DB;
import BO.MetaData;
import BO.Movies;
import BO.Star;
import BO.cart;
import BO.customer;
import BO.dbTable;
import BO.genre;
import BO.users;
import BO.users_clprev;
import BO.users_db;
import BO.users_procPrev;
import BO.users_tbprev;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sun.xml.internal.ws.api.addressing.WSEndpointReference.Metadata;

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
			idQuery = "SELECT id FROM stars where (first_name LIKE '%" + fname + "%'" + "OR levenshtein('"+fname+"',first_name)<=3)"
			+ "AND (last_name LIKE '" + lname + "'"+"OR levenshtein('"+lname+"',last_name)<=3)";
		} else if (!fname.isEmpty()) {
			idQuery = "SELECT id FROM stars where first_name LIKE '%" + fname + "%'"+"OR levenshtein('"+fname+"',first_name)<=3";
		} else {
			idQuery = "SELECT id FROM stars where last_name LIKE '%" + lname +"%'"+"OR levenshtein('"+lname+"',last_name)<=3";
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

	public boolean authenticaDBA(String user, String pass) throws SQLException
	{
		Connection flag = DBUtil.getconnection(user, pass);
		if (flag != null) {
			return true;
		} else
			return false;
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

	public boolean addUserDetails(String fName, String lName, String uName, String pass, String email) throws SQLException
	{
		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;

		psmt = conObj.prepareStatement("CREATE USER ?@'%' IDENTIFIED BY ?;");
		psmt.setString(1, uName);
		psmt.setString(2, pass);
		psmt.executeUpdate();

		psmt = conObj.prepareStatement("call add_user (?,?,?,?,?);");
		psmt.setString(1, fName);
		psmt.setString(2, lName);
		psmt.setString(3, uName);
		psmt.setString(4, email);
		psmt.setString(5, pass);
		int rows = psmt.executeUpdate();

		psmt = conObj.prepareStatement("show databases;");
		ResultSet rs = psmt.executeQuery();
		ArrayList<String> dbList = new ArrayList<String>();
		while(rs.next()){
			dbList.add(rs.getString(1));
		}

		for(String a : dbList){
			psmt = conObj.prepareStatement("insert into db (db,username) values (?,?);");
			psmt.setString(1, a);
			psmt.setString(2, uName);
			psmt.executeUpdate();
		}


		psmt.close();

		conObj.close();
		if(rows>0){
			return true;
		}
		else{
			return false;
		}

	}

	public ArrayList<users> listOfUsers() throws SQLException
	{
		ArrayList<users> list = new ArrayList<users>();
		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;
		psmt = conObj.prepareStatement("select first_name, last_name, email, username from employees natural join user;");

		ResultSet rs = psmt.executeQuery();
		while(rs.next()){
			users objUser = new users();
			objUser.fname = rs.getString(1);
			objUser.lname = rs.getString(2);
			objUser.email = rs.getString(3);
			objUser.uname = rs.getString(4);
			list.add(objUser);
		}
		psmt.close();
		rs.close();
		conObj.close();
		return list;		
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

	public boolean AddTablePriv (String table, String priv, String db, String uname) throws SQLException
	{
		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;
		psmt = conObj.prepareStatement("select count(*) from tables_priv where db=? and username=? and table_name=? and table_priv=?;");
		psmt.setString(1, db);
		psmt.setString(2, uname);
		psmt.setString(3, table);
		psmt.setString(4, priv);
		int count = 0;
		ResultSet rs = psmt.executeQuery();
		while(rs.next()){
			count = Integer.parseInt(rs.getString(1));
		}

		if(count==0){
			psmt = conObj.prepareStatement("insert into tables_priv values (?,?,?,?,now());");
			psmt.setString(1, db);
			psmt.setString(2, uname);
			psmt.setString(3, table);
			psmt.setString(4, priv);
			int rows = psmt.executeUpdate();

			if(priv.equals("select")){
				psmt = conObj.prepareStatement("GRANT SELECT ON " + db + "." + table + " TO ?@'%';");
				psmt.setString(1, uname);
				psmt.executeUpdate();
			}
			else if(priv.equals("insert")){
				psmt = conObj.prepareStatement("GRANT INSERT ON " + db + "." + table + " TO ?@'%';");
				psmt.setString(1, uname);
				psmt.executeUpdate();
			}

			else if(priv.equals("update")){
				psmt = conObj.prepareStatement("GRANT Update ON " + db + "." + table + " TO ?@'%';");
				//psmt.setString(1, db);
				psmt.setString(1, uname);
				psmt.executeUpdate();
			}

			boolean res= false;
			if(rows>0)
				res = true;
			psmt.close();
			rs.close();
			conObj.close();
			return res;
		}
		else{
			psmt.close();
			rs.close();
			conObj.close();
			return true;
		}
	}

	public boolean AddColumnPriv (String table, String priv, String db, String uname, String column) throws SQLException
	{
		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;
		psmt = conObj.prepareStatement("select count(*) from columns_priv where db=? and username=? and table_name=? and column_name=? and column_priv=?;");
		psmt.setString(1, db);
		psmt.setString(2, uname);
		psmt.setString(3, table);
		psmt.setString(4, column);
		psmt.setString(5, priv);
		int count = 0;
		ResultSet rs = psmt.executeQuery();
		while(rs.next()){
			count = Integer.parseInt(rs.getString(1));
		}

		if(count==0){
			psmt = conObj.prepareStatement("insert into columns_priv values (?,?,?,?,?,now());");
			psmt.setString(1, db);
			psmt.setString(2, uname);
			psmt.setString(3, table);
			psmt.setString(4, column);
			psmt.setString(5, priv);
			int rows = psmt.executeUpdate();

			if(priv.equals("select")){
				psmt = conObj.prepareStatement("GRANT SELECT (" + column + ") ON " + db + "." + table + " TO ?@'%';");
				psmt.setString(1, uname);
				psmt.executeUpdate();
			}
			else if(priv.equals("insert")){
				psmt = conObj.prepareStatement("GRANT INSERT (" + column + ") ON " + db + "." + table + " TO ?@'%';");
				psmt.setString(1, uname);
				psmt.executeUpdate();
			}

			else if(priv.equals("update")){
				psmt = conObj.prepareStatement("GRANT Update (" + column + ") ON " + db + "." + table + " TO ?@'%';");
				psmt.setString(1, uname);
				psmt.executeUpdate();
			}

			boolean res= false;
			if(rows>0)
				res = true;
			psmt.close();
			conObj.close();
			return res;
		}

		else{
			psmt.close();
			rs.close();
			conObj.close();
			return true;
		}
	}

	public boolean AddProcedurePriv (String procedure, String priv, String db, String uname) throws SQLException
	{
		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;
		psmt = conObj.prepareStatement("select count(*) from procs_priv where db=? and username=? and routine_name=? and proc_priv=?;");
		psmt.setString(1, db);
		psmt.setString(2, uname);
		psmt.setString(3, procedure);
		psmt.setString(4, priv);
		int count = 0;
		ResultSet rs = psmt.executeQuery();
		while(rs.next()){
			count = Integer.parseInt(rs.getString(1));
		}

		if(count==0){
			psmt = conObj.prepareStatement("insert into procs_priv (db,username,routine_name,proc_priv,timestamp) values (?,?,?,?,now());");
			psmt.setString(1, db);
			psmt.setString(2, uname);
			psmt.setString(3, procedure);
			psmt.setString(4, priv);
			int rows = psmt.executeUpdate();

			//int rows = 0;
			if(priv.equals("execute")){
				psmt = conObj.prepareStatement("GRANT EXECUTE ON PROCEDURE " + db + "." + procedure + " TO ?@'%';");
				psmt.setString(1, uname);
				psmt.executeUpdate();
			}
			boolean res= false;
			if(rows>0)
				res = true;
			psmt.close();
			conObj.close();
			return res;
		}
		else{
			psmt.close();
			rs.close();
			conObj.close();
			return true;
		}
	}


	public boolean RevokeTablePriv (String table, String tabPriv, String db, String date, String uname) throws SQLException
	{
		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;
		psmt = conObj.prepareStatement("delete from tables_priv where db=? and table_name=? and table_priv=? and username=?;");
		psmt.setString(1, db);
		psmt.setString(2, table);
		psmt.setString(3, tabPriv);
		psmt.setString(4, uname);
		int rows = psmt.executeUpdate();

		psmt = conObj.prepareStatement("REVOKE " + tabPriv + " ON " + db + "." + table + " FROM ?@'%';");
		psmt.setString(1, uname);
		psmt.executeUpdate();

		boolean res= false;
		if(rows>0)
			res = true;
		psmt.close();
		conObj.close();
		return res;

	}

	public boolean RevokeColumnPriv (String table, String colPriv, String db, String date, String uname, String column) throws SQLException
	{
		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;
		psmt = conObj.prepareStatement("delete from columns_priv where db=? and table_name=? and column_priv=? and " +
		"column_name=? and username=?;");
		psmt.setString(1, db);
		psmt.setString(2, table);
		psmt.setString(3, colPriv);
		psmt.setString(4, column);
		psmt.setString(5, uname);
		int rows = psmt.executeUpdate();

		psmt = conObj.prepareStatement("REVOKE " + colPriv + "(" + column + ") ON " + db + "." + table + " FROM ?@'%';");
		psmt.setString(1, uname);
		psmt.executeUpdate();

		boolean res= false;
		if(rows>0)
			res = true;
		psmt.close();
		conObj.close();
		return res;

	}

	public boolean RevokeProcedurePriv (String procedure, String procPriv, String db, String date, String uname) throws SQLException
	{
		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;
		psmt = conObj.prepareStatement("delete from procs_priv where db=? and routine_name=? and proc_priv=? and username=?;");
		psmt.setString(1, db);
		psmt.setString(2, procedure);
		psmt.setString(3, procPriv);
		psmt.setString(4, uname);
		int rows = psmt.executeUpdate();

		psmt = conObj.prepareStatement("REVOKE EXECUTE ON PROCEDURE " + db + "." + procedure + " FROM ?@'%';");
		psmt.setString(1, uname);
		psmt.executeUpdate();

		boolean res= false;
		if(rows>0)
			res = true;
		psmt.close();
		conObj.close();
		return res;

	}

	public boolean updateDbTable (String select, String alter, String db, String uname) throws SQLException
	{
		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;
		psmt = conObj.prepareStatement("select select_priv, alter_priv from db where db=? and username=?");
		psmt.setString(1, db);
		psmt.setString(2, uname);
		ResultSet rs = psmt.executeQuery();
		ArrayList<dbTable> list = new ArrayList<dbTable>();

		while(rs.next()){
			dbTable dbObj = new dbTable();
			dbObj.select_prev = rs.getString(1);
			dbObj.alter_prev = rs.getString(2);
			list.add(dbObj);
		}
		int rows = 0;
		if(!list.get(0).select_prev.equals("select")){
			if(select.equals("Y")){
				psmt = conObj.prepareStatement("update db set select_priv='Y' where db=? and username=?");
				psmt.setString(1, db);
				psmt.setString(2, uname);
				rows = psmt.executeUpdate();


				psmt = conObj.prepareStatement("GRANT SELECT ON " + db + ".* TO ?@'%';");
				//psmt.setString(1, db);
				psmt.setString(1, uname);
				rows += psmt.executeUpdate();
			}
			else{
				psmt = conObj.prepareStatement("update db set select_priv='N' where db=? and username=?");
				psmt.setString(1, db);
				psmt.setString(2, uname);
				rows = psmt.executeUpdate();

				psmt = conObj.prepareStatement("REVOKE SELECT ON " + db + ".* FROM ?@'%';");
				//psmt.setString(1, db);
				psmt.setString(1, uname);
				rows += psmt.executeUpdate();
			}
		}

		int rows1 = 0;
		if(!list.get(0).alter_prev.equals("alter")){
			if(alter.equals("Y")){
				psmt = conObj.prepareStatement("update db set alter_priv='Y' where db=? and username=?");
				psmt.setString(1, db);
				psmt.setString(2, uname);
				rows1 = psmt.executeUpdate();


				psmt = conObj.prepareStatement("GRANT ALTER ON " + db + ".* TO ?@'%';");
				//psmt.setString(1, db);
				psmt.setString(1, uname);
				rows1 += psmt.executeUpdate();
			}
			else{
				psmt = conObj.prepareStatement("update db set alter_priv='N' where db=? and username=?");
				psmt.setString(1, db);
				psmt.setString(2, uname);
				rows1 = psmt.executeUpdate();

				psmt = conObj.prepareStatement("REVOKE ALTER ON " + db + ".* FROM ?@'%';");
				//psmt.setString(1, db);
				psmt.setString(1, uname);
				rows1 += psmt.executeUpdate();
			}
		}

		boolean res= false;
		if(rows>=1 || rows1>=1)
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


	public ArrayList<String> getTables(String db) throws SQLException
	{
		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;

		psmt = conObj.prepareStatement("show tables from " + db);


		MetaData metObj =  new MetaData();
		ResultSet rs = psmt.executeQuery();
		while (rs.next())
		{
			metObj.listOfTables.add(rs.getString(1));			
		}
		//System.out.println(metObj.listOfTables.size());
		rs.close();

		psmt.close();
		conObj.close();

		if(metObj != null){
			return metObj.listOfTables;}
		else return null;

	}

	public ArrayList<String> getColumns(String db, String table) throws SQLException
	{
		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;
		ArrayList<String> listCol = new ArrayList<String>();
		psmt = conObj.prepareStatement("show columns from " + db + "." + table + ";");
		ResultSet rs = psmt.executeQuery();
		while (rs.next())
		{
			listCol.add(rs.getString(1));			
		}
		//System.out.println(metObj.listOfTables.size());
		rs.close();

		psmt.close();
		conObj.close();

		if(listCol.size()>0){
			return listCol;}
		else return null;

	}

	public ArrayList<String> getStoredProc(String db) throws SQLException
	{
		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;
		ArrayList<String> listCol = new ArrayList<String>();
		psmt = conObj.prepareStatement("show procedure status where db=?;");
		psmt.setString(1, db);
		ResultSet rs = psmt.executeQuery();
		while (rs.next())
		{
			listCol.add(rs.getString(2));			
		}
		//System.out.println(metObj.listOfTables.size());
		rs.close();

		psmt.close();
		conObj.close();

		if(listCol.size()>0){
			return listCol;}
		else return null;

	}


	public MetaData getMetaData () throws SQLException
	{
		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;
		psmt = conObj.prepareStatement("show tables");
		
		ArrayList<String> tableData;
		MetaData metObj =  new MetaData();
		ResultSet rs = psmt.executeQuery();
		while (rs.next())
		{
			metObj.listOfTables.add(rs.getString(1));			
		}
		rs.close();
		ResultSetMetaData metadata = null;
		ResultSet rs1 = null;
		for (String a : metObj.listOfTables) {
			psmt = conObj.prepareStatement("select * from " + a);
			rs1 = psmt.executeQuery();
			metadata = rs1.getMetaData();
			tableData = new ArrayList<String>();
			for (int i = 1; i <= metadata.getColumnCount(); i++) {				
				tableData.add(metadata.getColumnName(i));
				tableData.add(metadata.getColumnTypeName(i));
			}
			metObj.metaData.put(a, tableData);

		}
		psmt.close();
		rs1.close();
		conObj.close();
		System.out.println(metObj.toString());
		if(metObj != null){
			return metObj;}
		else return null;

	}
	
	public MetaData iGiveMetaData(String dataBase) throws SQLException
	{
		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;
		psmt = conObj.prepareStatement("show tables from " + dataBase);
		//psmt.setString(1, dataBase);
		
		ArrayList<String> tableData;
		MetaData metObj =  new MetaData();
		ResultSet rs = psmt.executeQuery();
		while (rs.next())
		{
			metObj.listOfTables.add(rs.getString(1));			
		}
		rs.close();
		ResultSetMetaData metadata = null;
		ResultSet rs1 = null;
		for (String a : metObj.listOfTables) {
			//psmt = conObj.prepareStatement("select * from " + a);
			psmt = conObj.prepareStatement("select * from " + dataBase + "." + a);
			rs1 = psmt.executeQuery();
			metadata = rs1.getMetaData();
			tableData = new ArrayList<String>();
			for (int i = 1; i <= metadata.getColumnCount(); i++) {				
				tableData.add(metadata.getColumnName(i));
				tableData.add(metadata.getColumnTypeName(i));
			}
			metObj.metaData.put(a, tableData);

		}
		psmt.close();
		rs1.close();
		conObj.close();
		System.out.println(metObj.toString());
		if(metObj != null){
			return metObj;}
		else return null;
		
	}
	
	public static users getuserdetails(String username) throws SQLException{
		users cus =new users();
		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;
		psmt = conObj.prepareStatement("select * from user where username=?");
		psmt.setString(1, username);
		ResultSet rs = psmt.executeQuery();
		while(rs.next()){
			cus.uname = rs.getString(2);
			cus.select_prev = rs.getString(3);
			cus.insert_prev = rs.getString(4);
			cus.update_prev = rs.getString(5);
			cus.alter_prev = rs.getString(6);
			cus.create_prev = rs.getString(7);
			cus.execute_prev = rs.getString(8);
		}

		psmt.close();
		rs.close();
		conObj.close();
		return cus;

	}

	public static ArrayList<users_db> getuserdetails_db(String username) throws SQLException{

		ArrayList<users_db> listObj = new ArrayList<users_db>();
		System.out.println(username);
		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;
		psmt = conObj.prepareStatement("select * from db where username=?");
		psmt.setString(1, username);
		ResultSet rs = psmt.executeQuery();
		while(rs.next()){
			users_db cus = new users_db();
			cus.db = rs.getString(1);
			cus.uname = rs.getString(2);
			cus.select_prev = rs.getString(3);
			cus.alter_prev = rs.getString(4);
			listObj.add(cus);
		}		
		psmt.close();
		rs.close();
		conObj.close();
		return listObj;

	}

	public static ArrayList<users_tbprev> getuserdetails_tbprev(String username) throws SQLException{

		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;
		ArrayList<users_tbprev> listCust = new ArrayList<users_tbprev>();

		psmt = conObj.prepareStatement("select count(*) from tables_priv where username=?");
		psmt.setString(1, username);
		ResultSet rs1 = psmt.executeQuery();
		int count = 0;
		while(rs1.next()){
			count = rs1.getInt(1);
		}

		if(count>=1){
			psmt = conObj.prepareStatement("select * from tables_priv where username=?");
			psmt.setString(1, username);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				users_tbprev cus = new users_tbprev();
				cus.db = rs.getString(1);
				cus.uname = rs.getString(2);
				cus.table_name = rs.getString(3);
				cus.table_prev = rs.getString(4);
				cus.timestamp = rs.getString(5);
				listCust.add(cus);
			}
			psmt.close();
			rs.close();
			conObj.close();
			return listCust;
		}
		else{
			psmt.close();
			conObj.close();
			return null;
		}		
	}

	public static ArrayList<users_clprev> getuserdetails_colPrev(String username) throws SQLException{

		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;
		ArrayList<users_clprev> listCust = new ArrayList<users_clprev>();

		psmt = conObj.prepareStatement("select count(*) from columns_priv where username=?");
		psmt.setString(1, username);
		ResultSet rs1 = psmt.executeQuery();
		int count = 0;
		while(rs1.next()){
			count = rs1.getInt(1);
		}

		if(count>=1){
			psmt = conObj.prepareStatement("select * from columns_priv where username=?");
			psmt.setString(1, username);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				users_clprev cus = new users_clprev();
				cus.db = rs.getString(1);
				cus.uname = rs.getString(2);
				cus.table_name = rs.getString(3);
				cus.column_name = rs.getString(4);
				cus.column_prev = rs.getString(5);
				cus.timestamp = rs.getString(6);
				listCust.add(cus);
			}
			psmt.close();
			rs.close();
			conObj.close();
			return listCust;
		}
		else{
			psmt.close();
			conObj.close();
			return null;
		}		
	}

	public ArrayList<users_procPrev> getuserdetails_procPrev(String username) throws SQLException{

		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;
		ArrayList<users_procPrev> listCust = new ArrayList<users_procPrev>();

		psmt = conObj.prepareStatement("select count(*) from procs_priv where username=?");
		psmt.setString(1, username);
		ResultSet rs1 = psmt.executeQuery();
		int count = 0;
		while(rs1.next()){
			count = rs1.getInt(1);
		}

		if(count>=1){
			psmt = conObj.prepareStatement("select * from procs_priv where username=?");
			psmt.setString(1, username);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				users_procPrev cus = new users_procPrev();
				cus.db = rs.getString(1);
				cus.uname = rs.getString(2);
				cus.routine_name = rs.getString(3);
				cus.routine_type = rs.getString(4);
				cus.proc_prev = rs.getString(5);
				cus.timestamp = rs.getString(6);
				listCust.add(cus);
			}
			psmt.close();
			rs.close();
			conObj.close();
			return listCust;
		}
		else{
			psmt.close();
			conObj.close();
			return null;
		}		
	}


	public static users_clprev getuserdetails_clprev(String username) throws SQLException{
		users_clprev cus = new users_clprev();
		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;
		psmt = conObj.prepareStatement("select * from columns_priv where username=?");
		psmt.setString(1, username);
		ResultSet rs = psmt.executeQuery();
		while(rs.next()){
			cus.db = rs.getString(1);
			cus.uname = rs.getString(2);
			cus.table_name = rs.getString(3);
			cus.column_name = rs.getString(4);
			cus.column_prev = rs.getString(5);
			cus.timestamp = rs.getString(6);
		}

		psmt.close();
		rs.close();
		conObj.close();
		return cus;

	}

	public boolean deleteUser(String uname) throws SQLException {
		Connection conObj = new DBUtil().getConnectionObj();
		CallableStatement cstmt = null;
		String pass = "";

		cstmt = conObj.prepareCall("call delete_user (?,?);");
		cstmt.setString(1, uname);
		cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);
		cstmt.executeUpdate();
		pass = cstmt.getString(2);

		PreparedStatement psmt = null;
		psmt = conObj.prepareStatement("DROP USER ?@'%'");
		psmt.setString(1, uname);
		psmt.executeUpdate();

		psmt.close();
		conObj.close();
		if(!pass.isEmpty()){
			return true;
		}
		else{
			return false;
		}

	}
	
	public ArrayList<String> getAllDatabases() throws SQLException
	{
		Connection conObj = new DBUtil().getConnectionObj();
		PreparedStatement psmt = null;
		psmt = conObj.prepareStatement("show databases;");
		ResultSet rs = psmt.executeQuery();
		ArrayList<String> dbList = new ArrayList<String>();
		while(rs.next()){
			dbList.add(rs.getString(1));
		}
		return dbList;
	}
	
	public List<Movies> getMoviesBySearchQueryajax(String query)
	throws SQLException {
		Connection conObj = new DBUtil().getConnectionObj();
		Statement st = conObj.createStatement();
		ResultSet rs = st.executeQuery(query);
		List<Movies> movieList = new ArrayList<Movies>();
		while (rs.next()) {
			movieList.add(new Movies(rs.getInt(1),rs.getString(2)));
		}
		st.close();
		rs.close();
		conObj.close();
		return movieList;
	}
}
