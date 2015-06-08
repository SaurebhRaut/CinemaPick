package com.util.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BO.customer;
import DB.dbOperations;

/**
 * Servlet implementation class loginServletDBA
 */
@WebServlet("/loginServletDBA")
public class loginServletDBA extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public loginServletDBA() {
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
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		try {
			boolean flag = new dbOperations().authenticaDBA(user, pwd);
			if(flag){
				Cookie loginCookie = new Cookie("user",user);
				Cookie loginCookiePassword = new Cookie("pwd",pwd);
				//setting cookie to expiry in 30 mins
				loginCookie.setMaxAge(30*60);
				response.addCookie(loginCookie);
				loginCookiePassword.setMaxAge(30*60);
				response.addCookie(loginCookiePassword);
				if(user.equals("root")){
					response.sendRedirect("displayMetaData.jsp");
				}
				else{
					response.sendRedirect("homeUser.jsp");
				}
			}else{
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/loginDBA.jsp");
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
