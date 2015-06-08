<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page import="DB.DBUtil"%>
<title>Insert title here</title>
</head>
<body>

	<div id="nav">
		<a href="Menu.jsp">HOME</a>
		<a href="Login.jsp">Logout</a>
	</div>
	<%
		try {
			DBUtil db = new DBUtil();
			Connection con = db.getconnection();
			ArrayList<String> listOfTables = new ArrayList<String>();
			out.println("<table align=center>");

			// Create an execute an SQL statement to select all of table"Stars" records
			Statement select = con.createStatement();
			ResultSet rs = select.executeQuery("show tables");

			while (rs.next()) {
				listOfTables.add(rs.getString("Tables_in_moviedb"));
			}
			ResultSetMetaData metadata;
			for (String a : listOfTables) {
				rs = select.executeQuery("select * from " + a);
				metadata = rs.getMetaData();

				out.println("<tr>");
				out.println("<td align=center><b><i>" + a + "</i></b></td>");
				out.println("</tr>");
				//out.println("<td>" + metadata.getColumnCount() + "</td>");
				//out.println("<td>");
				for (int i = 1; i <= metadata.getColumnCount(); i++) {
					out.println("<tr>");
					out.println("<td>" + metadata.getColumnName(i)
							+ "</td>");
					out.println("<td>" + metadata.getColumnTypeName(i)
							+ "</td>");
					out.println("</tr>");

				}
				out.println("<tr>");
				out.println("</tr>");

			}
		} catch (Exception e) {
			out.println(e.toString());
		}
	%>
</body>
</html>