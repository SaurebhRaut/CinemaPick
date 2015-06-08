<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>
	function check() {
		var dropdown = document.getElementById("selectSearchDown");
		var current_value = dropdown.options[dropdown.selectedIndex].value;
		if (current_value == "Star") {
			document.getElementById("insertStarDiv").style.display = "block";

			document.getElementById("insertCustomerDiv").style.display = "none";
		} else if (current_value == "Customer") {
			document.getElementById("insertCustomerDiv").style.display = "block";

			document.getElementById("insertStarDiv").style.display = "none";
		}
	}
</script>
</head>
<body>

	<div id="nav">
		<a href="Menu.jsp">HOME</a> <a href="Login.jsp">Logout</a>
	</div>
	<br>
	<center>
		<div id="insertBy">
			<select id="selectSearchDown" onchange="check();">
				<option value="0">Select a insert option!</option>
				<option value="Star">Star</option>
				<option value="Customer">Customer</option>
			</select>
		</div>
	</center>
	<br>
	<div id="insertStarDiv" style="display: none;">
		<form action="insert">
			<input type="hidden" name="type" value="1">
			<center>
				<table border="2">
					<tr>
						<td>Input Star First Name:</td>
						<td><input type="text" name="star_first_name"></td>
					</tr>
					<tr>
						<td>Input Star Last Name:</td>
						<td><input type="text" name="star_last_name"></td>
					</tr>
					<tr>
						<td>Input Star DOB:</td>
						<td><input type="date" name="dob_star"></td>
					</tr>
					<tr>
						<td>Input Star Photo URL:</td>
						<td><input type="text" name="star_url"></td>
					</tr>
					<tr>
						<!-- 						<td style="text-align: center;"><input type="button" -->
						<!-- 							class="btn" value="Cancel" -->
						<!-- 							onclick="togglediv('addNewGalleryForm'); overlayClose();"></td> -->
						<td colspan="2" style="text-align: center;"><input
							type="submit" class="btn" value="Submit"></td>
					</tr>
				</table>
			</center>
		</form>
	</div>

	<div id="insertCustomerDiv" style="display: none;">
		<form action="insert">
			<input type="hidden" name="type" value="2">
			<center>
				<table border="2">
					<tr>
						<td>Input Customer First Name:</td>
						<td><input type="text" name="custom_first_name"></td>
					</tr>
					<tr>
						<td>Input Customer Last Name:</td>
						<td><input type="text" name="custom_last_name"></td>
					</tr>
					<tr>
						<td>Input Customer Address:</td>
						<td><input type="text" name="custom_address"></td>
					</tr>
					<tr>
						<td>Input Customer Email:</td>
						<td><input type="text" name="custom_email"></td>
					</tr>
					<tr>
						<td>Input Customer Password:</td>
						<td><input type="password" name="custom_pass"></td>
					</tr>
					<tr>
						<!-- 						<td style="text-align: center;"><input type="button" -->
						<!-- 							class="btn" value="Cancel" -->
						<!-- 							onclick="togglediv('addNewGalleryForm'); overlayClose();"></td> -->
						<td colspan="2" style="text-align: center;"><input
							type="submit" class="btn" value="Submit"></td>
					</tr>
				</table>
			</center>
		</form>
	</div>

	<center>
		<div id="msg">${message}</div>
	</center>
</body>
</html>