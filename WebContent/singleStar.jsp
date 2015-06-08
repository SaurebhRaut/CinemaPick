<%@page import="DB.dbOperations"%>
<%@page import="BO.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<title>Display Star Details</title>
</head>
<body>
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="search.jsp"><span
						class="glyphicon glyphicon-user"></span> Home</a>
				</li>
				<li><a href="showCart.jsp"><span
						class="glyphicon glyphicon-shopping-cart"></span> Checkout</a>
				</li>
				<li><a href="login.jsp"><span
						class="glyphicon glyphicon-log-in"></span> Logout</a>
				</li>
			</ul>
		</div>
	</div>
	</nav>

	<h3 align="center">Star Details</h3>
	<div id="search_div" class="container" align="center" style="width:1300px">
		<table align="center" class="table table-striped">
			<thead>
				<tr>
					<th>Star ID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Date of Birth</th>
					<th>Photo URL</th>
					<th>Movies</th>
				</tr>
			</thead>
			<%
				int star_id = Integer.parseInt(request.getParameter("star_id"));
				Star star = new dbOperations().getStarDetails(star_id);
				if (star == null)
					out.write("<h3>Error!</h3>");
				else {
			%>
			<tbody>
				<tr>
					<td><%=star.id%></td>
					<td><%=star.first_name%></td>
					<td><%=star.last_name%></td>
					<td><%=star.dob%></td>
					<td style="width: 70px;"><a href="<%=star.photo_url%>"><%=star.photo_url%></a>
					</td>
					<td>
						<%
							for (int i = 0; i < star.movies.size(); i++) {
									out.println("<a href=SingleMovie.jsp?movieID="+star.movies.get(i+1) +">"+star.movies.get(i)+"</a><br>");
									i++;
								}
						%>
					</td>
				</tr>
			</tbody>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>