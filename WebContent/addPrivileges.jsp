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
<script>
	function check() {
		var dropdown = document.getElementById("selectSearchDown");
		var current_value = dropdown.options[dropdown.selectedIndex].value;
		if (current_value == "DB") {
			document.getElementById("dbDiv").style.display = "block";
			document.getElementById("userDiv").style.display = "none";
			document.getElementById("tabDiv").style.display = "none";
			document.getElementById("colDiv").style.display = "none";
			document.getElementById("dbSelect").style.display = "none";
		} else if (current_value == "tab") {
			document.getElementById("tabDiv").style.display = "block";
			document.getElementById("userDiv").style.display = "none";
			document.getElementById("dbDiv").style.display = "none";
			document.getElementById("colDiv").style.display = "none";
			document.getElementById("dbSelect").style.display = "block";
		} else if (current_value == "col") {
			document.getElementById("colDiv").style.display = "block";
			document.getElementById("userDiv").style.display = "none";
			document.getElementById("dbDiv").style.display = "none";
			document.getElementById("tabDiv").style.display = "none";
			document.getElementById("dbSelect").style.display = "none";
		} else if (current_value == "proc") {
			document.getElementById("userDiv").style.display = "block";
			document.getElementById("tabDiv").style.display = "none";
			document.getElementById("dbDiv").style.display = "none";
			document.getElementById("colDiv").style.display = "none";
			document.getElementById("dbSelect").style.display = "none";
		}
	}
</script>
</head>
<body>

	<div id="nav">
		<a href="displayMetaData.jsp">Home</a> <a href="loginDBA.jsp">Logout</a>
	</div>


	<h3 align="center">
		Add Privileges for
		<%=request.getParameter("uname")%></h3>
	<br>
	<center>
		<div id="insertBy">
			<select id="selectSearchDown" onchange="check();">
				<option value="0">Select</option>
				<option value="DB">DataBase</option>
				<option value="tab">Tables</option>
				<option value="col">Columns</option>
				<option value="proc">Stored procedures</option>
			</select>
		</div>
	</center>
	<br>

	<!-- 	<h3 align="center"> -->
	<!-- 		Current Privileges of -->
	<%-- 		<%=request.getParameter("uname")%></h3> --%>

	<%
		dbOperations dbObj = new dbOperations();
		MetaData metaObj = new MetaData();
		metaObj = dbObj.getMetaData();
		if (metaObj != null) {
	%>
	<div id="dbDiv" style="display: none;" align="center">
		<input type="hidden" name="type" value="1">
		<h2>
			Details of DB privileges for
			<%=request.getParameter("uname")%></h2>
		<table border=2>
			<tr>
				<th>DB</th>
				<th>Username</th>
				<th>Select Privilege</th>
				<th>Alter Privilege</th>
				<th>Update</th>
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
					value="Update"></td>
			</tr>
			</form>
			<%
				}
			%>
		</table>
	</div>
	<div id="dbSelect" style="display: none">
		<center>
			<div id="insertBy">
				<select id="dbDropDown">
					<option value="NONE">Select Database</option>
					<%
						ArrayList<users_db> cust = dbOperations.getuserdetails_db(temp);
							for (int i = 0; i < cust.size(); i++) {
					%>
					<option value="<%=cust.get(i).db%>"><%=cust.get(i).db%></option>
					<%
						}
					%>
				</select>
			</div>
		</center>
	</div>
	
	<div>
		<input type = "hidden" id = "meraNaamDB">
		
	
	
	</div>
	
	<div id="tabDiv" style="display: none;" align="center">
		<h2>
			Details of table privileges for
			<%=request.getParameter("uname")%></h2>

		<table border=2>
			<tr>
				<th>DB</th>
				<th>Table Name</th>
				<th>Table Privilege</th>
				<th>Column Privilege</th>
				<th>Time</th>
			</tr>

			<tr>
				<%
					ArrayList<users_tbprev> cus1 = dbOperations.getuserdetails_tbprev(request.getParameter("uname"));
				%>
<%-- 				<td><%=cus1.get(i).db%></td> --%>
<%-- 				<td><%=cus1.table_name%></td> --%>
<%-- 				<td><%=cus1.table_prev%></td> --%>
<%-- 				<td><%=cus1.column_prev%></td> --%>
<%-- 				<td><%=cus1.timestamp%></td> --%>
		</table>
	</div>
	<div id="userDiv" style="display: none;" align="center">
		<h2>
			Details of user
			<%=request.getParameter("uname")%></h2>

		<table border=2>
			<tr>
				<th>Username</th>
				<th>Execute Privilege</th>
			</tr>



			<%
				users cus2 = dbOperations.getuserdetails(request
							.getParameter("uname"));
			%>
			<form action="updateUserTable" method="post">
				<tr>
					<td><%=cus2.uname%></td>
					<td><input type="text" name="execPriv"
						value="<%=cus2.execute_prev%>"></td>
					<td><input type="submit" name="userTableUpdateButton"
						value="Update"></td>
				</tr>
			</form>
		</table>
	</div>
	<div id="colDiv" style="display: none;">
		<h2>
			Details of Column privileges for
			<%=request.getParameter("uname")%>
		</h2>

		<table border=2>


			<tr>
				<th>DB</th>
				<th>Table Name</th>
				<th>Column Name</th>
				<th>Column Privilege</th>
				<th>Time</th>
			</tr>

			<tr>

				<%
					users_clprev cus3 = dbOperations.getuserdetails_clprev(request
								.getParameter("uname"));
				%>

				<td><%=cus3.uname%></td>
				<td><%=cus3.table_name%></td>
				<td><%=cus3.column_name%></td>
				<td><%=cus3.column_prev%></td>
				<td><%=cus3.timestamp%></td>
		</table>
		<%
			}
		%>





	</div>


	<center>
		<div id="msg">${message}</div>
	</center>
</body>
</html>