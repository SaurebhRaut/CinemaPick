<%@page import="DB.DBUtil"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div id="nav">

		<a href="Query.jsp">Back</a> <a href="Menu.jsp">HOME</a> <a
			href="Login.jsp">Logout</a>

	</div>
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
			DBUtil db = new DBUtil();
			Connection con = db.getconnection();

			Statement stmt;
			PreparedStatement pstmt;
			ResultSet rs;
			ResultSetMetaData metadata;

			stmt = con.createStatement(); // Statements allow to issue SQL queries to the database
			if (type.equals("1")) {
				String str = request.getParameter("query");
				if (Character.toLowerCase(str.charAt(0)) == 's') {
					rs = stmt.executeQuery(str); // Result set get the result of the SQL query
					metadata = rs.getMetaData();
					ArrayList<String> col = new ArrayList<String>();
					out.println("<table border=\"1\" align=center>");
					for (int i = 1; i <= metadata.getColumnCount(); i++) {
						out.println("<td><b><i>"
								+ metadata.getColumnName(i)
								+ "</i></b></td>");
						col.add(metadata.getColumnName(i));
						// 					out.println("<td>" + metadata.getColumnTypeName(i)
						// 							+ "</td>");
					}
					int i = 0;
					while (rs.next()) {
						i = 0;
						out.println("<tr>");
						while (i < metadata.getColumnCount()) {
							out.println("<td>" + rs.getString(col.get(i++))
									+ "</td>");
						}
						out.println("</tr>");
					}
					out.println("</table>");
				} else {
					int rows = stmt.executeUpdate(str);
					out.println("Number of rows affected: " + rows);
				}
			} else {
				String first = request.getParameter("fname");
				String last = request.getParameter("lname");
				String Id = request.getParameter("id");
				if (Id.isEmpty()) {
					if (!first.isEmpty() && !last.isEmpty()) {
						String sql = "SELECT id FROM stars where first_name=? and last_name=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, first);
						pstmt.setString(2, last);

						rs = pstmt.executeQuery();
						int temp = 0;
						while (rs.next()) {
							temp = rs.getInt("id");
						}
						if(temp!=0)
						{
						ArrayList<Integer> list = new ArrayList<Integer>();
						sql = "SELECT * FROM stars_in_movies where star_id="
								+ temp;
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							list.add(rs.getInt("movie_id"));
						}
						out.println("<table border=\"1\" align=center>");
						out.println("<tr>");
						out.println("<th>ID</th>");
						out.println("<th>Title</th>");
						out.println("<th>Year</th>");
						out.println("<th>Director</th>");
						out.println("<th>Banner URL</th>");
						out.println("<th>Trailer URL</th>");
						out.println("</tr>");

						for (int a : list) {
							sql = "SELECT * FROM movies where id=" + a;
							rs = stmt.executeQuery(sql);
							while (rs.next()) {
								out.println("<tr>");
								out.println("<td>" + rs.getInt("id")
										+ "</td>");
								out.println("<td>" + rs.getString("title")
										+ "</td>");
								out.println("<td>" + rs.getString("year")
										+ "</td>");
								out.println("<td>"
										+ rs.getString("director")
										+ "</td>");
								out.println("<td>"
										+ rs.getString("banner_url")
										+ "</td>");
								out.println("<td>"
										+ rs.getString("trailer_url")
										+ "</td>");
								out.println("</tr>");
							}
						}
						}
						else{
							out.print("<h3>No Data Found!</h3>");
						}
					} else if (!first.isEmpty()) {
						String sql = "SELECT id FROM stars where first_name=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, first);
						rs = pstmt.executeQuery();
						int temp = 0;
						while (rs.next()) {
							temp = rs.getInt("id");
						}

						if (temp != 0) {
							ArrayList<Integer> list = new ArrayList<Integer>();
							sql = "SELECT * FROM stars_in_movies where star_id="
									+ temp;
							rs = stmt.executeQuery(sql);
							while (rs.next()) {
								list.add(rs.getInt("movie_id"));
							}
							out.println("<table border=\"1\" align=center>");
							out.println("<tr>");
							out.println("<th>ID</th>");
							out.println("<th>Title</th>");
							out.println("<th>Year</th>");
							out.println("<th>Director</th>");
							out.println("<th>Banner URL</th>");
							out.println("<th>Trailer URL</th>");
							out.println("</tr>");

							for (int a : list) {
								sql = "SELECT * FROM movies where id=" + a;
								rs = stmt.executeQuery(sql);
								while (rs.next()) {
									out.println("<tr>");
									out.println("<td>" + rs.getInt("id")
											+ "</td>");
									out.println("<td>"
											+ rs.getString("title")
											+ "</td>");
									out.println("<td>"
											+ rs.getString("year")
											+ "</td>");
									out.println("<td>"
											+ rs.getString("director")
											+ "</td>");
									out.println("<td>"
											+ rs.getString("banner_url")
											+ "</td>");
									out.println("<td>"
											+ rs.getString("trailer_url")
											+ "</td>");
									out.println("</tr>");
								}
							}
						} else {
							out.print("<h3>No Data Found!</h3>");
						}
					} else {
						String sql = "SELECT id FROM stars where last_name=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, last);
						rs = pstmt.executeQuery();
						int temp = 0;
						while (rs.next()) {
							temp = rs.getInt("id");
						}

						if (temp != 0) {
							ArrayList<Integer> list = new ArrayList<Integer>();
							sql = "SELECT * FROM stars_in_movies where star_id="
									+ temp;
							rs = stmt.executeQuery(sql);
							while (rs.next()) {
								list.add(rs.getInt("movie_id"));
							}
							out.println("<table border=\"1\" align=center>");
							out.println("<tr>");
							out.println("<th>ID</th>");
							out.println("<th>Title</th>");
							out.println("<th>Year</th>");
							out.println("<th>Director</th>");
							out.println("<th>Banner URL</th>");
							out.println("<th>Trailer URL</th>");
							out.println("</tr>");

							for (int a : list) {
								sql = "SELECT * FROM movies where id=" + a;
								rs = stmt.executeQuery(sql);
								while (rs.next()) {
									out.println("<tr>");
									out.println("<td>" + rs.getInt("id")
											+ "</td>");
									out.println("<td>"
											+ rs.getString("title")
											+ "</td>");
									out.println("<td>"
											+ rs.getString("year")
											+ "</td>");
									out.println("<td>"
											+ rs.getString("director")
											+ "</td>");
									out.println("<td>"
											+ rs.getString("banner_url")
											+ "</td>");
									out.println("<td>"
											+ rs.getString("trailer_url")
											+ "</td>");
									out.println("</tr>");
								}
							}
						} else {
							out.print("<h3>No Data Found!</h3>");
						}
					}
				}
				else {
					ArrayList<Integer> list = new ArrayList<Integer>();
					String sql = "SELECT * FROM stars_in_movies where star_id="
							+ Id;
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						list.add(rs.getInt("movie_id"));
					}
					
					if (list.size()>0){
						out.println("<table border=\"1\" align=center>");
						out.println("<tr>");
						out.println("<th>ID</th>");
						out.println("<th>Title</th>");
						out.println("<th>Year</th>");
						out.println("<th>Director</th>");
						out.println("<th>Banner URL</th>");
						out.println("<th>Trailer URL</th>");
						out.println("</tr>");

						for (int a : list) {
							sql = "SELECT * FROM movies where id=" + a;
							rs = stmt.executeQuery(sql);
							while (rs.next()) {
								out.println("<tr>");
								out.println("<td>" + rs.getInt("id") + "</td>");
								out.println("<td>" + rs.getString("title")
										+ "</td>");
								out.println("<td>" + rs.getString("year")
										+ "</td>");
								out.println("<td>" + rs.getString("director")
										+ "</td>");
								out.println("<td>" + rs.getString("banner_url")
										+ "</td>");
								out.println("<td>"
										+ rs.getString("trailer_url") + "</td>");
								out.println("</tr>");
							}
						}
					}
					else {
						out.print("<h3>No Data Found!</h3>");
					}

					
				}
			}

			con.close();
		} catch (Exception e) {
			out.println("Error Occured! Please re-try.");
		}
	%>
</body>
</html>