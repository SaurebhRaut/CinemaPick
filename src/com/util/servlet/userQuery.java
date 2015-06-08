package com.util.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;

import DB.DBUtil;
import DB.dbOperations;

/**
 * Servlet implementation class userQuery
 */
@WebServlet("/userQuery")
public class userQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userQuery() {
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
		
//		String fName = request.getParameter("user");
//		String pwd = request.getParameter("pwd");
//		String query = request.getParameter("query");
//		String pass = request.getParameter("pass");
//		String email = request.getParameter("email");
		try {
		//=======================
		String fName = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		String query = request.getParameter("query");
		Connection conObj = new DBUtil().getconnection(fName, pwd);
		java.sql.Statement statement = null;
		statement = conObj.createStatement();
		ResultSet rs = statement.executeQuery(query); 
		if(rs.next()){
		System.out.println(rs.getInt(1));
		}
		//========================
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//System.out.println("Error");
			//e.printStackTrace();
			request.setAttribute("error", "User has no priviledges for this query!");
			request.getRequestDispatcher("homeUser.jsp").forward(request, response); 
		}
	}

}

