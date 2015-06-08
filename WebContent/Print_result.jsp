<%@page import="DB.DBUtil"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>
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
				<li><a href="homeUser.jsp"><span
						class="glyphicon glyphicon-arrow-left"></span> Back</a>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="homeUser.jsp"><span
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
		String type = request.getParameter("type");
		//out.println(str);
	%>
	<%
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			out.println("can't load mysql driver");
			out.println(e.toString());
		}
	%>
	<%
		try {
			String fName = request.getParameter("user");
			String pwd = request.getParameter("pwd");
			String query = request.getParameter("query");
			String lower_query = query.toLowerCase();

			DBUtil db = new DBUtil();

			Connection con = db.getconnection(fName, pwd);

			Statement stmt = null;
			PreparedStatement pstmt;
			ResultSet rs;
			ResultSetMetaData metadata;

			stmt = con.createStatement(); // Statements allow to issue SQL queries to the database
			if (lower_query.charAt(0) == 's') {

				rs = stmt.executeQuery(query); // Result set get the result of the SQL query
				metadata = rs.getMetaData();
				ArrayList<String> col = new ArrayList<String>();
	%>
	<div style="margin-top: 50px; width: 290px; margin-left: 540px">
		<h3 align="center">
			<B><I>Query Results</I> </B>
		</h3>
		<table align="center" class="table table-striped">
			<thead>
				<tr>
					<%
						for (int i = 1; i <= metadata.getColumnCount(); i++) {
					%>
					<th><%=metadata.getColumnName(i)%></th>
					<%
						col.add(metadata.getColumnName(i));
								}
					%>
				</tr>
			</thead>
			<%
				int i = 0;
						while (rs.next()) {
							i = 0;
			%>
			<tr>
				<%
					while (i < metadata.getColumnCount()) {
				%>

				<td><%=rs.getString(col.get(i++))%></td>
				<%
					}
				%>
			</tr>
			<%
				}
			%>
		</table>
		<%
			} else {
					int rows = stmt.executeUpdate(query);
		%>
		<h3 align="center" style="margin-top: 150px">
			<B><I>Number of rows affected: <%=rows %></I> </B>
		</h3>

		<%
			}
				con.close();
			} catch (Exception e) {
		%>
		<h3 align="center" style="margin-top: 150px">
			<B><I>Error Occurred! Please re-try.</I> </B>
		</h3>
		<%
			}
		%>
	
</body>
</html>