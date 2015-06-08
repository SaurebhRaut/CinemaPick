package com.util.servlet;
import DB.DBUtil;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import java.sql.*;

/**
 * Servlet implementation class AddMovie
 */
@WebServlet("/AddMovie")
public class AddMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMovie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		try {
//			
//		String title = request.getParameter("title");
//		int year = Integer.parseInt(request.getParameter("year"));
//		String director = request.getParameter("director");
//		String banner_url = request.getParameter("banner_url");
//		String trailer_url = request.getParameter("trailer_url");
//		String genres = request.getParameter("genres");
//		String star_fname = request.getParameter("star_fname");
//		String star_lname = request.getParameter("star_lname");
//		int flag=0;
//		
//		Connection dbConn = DBUtil.getConnectionObj();
//		String procedure_call = "{call addMovie(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
//		java.sql.CallableStatement cstmt;
//		
//			
//			String sqlstat = "{call addMovie(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
//			cstmt = dbConn.prepareCall(sqlstat);
//			cstmt.setString(1, title);
//			cstmt.setInt(2, year);
//			cstmt.setString(3, director);
//			cstmt.setString(4, banner_url);
//			cstmt.setString(5, trailer_url);
//			
//			cstmt.setString(6, star_fname);
//			cstmt.setString(7, star_lname);
//			cstmt.setString(8, genres);
//			cstmt.registerOutParameter(9, java.sql.Types.INTEGER);
//			cstmt.executeUpdate();
//            flag = cstmt.getInt(9);
////			if (flag > 0) {
////                String message = "Movie uploaded and saved into database" + flag;
////            }
////			
////			
////			cstmt = dbConn.prepareCall(procedure_call);
////		
////						
////						//set the values of the stored procedure's input parameters
////
////						System.out.println("calling stored procedure . . .");
////						
////						cstmt.registerOutParameter(9, java.sql.Types.INTEGER);
////						
////						cstmt.setString(1, title);
////						cstmt.setInt(2, year);
////						cstmt.setString(3, director);
////						cstmt.setString(4, banner_url);
////						cstmt.setString(5, trailer_url);
////						
////						cstmt.setString(6, star_fname);
////						cstmt.setString(7, star_lname);
////						cstmt.setString(8, genres);
////						
////						
////						boolean hadResults = cstmt.execute();
////						
////						
//////						flag=cstmt.getInt(1);
////						
////						ResultSet result = cstmt.getResultSet();
////						
////						flag = result.getInt(9);
////						
//						if(flag!=-1){
//							String message = "Movie uploaded and saved into database" + flag;
//							request.setAttribute("error", message);
//							request.getRequestDispatcher("homeUser.jsp").forward(request, response); 
//						}
//						else{
//							request.setAttribute("error", "Error!! movie not added");
//							request.getRequestDispatcher("homeUser.jsp").forward(request, response); 
//						}
						
		String title = request.getParameter("title");
		int year = Integer.parseInt(request.getParameter("year"));
		String director = request.getParameter("director");
		String banner_url = request.getParameter("banner_url");
		String trailer_url = request.getParameter("trailer_url");
		String genres = request.getParameter("genres");
		String star_fname = request.getParameter("star_fname");
		String star_lname = request.getParameter("star_lname");
		java.sql.CallableStatement csmt = null;
		Connection conn = null;
		try
		{
			DBUtil db = new DBUtil();
			conn =  db.getConnectionObj();
			String sqlstat = "{call addMovie (?, ?, ?, ? ,? ,?, ?, ?, ?)}";
			csmt = conn.prepareCall(sqlstat);
			csmt.setString(1, title);
			String empty = "";
			csmt.setInt(2, year);
			csmt.setString(3, director);
			csmt.setString(4, banner_url);
			csmt.setString(5, trailer_url);
			csmt.setString(6, star_fname);
			csmt.setString(7, star_lname);
			csmt.setString(8, genres);


			csmt.registerOutParameter(9, java.sql.Types.INTEGER);
			
			
			csmt.executeUpdate();
            int row = csmt.getInt(9);
			if (row > 0) {
                System.out.println("Got row and success! " +  row);
            }
			String message = "Movie uploaded and saved into database";
			request.setAttribute("error", message);
			request.getRequestDispatcher("addmovies.jsp").forward(request, response); 

	}
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("error", "Error!! movie not added");
			request.getRequestDispatcher("addmovies.jsp").forward(request, response); 
			
		}
//	} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//						System.out.println("stored procedure executed");		
				
				
	}

}
