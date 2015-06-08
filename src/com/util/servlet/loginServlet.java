package com.util.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BO.customer;
import DB.DBUtil;
import DB.dbOperations;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public loginServlet() {
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
		// TODO Auto-generated method stub

		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		try {
			customer cust = new dbOperations().authentica(user, pwd);
			if( cust != null){
				Cookie loginCookie = new Cookie("user",cust.first_name);
				Cookie idCookie = new Cookie("id", Integer.toString(cust.id));
				//setting cookie to expiry in 30 mins
				loginCookie.setMaxAge(30*60);
				idCookie.setMaxAge(30*60);
				response.addCookie(idCookie);
				response.addCookie(loginCookie);
				
				response.sendRedirect("search.jsp");
			}else{
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
				String msg = "Either user name or password is wrong!";
				request.setAttribute("msg", msg);
				rd.include(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
