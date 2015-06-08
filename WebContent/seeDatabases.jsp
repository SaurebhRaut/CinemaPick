<%@page import="BO.users"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="DB.dbOperations"%>
<%@page import="BO.MetaData"%>
<%@page import="BO.users_db"%>
<%@page import="BO.users_tbprev"%>
<%@page import="BO.users_clprev"%>

<%@page import="java.util.*"%>
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
</head>
<body>

	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div>
			<ul class="nav navbar-nav navbar-left">
				<li><a href="displayDetails.jsp"><span
						class="glyphicon glyphicon-arrow-left"></span> Back</a>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="displayMetaData.jsp"><span
						class="glyphicon glyphicon-home"></span> Home</a>
				</li>
				<li><a href="loginDBA.jsp"><span
						class="glyphicon glyphicon-log-in"></span> Logout</a>
				</li>
			</ul>
		</div>
	</div>
	</nav>


	<!-- 	<h3 align="center"> -->
	<!-- 		Add DB Privileges for -->
	<%-- 		<%=request.getParameter("uname")%></h3> --%>


	<%
		dbOperations dbObj = new dbOperations();
		MetaData metaObj = new MetaData();
		metaObj = dbObj.getMetaData();
		if (metaObj != null) {
	%>
	<h2 align="center">
		DB privileges for
		<%=request.getParameter("uname")%></h2>
	<div id="dbDiv" align="center"
		style="margin-top: 50px; width: 200px; margin-left: 200px">
		<input type="hidden" name="type" value="1">

		<table class="table table-striped">
			<tr>
				<th>DB</th>
				<th>Username</th>
				<th>Select Privilege</th>
				<th>Alter Privilege</th>
				<th>Update</th>
				<th>Select</th>
			</tr>
			<%
				String temp = request.getParameter("uname");

					ArrayList<users_db> cus = dbOperations.getuserdetails_db(temp);
					for (int i = 0; i < cus.size(); i++) {
			%>
			<form action="updateDbTable" method="post">
				<tr>
					<td><input readonly type="text" name="db"
						value="<%=cus.get(i).db%>"></td>
					<td><input readonly type="text" name="uname" value="<%=temp%>">
					</td>
					<td><input type="text" name="selPriv"
						value="<%=cus.get(i).select_prev%>"></td>
					<td><input type="text" name="altPriv"
						value="<%=cus.get(i).alter_prev%>"></td>
					<td><input type="submit" name="dbTableUpdateButton"
						value="Update" class="btn btn-primary"></td>
					<td><a href="seeTables.jsp?db=<%=cus.get(i).db%>&uname=<%=temp%>"><input
							type="button" name="showTableButton" value="Show Tables" class="btn btn-primary">
					</td>
				</tr>
			</form>
			<%
				}
				}
			%>
		</table>
	</div>
</body>
</html>