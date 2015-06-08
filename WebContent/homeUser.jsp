<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<title>Insert title here</title>

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
		if (content == "User has no priviledges for this query!")
			alert("You do not have priviledges to run this query. Please alter your query!");
	}
</script>
</head>
<body onload=myFunction()>
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div>
			
			<ul class="nav navbar-nav navbar-right">
				<li><a href="homeUser.jsp"><span
						class="glyphicon glyphicon-home"></span> Home</a></li>
				<li><a href="loginDBA.jsp"><span
						class="glyphicon glyphicon-log-in"></span> Logout</a></li>
			</ul>
		</div>
	</div>
	</nav>
	<%
		String username = "", pwd = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user"))
					username = cookie.getValue();
				if (cookie.getName().equals("pwd"))
					pwd = cookie.getValue();
			}
		}
	%>
	<div style="margin-top: 50px; width: 290px; margin-left: 540px">
	<h3 align="center"><B><I>Enter your query</I></B></h3>
		<form method="post"
			action="Print_result.jsp?user=<%=username%>&pwd=<%=pwd%>">
			<table align="center" class="table table-striped">
				<tr>
					<td><input type="text" name="query">
					</td>
					<td><input type="submit" value="Submit" class="btn btn-primary">
					</td>
				</tr>
			</table>
		</form>
	</div>

	<%%>
</body>
</html>