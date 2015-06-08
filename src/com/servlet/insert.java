package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BO.Customer;
import BO.Star;
import DB.DBMethods;

/**
 * Servlet implementation class insert
 */
@WebServlet("/insert")
public class insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int type = Integer.parseInt(request.getParameter("type"));
		int result = 0;
		if (type ==1) //insert star
		{
			Star newstar = new Star();
			newstar.first_name = request.getParameter("star_first_name");
			newstar.last_name = request.getParameter("star_last_name");
			if(newstar.last_name == "")
				newstar.last_name = newstar.first_name;
			
			newstar.dob = request.getParameter("dob_star");
			newstar.photo_url = request.getParameter("star_url");
			DBMethods db = new DBMethods();
			result = db.insertStar(newstar);
		}
		else if (type ==2)
		{
			Customer cust = new Customer();
			cust.first_name = request.getParameter("custom_first_name");
			cust.last_name = request.getParameter("custom_last_name");
			if(cust.last_name == "")
				cust.last_name = cust.first_name;
			cust.address = request.getParameter("custom_address");
			cust.email = request.getParameter("custom_email");
			cust.password = request.getParameter("custom_pass");
			DBMethods db = new DBMethods();
			result = db.insertCustomer(cust);
		}
		
		System.out.println(result);
		String msg;
		if(result >0)
			msg = "Entered the Database with ID = " + result;
		else
			msg = "Insertion Failed";
		
		request.setAttribute("message", msg);
		request.getRequestDispatcher("Insert.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
