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
 * Servlet implementation class updateProcedurePriv
 */
@WebServlet("/updateProcedurePriv")
public class updateProcedurePriv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateProcedurePriv() {
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
		String procedure = request.getParameter("proc");
		String priv = request.getParameter("priv");
		String db = request.getParameter("db");
		String uname = request.getParameter("uname");
		
		try {
			if(new dbOperations().AddProcedurePriv(procedure, priv, db, uname))
			{
				request.getRequestDispatcher("seeProcedures.jsp?db="+db+"&uname="+uname).forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
