<%@page import="BO.users"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="DB.dbOperations"%>
<%@page import="BO.MetaData"%>
<%@page import="BO.users_db"%>
<%@page import="BO.users_tbprev"%>
<%@page import="BO.users_clprev"%>
<%@page import="BO.users_procPrev"%>
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
// 		String table = request.getParameter("table");
		dbOperations dbObj = new dbOperations();
		MetaData metaObj = new MetaData();
		metaObj = dbObj.getMetaData();
		if (metaObj != null) {
	%>
	<div id="tableSelect">

		<script type="text/javascript">
			function chh()
			{
				document.getElementById("privType").style.display = "block";
			}
		
			function chh1()
			{
				document.getElementById("subButton").style.display = "block";
				var firstDropdown = document.getElementById("procDropDown");
				var firstValue = firstDropdown.options[firstDropdown.selectedIndex].value;
				var colVal = document.getElementById("proc");
				colVal.value = firstValue;
				
				var secondDropdown = document.getElementById("privDropDown");
				var secondValue = secondDropdown.options[secondDropdown.selectedIndex].value;
				var privVal = document.getElementById("priv");
				privVal.value = secondValue;
			}
			
			
	</script>
		<center>
			<div id="insertBy">
				<select id="procDropDown" onchange="chh();">
					<option value="NONE">Select Stored Procedures</option>
					<%
						ArrayList<String> cust = dbObj.getStoredProc(db);
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

	<div id="privType" style="display: none; padding:10px;" align="center">
		<select id="privDropDown" onchange="chh1();">
			<option value="none">Select Privilege</option>
			<option value="execute">Execute</option>
		</select>
	</div>

	<div id="subButton" style="display: none;" align="center">
		<form action="updateProcedurePriv" method="post">
			<input type="submit" value="Add Privilege"> 
<%-- 			<input type="hidden" id="table" name="table" value=<%=table %>>  --%>
				<input type="hidden" id="priv" name="priv"> 
				<input type="hidden" id="proc" name="proc">
				<input type="hidden" id="db" name="db" value="<%=db%>"> 
				<input type="hidden" id="uname" name="uname" value="<%=uname%>">
		</form>
		
	</div>

	
		<%
			ArrayList<users_procPrev> cus1 = dbObj.getuserdetails_procPrev(request.getParameter("uname"));
				if (cus1!=null) {
		%>
		<h2 align="center">
			Table privileges for
			<%=request.getParameter("uname")%></h2>
			<div id="tabDiv" align="center" style="margin-top: 50px; width: 500px; margin-left: 170px">
		<table class="table table-striped">
			<tr>
				<th>DB</th>
				<th>Routine Name</th>
				<th>Routine Type</th>
				<th>Procedure Privilege</th>
				<th>Time</th>
			</tr>
			<%
				for (int i = 0; i < cus1.size(); i++) {
			%>
			<form action="revokeProcedurePriv" method="Post">
				<tr>
					<td><input readonly type="text" name="db"
						value="<%=cus1.get(i).db%>"></td>
					<td><input readonly type="text" name="routine"
						value="<%=cus1.get(i).routine_name%>"></td>
					<td><input readonly type="text" name="type"
						value="<%=cus1.get(i).routine_type%>"></td>
						<td><input readonly type="text" name="procPriv"
						value="<%=cus1.get(i).proc_prev%>"></td>
					<td><input readonly type="text" name="date"
						value="<%=cus1.get(i).timestamp%>"></td>

					<td><input type="submit" value="Revoke" name="revokeButton" class="btn btn-primary">
					</td>
					<td><input type="hidden" name="uname"
						value="<%=request.getParameter("uname")%>"></td>
				</tr>
			</form>
			<%
				}
			%>
		</table>
		<%
			} else {
		%>
		<h3 align="center">
			<B>No stored procedure privileges for <%=request.getParameter("uname")%>!!!</B>
		</h3>
		<%
			}
			}
		%>
	</div>
</body>
</html>