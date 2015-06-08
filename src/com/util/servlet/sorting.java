package com.util.servlet;

import BO.movieDetails;
import DB.DBMethods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class sorting
 */
@WebServlet("/sorting")
public class sorting extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sorting() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("moviePP"));
		int poss = Integer.parseInt(request.getParameter("curr_poss"));
		int type = Integer.parseInt(request.getParameter("type"));
		int order = Integer.parseInt(request.getParameter("order"));
		DBMethods.Sort(type, order);
		response.sendRedirect("MovieList.jsp?moviePP="+num+"&curr_poss="+poss);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*		int num = Integer.parseInt(request.getParameter("moviePP"));
		int type = Integer.parseInt(request.getParameter("type"));
		int poss = Integer.parseInt(request.getParameter("curr_poss"));
		DBMethods.Sort(type, order);
		request.getRequestDispatcher("MovieList.jsp?moviePP="+num+"&curr_poss="+poss);*/
	}

}
