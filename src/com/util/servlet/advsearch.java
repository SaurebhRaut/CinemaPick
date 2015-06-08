package com.util.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BO.Movies;
import BO.movieDetails;
import DB.dbOperations;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class advsearch
 */
@WebServlet("/advsearch")
public class advsearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public advsearch() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String title = request.getParameter("title");
		String year = request.getParameter("year");
		String director = request.getParameter("director");
		String star_Fname = request.getParameter("f_starName");
		String star_Lname = request.getParameter("l_starName");
		boolean allempty = false;

		if (title.isEmpty() && year.isEmpty() && director.isEmpty())
			allempty = true;

		StringBuilder query = new StringBuilder();
		query.append("Select * from movies where ");
		boolean firstParam = true;
		if (!title.isEmpty()) {
			if (firstParam) {
				query.append(" title like '%" + title + "%'");
				query.append("OR levenshtein('"+title+"',title)<=3");
				firstParam = false;
			} else
			{
				query.append(" AND title like '%" + title + "%'");
				query.append("OR levenshtein('"+title+"',title)<=3)");
			}
		}
		if (!year.isEmpty()) {
			if (firstParam) {
				query.append(" year like '%" + year + "%'");
				firstParam = false;
			} else
				query.append(" AND year like '%" + year + "%'");
		}
		if (!director.isEmpty()) {
			if (firstParam) {
				query.append(" director like '%" + director + "%'");
				query.append("OR levenshtein('"+director+"',director)<=3");
				firstParam = false;
			} else
			{
				query.append(" AND (director like '%" + director + "%'");
				query.append("OR levenshtein('"+director+"',director)<=3)");
			}
		}

		try {
			dbOperations dbop = new dbOperations();
			System.out.println(query.toString());
			
			List<Movies> listFromQuery = new ArrayList<Movies>();
			
			if(!allempty)
				listFromQuery = dbop.getMoviesBySearchQuery(query.toString());
			List<Movies> listFromStarDetails = dbop.getMoviesByStarDetails(
					star_Fname, star_Lname);

			List<Movies> list = new ArrayList<Movies>();
			
			List<Movies> olist = new ArrayList<Movies>();
			List<Movies> ilist = new ArrayList<Movies>();

			if (listFromQuery.size() > listFromStarDetails.size()) {
				olist = listFromQuery;
				ilist = listFromStarDetails;
			} else {
				ilist = listFromQuery;
				olist = listFromStarDetails;
			}

			
			if(ilist.size() == 0)
				list = olist;
			
			// Get intersection of the movie lists
			for (Movies t : ilist) {
				
				if (olist.contains(t)) {
					list.add(t);
				}
			}

			ArrayList<movieDetails> resultSet = new ArrayList<movieDetails>();
			dbOperations dboper = new dbOperations();
			for (Movies m : list) {
				movieDetails mod = new movieDetails(m);
				mod.genreList = dboper.getListofGenresBymovie_id(mod.id);
				mod.starList = dboper.getListofStarsBymovie_id(mod.id);
				resultSet.add(mod);
			}
			
		/*	System.out.println("Result set size----" + resultSet.size());
			for(movieDetails mov: resultSet)
			{
				System.out.println(mov.title);
			}
			System.out.println("***********");
			System.out.println();*/
			
/*			request.setAttribute("movieList", resultSet);
			//request.setAttribute("unique", 1);
//			/request.getRequestDispatcher("//.jsp").forward(request, response);
*/			
			new DB.DBMethods().StoreList(resultSet);
			request.getRequestDispatcher("MovieList.jsp?curr_poss=1&moviePP=5").forward(request, response);
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
