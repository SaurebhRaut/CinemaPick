<%@page import="BO.Star"%>
<%@page import="DB.dbOperations"%>
<%@page import="BO.Movies"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="css/jquery.qtip.min.css" />
<script type = "text/javascript" src = "js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.qtip.min.js"></script>
</head>
<body>
	<%
		int movie_id = Integer.parseInt(request.getParameter("movie"));
		System.out.println("I am seeing =-- " + movie_id);
		dbOperations dbo = new dbOperations();
		Movies mov = dbo.getMovieDetails(movie_id);
		List<Star> starList = dbo.getListofStarsBymovie_id(movie_id);
	%>
	<div class="democontent">
		<img src="<%=mov.banner_url %>"
			alt="Image Not Available." width="120" height="170" class="photo left">
		<div class="info">
			<strong><h1><%=mov.title %></h1><br></strong>
			<p>
				<strong>Director: </strong> <%=mov.director %><br>
				<strong>Year: </strong><%=mov.year %><br>
			</p>
			<p>
			<Strong>Stars: </Strong> 
				<%int i =0;
				for(Star s : starList){%>
					<Label><%=s.first_name + " " + s.last_name%></Label>				
				<%} %>
			</p>
			<p class="note">
				Image credit: <a
					href="http://flickr.com/photos/dbarronoss/372713343/">Flickr</a>
			</p>
			<div class="clear"></div>
		</div>
	</div>

</body>
</html>