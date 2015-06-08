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
 * Servlet implementation class validateCreditCard
 */
@WebServlet("/validateCreditCard")
public class validateCreditCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public validateCreditCard() {
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
		int custId = Integer.parseInt(request.getParameter("user_id"));
		String fName = request.getParameter("fname");
		String lName = request.getParameter("lname");
		String credId = request.getParameter("c_num");
		String expiry = request.getParameter("expiryDate");
		
		try {
			if(new dbOperations().validateCard(credId, fName, lName, expiry))
			{
				new dbOperations().clearCart(custId);
				request.getRequestDispatcher("search.jsp").forward(request, response);
			}
			else
			{
				request.setAttribute("error", "Card Details Invalid");
				request.getRequestDispatcher("showCart.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

}
