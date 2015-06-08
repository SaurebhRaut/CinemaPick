package com.util.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.reportMethods;

/**
 * Servlet implementation class genReports
 * @param <E>
 */
@WebServlet("/genReports")
public class genReports<E> extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public genReports() {
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
		
		reportMethods<E> report = new reportMethods<E>();
		String reportPath = report.generateReport();
		
		if(reportPath != null)
		{
			request.setAttribute("message", reportPath);
			System.out.println(reportPath +  "*********");
			request.getRequestDispatcher("successReport.jsp").forward(request, response);
		}
	}

}
