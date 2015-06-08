<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
					href="displayMetaData.jsp"><span
						class="glyphicon glyphicon-arrow-left"></span> Back</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="displayMetaData.jsp"><span
						class="glyphicon glyphicon-home"></span> Home</a></li>
				<li><a href="loginDBA.jsp"><span
						class="glyphicon glyphicon-log-in"></span> Logout</a></li>
			</ul>
		</div>
	</div>
	</nav>
	<% String reportPath =  request.getAttribute("message").toString();
	if(reportPath != null)
		{
		//System.out.println(reportPath);
	%>
	<div align="center" style="margin-top: 50px; width: 557px; margin-left: 380px">
	<table class="table table-striped">
	<thead>
	<tr>
	<td style="text-align: center;"><B><I>The report is generated at</I></B></td>
	</tr>
	</thead>
	<tbody>
	<tr>
	<td><%=reportPath%></td>
	</tr>
	</tbody>
	</table>
			
	</div>
	<%} %>
</body>
</html>