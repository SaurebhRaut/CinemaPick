package com.util.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.dbOperations;

/**
 * Servlet implementation class addUser
 */
@WebServlet("/addUser")
public class addUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addUser() {
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
		String fName = request.getParameter("fname");
		String lName = request.getParameter("lname");
		String uName = request.getParameter("uname");
		String pass = request.getParameter("pass");
		String email = request.getParameter("email");
		
		
		try {
			if(new dbOperations().addUserDetails(fName,lName,uName, pass, email))
			{				
				request.getRequestDispatcher("displayMetaData.jsp").forward(request, response);
			}
			else
			{
				//request.setAttribute("error", "Card Details Invalid");
				request.getRequestDispatcher("displayDetails.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//System.out.println("Error");
			request.setAttribute("error", "User already present!");
			request.getRequestDispatcher("displayDetails.jsp").forward(request, response); 
		}
	}

}
