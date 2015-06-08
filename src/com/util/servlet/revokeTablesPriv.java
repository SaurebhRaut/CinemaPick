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
 * Servlet implementation class revokeTablesPriv
 */
@WebServlet("/revokeTablesPriv")
public class revokeTablesPriv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public revokeTablesPriv() {
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
		String table = request.getParameter("table");
		String tabPriv = request.getParameter("tabPriv");
		String db = request.getParameter("db");
		String date = request.getParameter("date");
		String uname = request.getParameter("uname");
		
		try {
			if(new dbOperations().RevokeTablePriv(table, tabPriv, db, date, uname))
			{
				request.getRequestDispatcher("seeTables.jsp?db="+db+"&uname="+uname).forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
