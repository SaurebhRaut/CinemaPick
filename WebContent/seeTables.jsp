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
<script type="text/javascript">
	function chh() {
		var dropdown = document.getElementById("tableDropDown");

	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div>
			<ul class="nav navbar-nav navbar-left">
				<li><a
					href="seeDatabases.jsp?uname=<%=request.getParameter("uname")%>"><span
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

	<%
		String uname = request.getParameter("uname");
		String db = request.getParameter("db");
		dbOperations dbObj = new dbOperations();
		MetaData metaObj = new MetaData();
		metaObj = dbObj.getMetaData();
		if (metaObj != null) {
	%>
	<div id="tableSelect">

		<script type="text/javascript">
			function chh() {
				document.getElementById("privType").style.display = "block";
			}

			function chh1() {
				document.getElementById("subButton").style.display = "block";
				var firstDropdown = document.getElementById("tableDropDown");
				var firstValue = firstDropdown.options[firstDropdown.selectedIndex].value;
				var tableVal = document.getElementById("table");
				tableVal.value = firstValue;

				var secondDropdown = document.getElementById("privDropDown");
				var secondValue = secondDropdown.options[secondDropdown.selectedIndex].value;
				var privVal = document.getElementById("priv");
				privVal.value = secondValue;
			}

			function showCol() {
				var db = document.getElementById("db").value;
				var uname = document.getElementById("uname").value;
				var firstDropdown = document.getElementById("tableDropDown");
				var firstValue = firstDropdown.options[firstDropdown.selectedIndex].value;
				window.location = "seeColumns.jsp?db=" + db + "&uname=" + uname
						+ "&table=" + firstValue;
			}

			function showProc() {
				var db = document.getElementById("db").value;
				var uname = document.getElementById("uname").value;
				// 				var firstDropdown = document.getElementById("tableDropDown");
				// 				var firstValue = firstDropdown.options[firstDropdown.selectedIndex].value;
				window.location = "seeProcedures.jsp?db=" + db + "&uname="
						+ uname;
			}
		</script>
	
		<center>
			<div id="insertBy">
				<select id="tableDropDown" onchange="chh();">
					<option value="NONE">Select Table</option>
					<%
						ArrayList<String> cust = dbObj.getTables(db);
							for (int i = 0; i < cust.size(); i++) {
					%>
					<option value="<%=cust.get(i)%>"><%=cust.get(i)%></option>
					<%
						}
					%>
				</select>
			</div>
		</center>
	</div>

	<div id="privType" style="display: none; padding:10px" align="center">
		<select id="privDropDown" onchange="chh1();">
			<option value="none">Select Privilege</option>
			<option value="select">Select</option>
			<option value="insert">Insert</option>
			<option value="update">Update</option>
		</select>
	</div>

	<div id="subButton" style="display: none;" align="center">
		<form action="updateTablePriv" method="post">
			<input type="submit" value="Add Privilege"> <input
				type="hidden" id="table" name="table" value=> <input
				type="hidden" id="priv" name="priv"> <input type="hidden"
				id="db" name="db" value="<%=db%>"> <input type="hidden"
				id="uname" name="uname" value="<%=uname%>">
		</form>
		<input type="button" value="Columns" onClick="showCol()" style="
    margin-top: 10px;"> <input
			type="button" value="Stored Procedures" onClick="showProc()">
	</div>

	<%
		ArrayList<users_tbprev> cus1 = dbOperations
					.getuserdetails_tbprev(request.getParameter("uname"));
			if (cus1 != null) {
	%>
	<h2 align="center">
		Table privileges for
		<%=request.getParameter("uname")%></h2>
	<div id="tabDiv" align="center"
		style="margin-top: 50px; width: 500px; margin-left: 270px">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>DB</th>
					<th>Table Name</th>
					<th>Table Privilege</th>
					<th>Time</th>

				</tr>
			</thead>
			<tbody>
				<%
					for (int i = 0; i < cus1.size(); i++) {
				%>
				<form action="revokeTablesPriv" method="Post">
					<tr>
						<td><input readonly type="text" name="db"
							value="<%=cus1.get(i).db%>">
						</td>
						<td><input readonly type="text" name="table"
							value="<%=cus1.get(i).table_name%>">
						</td>
						<td><input readonly type="text" name="tabPriv"
							value="<%=cus1.get(i).table_prev%>">
						</td>
						<td><input readonly type="text" name="date"
							value="<%=cus1.get(i).timestamp%>">
						</td>

						<td><input type="submit" value="Revoke" name="revokeButton" class="btn btn-primary">
						</td>
						<td><input type="hidden" name="uname"
							value="<%=request.getParameter("uname")%>">
						</td>
					</tr>
				</form>
				<%
					}
				%>
			</tbody>
		</table>
		<%
			} else {
		%>
		<h3 align="center">
			<B>No table privileges for <%=request.getParameter("uname")%>!!!</B>
		</h3>
		<%
			}
			}
		%>
	</div>
</body>
</html>