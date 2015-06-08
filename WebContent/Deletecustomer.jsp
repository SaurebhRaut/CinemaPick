<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*" %>

<%@ page import="BO.Customer"%> 
<%@ page import="DB.DBMethods"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>

	<div id="nav">
		<a href="Menu.jsp">HOME</a>
		<a href="Login.jsp">Logout</a>
	</div>
	<% 	try {
		 
				int i=0;
				ArrayList<Customer> result_Customer = new ArrayList<Customer>();
				result_Customer = DBMethods.customer_List();
				out.println("<form action=Deletion method=post>");
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
				Customer currcustomer = new Customer();
				currcustomer = result_Customer.get(i);
				while (currcustomer!= null) {
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
					if(i==result_Customer.size()){
						break;
					}
					currcustomer = result_Customer.get(i);
				}
				
				out.println("<tr>");
				out.println("<td><input type=submit value=Delete></td>");
// 				out.println("<td><a href=Login.jsp> Log out </td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</form>");
				
				
		
	}
	catch(Exception e)
	{
			out.println(e.toString());
	} %>	


</body>
</html>