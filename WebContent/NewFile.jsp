<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*"%>
<%@ page import="java.io.*" %>

<%@ page import="BO.Customer"%> 
<%@ page import="DB.DBMethods"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 	
	//String artist_id = request.getParameter("artist_id"); get the Array List;
	ArrayList<Integer> result_movies = new ArrayList<Integer>();
	result_movies = request.getParameter("movies-id");
	int Currposs = Integer.parseInt(getParameter("curr_poss"));
	
%>

<%

ArrayList<Integer> currmovie = ArrayList<Integer>();
for(int i=Currposs, int<= Currposs +10;i++)
{	currmovie.add(i-Currposs,result_movies.get(i));
}

		
out.println("<table border=\"1\" align=center>");
	//out.println("<caption> Gallery List </caption>");
	out.println();
	out.println();
	out.println();
	out.println("<tr>");
	out.println("<th>ID</th>");
	out.println("<th>First_Name</th>");
out.println("<th>Last_Name</th>");
out.println("<th>cc_id</th>");
out.println("<th>address</th>");
out.println("<th>email</th>");
out.println("<th>password</th>");
out.println("<th>Delete</th>");
out.println("</tr>");

while (currmovieentry!= null) {
	out.println("<tr>");
	out.println("<td>"+currcustomer.id+"</td>");
	out.println("<td>"+currcustomer.first_name+"</td>");
	out.println("<td>"+currcustomer.last_name+"</td>");
	out.println("<td>"+currcustomer.cc_id+"</td>");
	out.println("<td>"+currcustomer.address+"</td>");
	out.println("<td>"+currcustomer.email+"</td>");
	out.println("<td>"+currcustomer.password+"</td>"); 
	out.println("<td>"+" <input type=radio name=myradio value="+currcustomer.id+"></td>"); 
	//out.println("<td><input type="+"button"+"value="+"Back"+"onClick=>DeleteCustomerEntry</td>");//delete logic in here;;;;;;;;;;
	out.println("</tr>");
	i++;
	if(i==currmovie.size()){
		break;
	}
	currmovieentry = currmovie.get(i);
}

out.println("<div id=paging>");
out.println("<a href=")
%>
<div id="nav">
		<a href="Menu.jsp">HOME</a>
		<a href="Login.jsp">Logout</a>
	</div>


</body>
</html>