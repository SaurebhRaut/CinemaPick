	<%@page import="BO.Movies"%>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1"%>
	<%@ page import="BO.movieDetails"%>
	<%@ page import="BO.Star"%>
	
	<%@ page import="DB.DBMethods"%>
	<%@ page import="DB.dbOperations"%>
	
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet"
		href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script
		src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<title>Movie Details</title>
	</head>
	<body>
		<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="search.jsp"><span
							class="glyphicon glyphicon-user"></span> Home</a></li>
					<li><a href="showCart.jsp"><span
							class="glyphicon glyphicon-shopping-cart"></span> Checkout</a></li>
					<li><a href="login.jsp"><span
							class="glyphicon glyphicon-log-in"></span> Logout</a></li>
				</ul>
			</div>
		</div>
		</nav>
		<%
		String username = "";
		int id = 0;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user"))
					username = cookie.getValue();
	
				if (cookie.getName().equals("id"))
					id = Integer.parseInt(cookie.getValue());
			}
		}
		if (username == "")
			response.sendRedirect("login.jsp");
		else {
			int movieid = Integer.parseInt(request.getParameter("movieID"));
			dbOperations dbo = new dbOperations();
			Movies mov = dbo.getMovieDetails(movieid);
			movieDetails dispMovie = new movieDetails(mov);
			dispMovie.genreList = dbo.getListofGenresBymovie_id(dispMovie.id);
			dispMovie.starList = dbo.getListofStarsBymovie_id(dispMovie.id);
		%>
		<h3 align="center">Movie Details</h3>
		<div id="search_div" class="container">
			<table align=center class="table table-striped">
				<tr>
					<th>Title</th>
					<th>Year</th>
					<th>Director</th>
					<th>Genres</th>
					<th>Trailer_URL</th>
					<th>Cast</th>
				</tr>
				<tr>
					<td><%=dispMovie.title%></td>
					<td><%=dispMovie.year%></td>
					<td><%=dispMovie.director%></td>
					<td>
						<%for(int i =0; i<dispMovie.genreList.size(); i++)
						{
							out.println("<a href =browseServ?type=1&ge=" + dispMovie.genreList.get(i).name + ">" +dispMovie.genreList.get(i).name+ "</a><br>");
						
						}
					%>
					</td>
					<td><a href="<%=dispMovie.trailer_url%>"
						style="word-break: break-all;"><%=dispMovie.trailer_url%></a></td>
					<td>
						<%
							for (int i = 0; i < dispMovie.starList.size(); i++) {
								out.println("<a href=singleStar.jsp?star_id="
										+ dispMovie.starList.get(i).id + ">"
										+ dispMovie.starList.get(i).first_name + " "
										+ dispMovie.starList.get(i).last_name + "</a><br>");
							}
						%>
					</td>
				</tr>
	
			</table>
		</div>
		<div align="center">
			<table>
				<tr>
					<td><img align="center" src="<%=dispMovie.banner_url%>"
						width="200" height="300"></td>
				</tr>
			</table>
		</div>
		<%} %>
	</body>
	</html>