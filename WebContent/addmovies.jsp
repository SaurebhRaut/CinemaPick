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
<title>Insert title here</title>
<script>
	function myFunction() {
		var content = '${error}';
		if (content == "Movie uploaded and saved into database") {
			alert("Movie was successfully added");
			window.location.assign("displayMetaData.jsp");
		}
		if (content == "Error!! movie not added")
			alert("Error in running the stored procedure!");
	}
</script>
</head>
<body onload=myFunction()>
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
	<div align="center" style="width: 320px; margin-left: 39%;">
	<h3>Enter movie details</h3>
	<form action="AddMovie" method="post">
		<table class="table table-striped" align="center">
			<tr>
				<td><label>Title</label></td>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<td><label>Year</label></td>
				<td><input type="text" name="year"></td>
			</tr>
			<tr>
				<td><label>director</label></td>
				<td><input type="text" name="director"></td>
			</tr>
			<tr>
				<td><label>banner_url</label></td>
				<td><input type="text" name="banner_url"></td>
			</tr>
			<tr>
				<td><label>Trailer_url</label></td>
				<td><input type="text" name="trailer_url"></td>
			</tr>
			<tr>
				<td><label>genre</label></td>
				<td><input type="text" name="genres"></td>
			</tr>
			<tr>
				<td><label>Star_fname</label></td>
				<td><input type="text" name="star_fname"></td>
			</tr>
			<tr>
				<td><label>Star_lname</label></td>
				<td><input type="text" name="star_lname"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Submit" class="btn btn-primary"></td>
			</tr>

		</table>
	</form>
	</div>
</body>
</html>