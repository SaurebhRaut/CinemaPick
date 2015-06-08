<%@page import="DB.dbOperations"%>
<%@page import="BO.*"%>
<%@page import="java.util.*"%>
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
<title>Home Page</title>
</head>
<body>
	<script>
function clearfunction(id){
	var div = document.getElementById(id);
	div.style.display = "none";
}
function loadXMLDoc(id)
{
	var div = document.getElementById(id);
	div.style.display = "block";
var xmlhttp;
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    document.getElementById("ajaxResponse").innerHTML=xmlhttp.responseText;
    }
  }
  var x=document.getElementById("parstring").value;
  
	xmlhttp.open("GET","autocomplete?parstring="+x,true);
	xmlhttp.send();
}
</script>
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
	%>
	<h3>
		<font color="Black"><marquee direction="left"
				style="background: White">
				Hi,
				<%=username%>
				Welcome!!!
			</marquee> </font>
	</h3>
	<div id="ajaxdiv" onclick="clearfunction('ajaxResponse')"
		style="/* float: right; */ /* margin-right: 120px; */ /* position:relative; */z-index: 10000;position: absolute;margin-left: 80%;">

		<!-- 		<label>SearchAllMovies</label> -->
		<form id="myAjaxRequestForm">
			<input id="parstring" type="text" placeholder="Search here..." name="PartialString"	onkeyup="loadXMLDoc('ajaxResponse')" style="width: 215px;">
		</form>
		<div id="ajaxResponse" style="display: block; word-wrap: break-word; width: 215px; background-color: rgba(100, 149, 237, 0.137255);"></div>
	</div>

	<div id="search_div" class="container" style="width: 500px; z-index:-1">
		<h3 align="center">Advanced Search</h3>
		<form name="search_form" method="get" action="advsearch">
			<table align="center" class="table table-striped">
				<tbody>
					<tr>
						<td style="width: 200px">
							<div class="field_container" style="width: 115px">Title:</div></td>
						<td><input type="text" name="title" size="50" maxlength="100"
							style="width: 230px;"></td>
					</tr>
					<tr>
						<td>
							<div class="field_container">Year:</div></td>
						<td><input type="text" name="year" size="50" maxlength="100"
							style="width: 230px;">
						</td>
					</tr>
					<tr>
						<td>
							<div class="field_container">Director:</div></td>
						<td><input type="text" name="director" size="50"
							maxlength="100" style="width: 230px;">
						</td>
					</tr>
					<tr>
						<td>
							<div class="field_container">Star's First Name:</div></td>
						<td><input type="text" name="f_starName" size="50"
							maxlength="100" style="width: 230px;">
						</td>
					</tr>
					<tr>
						<td>
							<div class="field_container">Star's Last Name:</div></td>
						<td><input type="text" name="l_starName" size="50"
							maxlength="100" style="width: 230px;">
						</td>
					</tr>

					<div class="container">
						<div class="btn-group"
							style="float: right; margin-right: 200px; margin-top: 30px;">
							<tr>
								<td align="center"><button class="btn btn-primary"
										type="submit">Submit</button></td>
								<td align="center"><button class="btn btn-primary"
										type="reset">Reset</button></td>
							</tr>
						</div>
					</div>

				</tbody>
			</table>
		</form>
	</div>
	
	<div id="browse_container" style=" z-index:-1; margin-top:40px; padding:10px">
	<h3 align="center">Browse Movies</h3>
		<div id="genre_container">
			<%
				//get all the genres
				dbOperations dbo = new dbOperations();
				List<genre> genreList = dbo.getAllGenres();
				//System.out.println(genreList.size());
				List<genre> subList1 = genreList.subList(0, 12);
				List<genre> subList2 = genreList.subList(12, 24);
				List<genre> subList3 = genreList.subList(24, 36);
				List<genre> subList4 = genreList.subList(36, genreList.size());
			%>
			<div id="alpha_container">
				<%
					//Generate numerics
					int[] nums = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
					//Generate alphabets
					char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toUpperCase()
							.toCharArray();
				%>
				<table align="center">
					<tr>
						<td align="center">
							<%
								for (int n : nums) {
							%> <a href="browseServ?type=2&ti=<%=n%>"><%=n + " | "%></a> <%
 	}
 %>
						</td>
					</tr>
					<tr>
						<td align="center">
							<%
								for (int i = 0; i < 12; i++) {
							%> <a href="browseServ?type=2&ti=<%=alphabet[i]%>"><%=alphabet[i] + " | "%></a>

							<%
								}
							%>
						</td>
					</tr>
					<tr>
						<td align="center">
							<%
								for (int i = 12; i < alphabet.length; i++) {
							%> <a href="browseServ?type=2&ti=<%=alphabet[i]%>"><%=alphabet[i] + " | "%></a>

							<%
								}
							%>
						</td>
					</tr>
				</table>
			</div>
			<div class="container">
				<div class="btn-group">
					<table style="margin-top: 10px;">
						<tr style="height: 40px;">
							<td>
								<%
									for (genre g : subList1) {
								%>
								<button type="button" class="presentation">
									<a href="browseServ?type=1&ge=<%=g.name.toString()%>"><%=g.name.toString()%></a><br>
								</button> <%
 	}
 %>
							</td>
						</tr>
						<tr style="height: 40px;">
							<td align="center">
								<%
									for (genre g : subList2) {
								%>
								<button type="button" class="presentation">
									<a href="browseServ?type=1&ge=<%=g.name.toString()%>"><%=g.name.toString()%></a><br>
								</button> <%
 	}
 %>
							</td>
						</tr>
						<tr style="height: 40px;">
							<td align="center">
								<%
									for (genre g : subList3) {
								%>
								<button type="button" class="presentation">
									<a href="browseServ?type=1&ge=<%=g.name.toString()%>"><%=g.name.toString()%></a><br>
								</button> <%
 	}
 %>
							</td>
						</tr>
						<tr style="height: 40px;">
							<td align="center">
								<%
									for (genre g : subList4) {
								%>
								<button type="button" class="presentation">
									<a href="browseServ?type=1&ge=<%=g.name.toString()%>"><%=g.name.toString()%></a><br>
								</button> <%
 	}
 %>
							</td>
						</tr>
					</table>
				</div>
			</div>

		</div>

	</div>
</body>
</html>