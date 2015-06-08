<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
#ajaxResponse {
	width: 300px;
}

#parstring {
	width: 200px;
}

#AutocompleteTextField {
	left: 92px;
	width: 143px;
}
</style>
</head>
<body
	background="http://hdwallpaperd.com/wp-content/uploads/cool-lion-wallpaper-hd-p.jpg">

	<script>
function clearfunction(id){
	var div = document.getElementById(id);
	div.style.display = "none";
}
function loadXMLDoc(id)
{
	var div = document.getElementById(id);
	div.style.display = "block";
var xmlhttp;
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    document.getElementById("ajaxResponse").innerHTML=xmlhttp.responseText;
    }
  }
  var x=document.getElementById("parstring").value;
  
	xmlhttp.open("GET","autocomplete?parstring="+x,true);
	xmlhttp.send();
}
</script>

	<div id="ajaxdiv" onclick="clearfunction('ajaxResponse')">

		<label>SearchAllMovies</label>
		<form id="myAjaxRequestForm">
			<input id="parstring" type="text" name="PartialString"
				onkeyup=loadXMLDoc('ajaxResponse') />
		</form>
		<div id="ajaxResponse" style="background-color: CornflowerBlue;"></div>
	</div>

</body>
</html>