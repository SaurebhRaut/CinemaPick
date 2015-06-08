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
 * Servlet implementation class deleteUser
 */
@WebServlet("/deleteUser")
public class deleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteUser() {
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
		String uname = request.getParameter("uname");
		
		try {
			if(new dbOperations().deleteUser(uname))
			{				
				request.getRequestDispatcher("displayDetails.jsp").forward(request, response);
			}
//			else
//			{
//				
//				request.getRequestDispatcher("displayDetails.jsp").forward(request, response);
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//System.out.println("Error");
//			request.setAttribute("error", "User already present!");
//			request.getRequestDispatcher("displayDetails.jsp").forward(request, response); 
			e.printStackTrace();
		}
	}

}
