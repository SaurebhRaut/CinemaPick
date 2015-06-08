<%@page import="java.io.*"%>
<%@page import="DB.dbOperations"%>
<%@page import="java.util.*"%>
<%@page import="BO.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<style>
.overlay {
	position: fixed;
	top: 0;
	left: 0;
	height: 100%;
	width: 100%;
	background-color: rgba(0, 0, 0, 0.5);
	z-index: 10;
}

#cc {
	position: fixed;
	top: 40%;
	padding: 25px;
	left: 45%;
	margin-top: -100px;
	margin-left: -150px;
	background-color: #EDEBE4;
	border-radius: 5px;
	text-align: center;
	z-index: 11; /* 1px higher than the overlay layer */
}
</style>
<script>
	function call_cc(id) {
		var div = document.getElementById(id);
		div.style.display = div.style.display == "none" ? "block" : "none";
		document.getElementById("overlay").style.display = "block";
	}

	function overlayClose() {
		document.getElementById("overlay").style.display = "none";
	}
	function myFunction() {
		var content = '${error}';
		if (content == "Card Details Invalid")
			alert("Invalid Card Details. Please recheck!");
	}
	function callHome() {
		window.location = "search.jsp";
	}
	function callLogin() {
		window.location = "login.jsp";
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shopping Cart</title>
</head>
<body onload="myFunction()">

	<div id="overlay" class="overlay" style="display: none;"></div>

	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="search.jsp"><span
						class="glyphicon glyphicon-user"></span> Home</a></li>
				<li><a href="login.jsp"><span
						class="glyphicon glyphicon-log-in"></span> Logout</a></li>
			</ul>
		</div>
	</div>
	</nav>

	<!-- 	<div class="container"> -->
	<!-- 		<div class="btn-group" -->
	<!-- 			style="float: right; margin-right: 70px; margin-top: 50px;"> -->
	<!-- 			<button type="button" class="btn btn-primary" onclick="callHome()">Home</button> -->
	<!-- 			<button type="button" class="btn btn-primary" onclick="callLogin()">Logout</button> -->
	<!-- 		</div> -->
	<!-- 	</div> -->

	<div id="cartContainer" class="container">
		<%
			int id = -1;
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("id"))
						id = Integer.parseInt(cookie.getValue());
				}
			}
			if (id == -1)
				response.sendRedirect("login.jsp");
			else {
				List<cart> cartList = new dbOperations().getCartFromUserID(id);
				String msg = "";
		%>

		<%
			if (!cartList.isEmpty()) {
					int sumOFQuatn = 0;
		%>
		<h3 align="center">Shopping Cart</h3>
		<table align=center class="table table-striped">
			<thead>
				<tr>
					<th>Movie Title</th>
					<th>Year</th>
					<th>Director</th>
					<th>Quantity</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (cart c : cartList) {
								sumOFQuatn += c.quantity;
				%>

				<tr>
					<td><%=c.title%></td>
					<td><%=c.year%></td>
					<td id="dirc"><%=c.director%></td>
					<form action="updateCart" method="get">
						<input type="hidden" name="movie_id" value="<%=c.movie_id%>">
						<input type="hidden" name="cust_id" value="<%=id%>">
						<td><input type="text" name="cart_quan"
							value="<%=c.quantity%>"></td>
						<td><input type="submit" name="updateButton" value="Update"></a>
						</td>
					</form>
					<td><a
						href="deleteCart?movie_id=<%=c.movie_id%>&cust_id=<%=id%>"><input
							type="button" name="deleteButton" value="Delete"> </a></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>

	</div>
	<div align="right">
		<h3 style="margin-right: 300px;">
			Total price = $<%=(15 * sumOFQuatn)%></h3>
	</div>
	<div class="container">
		<div class="btn-group"
			style="float: right; margin-right: 200px; margin-top: 30px;">
			<button type="button" class="btn btn-primary" onclick="call_cc('cc')">Checkout</button>
		</div>
	</div>
	<!-- 	<div align="right"> -->
	<!-- 		<input type="button" Value="Check Out!" onClick="call_cc('cc')" -->
	<!-- 			style="margin-right: 300px;margin-top: 20px;"> -->
	<!-- 	</div> -->

	<div id="cc" style="display: none;">
		<form method="post" action="validateCreditCard">
			<h3 align="center">Credit Card Details</h3>
			<input type="hidden" name="user_id" value="<%=id%>">
			<table align="center">
				<tr>
					<td>First Name:</td>
					<td><input type="text" name="fname"></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><input type="text" name="lname"></td>
				</tr>
				<tr>
					<td>Credit Card Number:</td>
					<td><input type="text" name="c_num"></td>
				</tr>
				<tr>
					<td>Expiration Date:</td>
					<td><input type="date" name="expiryDate"
						style="height: 25px; width: 174px;"></td>
				</tr>
				<tr>
					<td align="center"><button type="submit"
							class="btn btn-primary" style="margin-top: 15px;">Submit</button>
					<td align="center">
						<button type="button" class="btn btn-primary"
							style="margin-top: 15px;"
							onclick="call_cc('cc'); overlayClose();">Cancel</button>

					</td>
				</tr>
			</table>
		</form>
	</div>
	<br>
	<%-- <div>
			${error}
	</div> --%>
	<%
		} else {
				msg = "Cart is empty!";
				out.print("<h3 align=center>" + msg + "</h3>");
			}
		}
	%>
</body>
</html>