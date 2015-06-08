<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="DB.dbOperations"%>
<%@page import="java.util.*"%>
<%@page import="BO.*"%>
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
		if (content == "User already present!")
			alert("User already present. Please add a new user!");
	}
</script>
</head>
<body onload=myFunction()>

	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="displayMetaData.jsp"><span
						class="glyphicon glyphicon-home"></span> Home</a></li>
				<li><a href="loginDBA.jsp"><span
						class="glyphicon glyphicon-log-in"></span> Logout</a></li>
			</ul>
		</div>
	</div>
	</nav>

	<%
		dbOperations dbObj = new dbOperations();
		users objUser = new users();
		ArrayList<users> listOfUsers = dbObj.listOfUsers();
		if (listOfUsers.size() > 0) {
	%>
	<h3 align="center"><B>User Details</B></h3>
	<div id="details" style="margin-top: 50px; width: 900px; margin-left: 250px">	
		<table align="center" class="table table-striped">
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>User Name</th>
				<th></th>
			</tr>
			<%
				for (int i = 0; i < listOfUsers.size(); i++) {
						objUser = listOfUsers.get(i);
			%>
			<!-- 			<form action="insert"> -->
			<form action="deleteUser" method="post">
			<tr>
				<td><%=objUser.fname%></td>
				<td><%=objUser.lname%></td>
				<td><%=objUser.email%></td>
				<td><a href="seeDatabases.jsp?uname=<%=objUser.uname%>"><%=objUser.uname%></a>
				</td>
				<td><input type="submit" value="Delete" class="btn btn-primary">
				</td>
				<td><input type="hidden" name="uname" value="<%=objUser.uname%>"></td>
				<%-- 				<td><a href="addPrivileges.jsp?uname=<%=objUser.uname%>"><input --%>
				<!-- 						type="button" value="Update"> </a></td> -->

			</tr>
			</form>
		
		<%
			}%>
			</table>
			</div>
			<%	} else {
		%>
		<h3 align="center">No Users Present!!!</h3>
		<%
			}
		%>

	

	<div class="container">
		<div class="btn-group" align="center" style="margin-top: 50px; margin-left: 510px">
<!-- 			style="float: right; margin-right: 200px; margin-top: 30px;"> -->
			<button type="button" class="btn btn-primary" onclick="call_cc('cc')">Add
				New User</button>
		</div>
	</div>

	<div id="cc" style="display: none;">
		<form method="post" action="addUser">
			<h3 align="center">User Details</h3>
			<input type="hidden" name="user_id" value="id">
			<table align="center" >
				<tr>
					<td>First Name:</td>
					<td><input type="text" name="fname">
					</td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><input type="text" name="lname">
					</td>
				</tr>
				<tr>
					<td>Username:</td>
					<td><input type="text" name="uname">
					</td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><input type="text" name="email">
					</td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="text" name="pass">
					</td>
				</tr>

				<tr>
					<td align="center"><button type="submit"
							class="btn btn-primary" style="margin-top: 15px;">Submit</button>
					<td align="center">
						<button type="button" class="btn btn-primary"
							style="margin-top: 15px;"
							onclick="call_cc('cc'); overlayClose();">Cancel</button></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>