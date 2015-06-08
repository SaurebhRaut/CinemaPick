<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="BO.movieDetails"%>
<%@ page import="BO.movieDetails"%>
<%@ page import="BO.Star"%>
<%@ page import="DB.DBMethods"%>
<%-- <%@ page import="test.dummy"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
form.sortBut {
	float: left;
	margin: 5px;
}

#sortContainer {
	margin-left: 68%;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<title>Display Movies</title>
<link type="text/css" rel="stylesheet" href="css/jquery.qtip.min.css" />
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.qtip.min.js"></script>
<script type="text/javascript" src="js/jquery.qtip.js"></script>
<!-- <script type="text/javascript" src="js/jquery.qtip.min.js.map"></script> -->
<script type="text/javascript" src="js/images.loaded.pkg.min.js"></script>
<style type="text/css">
.qtip-wiki {
	max-width: 400px;
}

.qtip-wiki p {
	margin: 0 0 6px;
	font-size: 13.5px;
}

.qtip-wiki h1 {
	font-size: 15px;
	line-height: 1.1;
	margin: 0 0 5px;
}

.qtip-wiki img {
	float: left;
	margin: 10px 10px 10px 0;
}

.qtip-wiki .info {
	overflow: hidden;
}

.qtip-wiki p.note {
	font-weight: 700;
}
</style>
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
		else
			response.sendRedirect("login.jsp");
		
		if (username == "" || id ==0)
			response.sendRedirect("login.jsp");
		else {
			System.out.println(username);
			int Currposs = Integer.parseInt(request
					.getParameter("curr_poss"));
			int nummovies = Integer.parseInt(request
					.getParameter("moviePP"));
			ArrayList<movieDetails> result_movies = new ArrayList<movieDetails>();
			result_movies = DBMethods.RetriveList();
			int count = 0;
			if (result_movies.size() > 0) {
	%>
	<h3 align="center">
		<B>Movie List</B>
	</h3>
	<div class="container">
		<div class="dropdown">
			<I>Number of entries per page: </I>
			<button class="btn btn-default dropdown-toggle" type="button"
				data-toggle="dropdown">
				Select <span class="caret"></span>
			</button>
			<ul class="dropdown-menu" style="margin-left: 15.7%;"">
				<li><a href="moviesPerPage?MoviesPerPage=2">2</a></li>
				<li><a href="moviesPerPage?MoviesPerPage=3">3</a></li>
				<li><a href="moviesPerPage?MoviesPerPage=5">5</a></li>
				<li><a href="moviesPerPage?MoviesPerPage=10">10</a></li>
				<li><a href="moviesPerPage?MoviesPerPage=20">20</a></li>
				<li class="divider"></li>
			</ul>
		</div>
	</div>
	<div id="sortContainer">
		<form method="get" action="sorting" class="sortBut">
			<input type=hidden name="moviePP" value="<%=nummovies%>"> <input
				type=hidden name=curr_poss value="<%=Currposs%>"> <input
				type=hidden name="type" value="<%=1%>"> <input type=hidden
				name="order" value="<%=0%>"><input type=submit
				value="ASC Title">
		</form>
		<form method="get" action="sorting" class="sortBut">
			<input type=hidden name="moviePP" value="<%=nummovies%>"> <input
				type=hidden name=curr_poss value="<%=Currposs%>"> <input
				type=hidden name="type" value="<%=1%>"> <input type=hidden
				name="order" value="<%=1%>"><input type=submit
				value="DSC Title">
		</form>
		<form method="get" action="sorting" class="sortBut">
			<input type=hidden name="moviePP" value="<%=nummovies%>"> <input
				type=hidden name="curr_poss" value="<%=Currposs%>"> <input
				type=hidden name="type" value="<%=2%>"> <input type=hidden
				name="order" value="<%=0%>"><input type=submit
				value="ASC Year">
		</form>
		<form method="get" action="sorting" class="sortBut">
			<input type=hidden name="moviePP" value="<%=nummovies%>"> <input
				type=hidden name="curr_poss" value="<%=Currposs%>"> <input
				type=hidden name="type" value="<%=2%>"> <input type=hidden
				name="order" value="<%=1%>"><input type=submit
				value="DSC Year">
		</form>
	</div>
	<%
		ArrayList<movieDetails> currmovie = new ArrayList<movieDetails>();
				for (int i = (Currposs - 1) * nummovies; i <= Math.min(((Currposs - 1)
						* nummovies + nummovies - 1),result_movies.size()); i++) {
					if (i >= result_movies.size()) {
						break;
					}
					currmovie.add(count, result_movies.get(i));
					count++;
				}
	%>
	<div id=search_div class="container">
		<table align=center class="table table-striped">
			<thead>
				<tr>
					<!-- 					<th align="center">ID</th> -->
					<th align="center">Title</th>
					<th align="center">Year</th>
					<th align="center">Director</th>
					<th align="center">Cast</th>
					<th align="center">Genres</th>
				</tr>
			</thead>
			<%
				movieDetails currmovieentry = new movieDetails();
						currmovieentry = currmovie.get(0);
						int i = 0;
						out.println("<tbody>");
						while (currmovieentry != null) {
							out.println("<tr>");
							// 						out.println("<td>" + currmovieentry.id + "</td>");
							/* out.println("<td><a href=SingleMovie.jsp?movieID="
									+ currmovieentry.id + ">"
									+ currmovieentry.title + "</a></td>"); */
							out.println("<td><label class = iamTool id = toolTip.jsp?movie=" + currmovieentry.id + ">"+ "<a href=SingleMovie.jsp?movieID="+currmovieentry.id+">"+currmovieentry.title+"</a></label></td>");
							out.println("<td>" + currmovieentry.year + "</td>");
							out.println("<td>" + currmovieentry.director + "</td>");
							out.println("<td>");
							for (int j = 0; j < currmovieentry.starList.size(); j++) {
								out.println("<a href=singleStar.jsp?star_id="
										+ currmovieentry.starList.get(j).id + ">"
										+ currmovieentry.starList.get(j).first_name
										+ " "
										+ currmovieentry.starList.get(j).last_name
										+ "</a>" + "    ");
							}
							out.println("</td>");
							
							//diplaying genres
							out.println("<td>");
							for (int j = 0; j < currmovieentry.genreList.size(); j++) {
								out.println(currmovieentry.genreList.get(j).name);
							}
							out.println("</td>");
							
							out.println("<td>");

							out.println("<a href=addCart?movieID="
									+ currmovieentry.id + "&cust_id=" + id
									+ "><U>Add to Cart</U></a>");
							out.println("</td>");
							out.println("</tr>");

							i++;
							if (i == currmovie.size()) {
								break;
							}
							currmovieentry = currmovie.get(i);
						}
			%>
			</tbody>
		</table>
	</div>

	<table align=center>
		<tr>
			<div id=paging>
				<%
					int size = (DBMethods.ReturnSize() / nummovies);
					float sizrem = (DBMethods.ReturnSize() % nummovies);
					if(sizrem>0){
						size +=1;
					}
					System.out.println(size);
				%>
				<td style="width: 43px;">
					<%
						if ((Currposs - 1) > 0) {
									out.println("<a href=MovieList.jsp?curr_poss="
											+ (Currposs - 1) + "&moviePP=" + nummovies
											+ ">Prev</a>");
								} else {
									out.println("<a href=MovieList.jsp?curr_poss=" + 1
											+ "&moviePP=" + nummovies + ">Prev" + "</a>");
								}
								out.println("</td>");
								if ((Currposs - 2) > 0) {
									for (int k = Currposs - 2; k <= Math.min(Currposs + 2,
											size); k++) {
										out.println("<td style=width: 20px;>");
										out.println("<a href=MovieList.jsp?curr_poss=" + k
												+ "&moviePP=" + nummovies + ">" + k + " | "
												+ "</a>");
										out.println("</td>");
									}
								} else {
									for (int k = 1; k <= Math.min(size, 5); k++) {
										out.println("<td style=width:20px;>");
										out.println("<a href=MovieList.jsp?curr_poss=" + k
												+ "&moviePP=" + nummovies + ">" + k + " | "
												+ "</a>");
										out.println("</td>");
									}
								}

								out.println("<td style=width: 15px;>");
								if ((Currposs + 1) > size) {
									out.println("<a href=MovieList.jsp?curr_poss="
											+ (Currposs) + "&moviePP=" + nummovies
											+ ">Next</a>");
								} else {
									out.println("<a href=MovieList.jsp?curr_poss="
											+ (Currposs + 1) + "&moviePP=" + nummovies
											+ ">Next" + "</a>");
								}
								out.println("</td>");
								out.println("</tr>");
					%>
				
			</div>
	</table>
	<%
		} else {
				out.println("<h3 align=center><B><I>No Data Found!!!</I></B></h3>");
			}
		}
	%>

	<script type="text/javascript">
		//Create the tooltips only when document ready
		$(document).ready(function() {
			// MAKE SURE YOUR SELECTOR MATCHES SOMETHING IN YOUR HTML!!!
			$('.iamTool').each(function() {
				$(this).qtip({
					content : {
						text : function(event, api) {
							$.ajax({
								cache : false,
								async : true,
								url : api.elements.target.attr('id')
							// Use href attribute as URL
							}).then(function(content) {
								// Set the tooltip content upon successful retrieval
								api.set('content.text', content);
							}, function(xhr, status, error) {
								// Upon failure... set the tooltip content to error
								api.set('content.text', status + ': ' + error);
							});

							return 'Loading...'; // Set some initial text
						}
					},
					position : {
						viewport : $(window)
					},
					style : 'qtip-wiki'
				});
			});
		});
	</script>
</body>
</html>