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
	<form action=Deletion method=get>
  <table border="1" align="center">
<tr>
<td>User Name</td>
<td><input type="text" name="user_id"></td>
</tr>
<tr>
<td>Password</td>
<td><input type="password" name="password"></td>
</tr>
<tr>
<td align="center" width=50%><input type=submit value=login></td>
</tr>
</table>
</form>
<div>
${Error}
</div>
</body>
</html>