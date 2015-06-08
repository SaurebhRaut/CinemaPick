<%@page import="DB.dbOperations"%>
<%@page import="BO.MetaData"%>
<%@page import="java.util.*"%>
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
<title>Display Meta Data</title>
</head>
<body>
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div>
			<ul class="nav navbar-nav navbar-right">
			<li><a href="displayMetaData.jsp"><span
						class="glyphicon glyphicon-home"></span> Home</a></li>
				<li><a href="displayDetails.jsp"><span
						class="glyphicon glyphicon-user"></span> Show Users</a></li>				
				<li><a href="loginDBA.jsp"><span
						class="glyphicon glyphicon-log-in"></span> Logout</a></li>
			</ul>
		</div>
	</div>
	</nav>
	<div align="right" style="padding:20px">
		<form action = "genReports" method = "post" style="
    float: left;
    margin-left: 70%;
">
			<input type="submit" value = "Generate Reports" class="btn btn-primary">			
		</form>
		<a href="addmovies.jsp"><input type="button" value = "Add Movie" class="btn btn-primary" style="
    margin-right: 10%;
    padding: 5px;
"></a>
	</div>
	<h3 align="center"><B><I>Meta-Data</I></B></h3>
	
		<script type="text/javascript">

			function chh() {
				var firstDropdown = document.getElementById("colDropDown");
				var firstValue = firstDropdown.options[firstDropdown.selectedIndex].value;
				/* var colVal = document.getElementById("col");
				colVal.value = firstValue; */
				window.location.assign("displayMetaData.jsp?dbselect=" + firstValue);
			}
		</script>
	
	<div style="margin-top: 50px; width: 300px; margin-left: 520px">
		
		<select id="colDropDown" onchange="chh();" style="margin-left: 25%;margin-bottom: 20px;">
					<option value="NONE">Select Database</option>
					<%
						dbOperations dbObj = new dbOperations();
						ArrayList<String> cust = dbObj.getAllDatabases();
							for (int i = 0; i < cust.size(); i++) {
					%>
					<option value="<%=cust.get(i)%>"><%=cust.get(i)%></option>
					<%
						}
					%>
		</select>
		
	
		<%
			String database = request.getParameter("dbselect");
			if(database== null || database == "")
			{
				%>
				<h4 style="
    margin-left: 22%;
">Please make a selection.</h4>
				
				<%
			}
			else
			{
		%>
		<table align="center" class="table table-striped">
			<%
				MetaData metaObj = new MetaData();
				metaObj = dbObj.iGiveMetaData(database);
				if (metaObj != null) {
					for (Map.Entry<String, ArrayList<String>> entry : metaObj.metaData
							.entrySet()) {
			%>
			<tr>
				<%
					String tableName = entry.getKey();
				%>

				<td colspan="2" align="center"><B><I><%=tableName%></I> </B>
				</td>

			</tr>
			<%
				ArrayList<String> valueList = entry.getValue();
						for (int j = 0; j < valueList.size() - 1; j++) {
			%>
			<tr>
				<td><%=valueList.get(j)%></td>
				<td><%=valueList.get(j + 1)%></td>
				<%
					j = j + 1;
				%>
				<%-- 				<%j+1; %> --%>
			</tr>
			<%
				}
					}
				}
			}
			%>

		</table>
	</div>
</body>
</html>