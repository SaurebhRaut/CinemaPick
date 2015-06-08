package com.util.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BO.Movies;
import BO.movieDetails;
import DB.dbOperations;

/**
 * Servlet implementation class browseServ
 */
@WebServlet("/browseServ")
public class browseServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public browseServ() {
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
		ArrayList<movieDetails> resultSet = new ArrayList<movieDetails>();
		String type = request.getParameter("type");
		switch (Integer.parseInt(type)) {
		case 1: {
			String genre = request.getParameter("ge");
			String query = "select m.id, m.title, m.year, m.director, m.banner_url, m.trailer_url from genres_in_movies, genres, movies as m where genres.id = genres_in_movies.genre_id AND m.id = genres_in_movies.movie_id AND name LIKE '"
					+ genre + "'";
			try {
				dbOperations dbo = new dbOperations();
				List<Movies> list = dbo
						.getMoviesBySearchQuery(query.toString());
				// form the list of movie details...
				resultSet = new ArrayList<movieDetails>();
				for (Movies m : list) {
					movieDetails mod = new movieDetails(m);
					mod.genreList = dbo.getListofGenresBymovie_id(mod.id);
					mod.starList = dbo.getListofStarsBymovie_id(mod.id);
					resultSet.add(mod);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		case 2:{
			String title = request.getParameter("ti");
			String query = "Select * from movies where title like '" + title + "%'";
			try {
				dbOperations dbo = new dbOperations();
				List<Movies> list = dbo
						.getMoviesBySearchQuery(query.toString());
				// form the list of movie details...
				resultSet = new ArrayList<movieDetails>();
				for (Movies m : list) {
					movieDetails mod = new movieDetails(m);
					mod.genreList = dbo.getListofGenresBymovie_id(mod.id);
					mod.starList = dbo.getListofStarsBymovie_id(mod.id);
					resultSet.add(mod);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		}
		
		//request.setAttribute("movieList", resultSet);
		new DB.DBMethods().StoreList(resultSet);
		request.getRequestDispatcher("MovieList.jsp?curr_poss=1&moviePP=5").forward(request, response);
		
		System.out.println("Result set size----" + resultSet.size());
		for(movieDetails mov: resultSet)
		{
			System.out.println(mov.title);
		}
		System.out.println("***********");
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
