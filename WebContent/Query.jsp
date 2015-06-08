<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div id="nav">
		<a href="Menu.jsp">HOME</a>
		<a href="Login.jsp">Logout</a>
	</div>
	<!-- 	<form action="Print.jsp" method="post" enctype="multipart/form-data"> -->
	<table align="center">
		<tr>
			<td>Enter a query</td>
			<td><input type="text" name="fname" id="query"
				placeholder="Enter your query.">
			</td>
		</tr>
		<tr>
			<td><input type="submit" value="Submit" onClick=myfunct()>
			</td>
		</tr>
	</table>
	<!-- 	</form> -->
	<br>
<!-- 	<table align="center"> -->
<!-- 		<tr> -->
<!-- 			<td><a href="displayMetaData.jsp">Click here for Metadata</a> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 	</table> -->
	<table align="center">
		<tr>
			<td>First Name</td>
			<td><input type="text" name="fname" id="fname"
				placeholder="Enter first name.">
			</td>
		</tr>
		<tr>
			<td>Last Name</td>
			<td><input type="text" name="lname" id="lname"
				placeholder="Enter last name.">
			</td>
		</tr>
		<tr>
			<td>ID</td>
			<td><input type="text" name="id" id="id"
				placeholder="Enter id.">
			</td>
		</tr>
		<tr>
			<td><input type="submit" value="Submit" onClick=myfunct1()>
			</td>
		</tr>
	</table>
	<script type="text/javascript">
		function myfunct() {
			var str = document.getElementById("query").value;
			window.location = "Print.jsp?type=1&query=" + str;
		}
		function myfunct1() {
			var f = document.getElementById("fname").value;
			var l = document.getElementById("lname").value;
			var i = document.getElementById("id").value;
			window.location = "Print.jsp?type=2&fname=" + f+"&lname="+l+"&id="+i;
		}
	</script>
</body>
</html>