<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8" />
<style>
html,body,div,span,object,iframe,h1,h2,h3,h4,h5,h6,p,blockquote,pre,abbr,address,cite,code,del,dfn,em,img,ins,kbd,q,samp,small,strong,sub,sup,var,b,i,dl,dt,dd,ol,ul,li,fieldset,form,label,legend,table,caption,tbody,tfoot,thead,tr,th,td,article,aside,canvas,details,figcaption,figure,footer,header,hgroup,menu,nav,section,summary,time,mark,audio,video
	{
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	font: inherit;
	vertical-align: baseline;
}

article,aside,details,figcaption,figure,footer,header,hgroup,menu,nav,section
	{
	display: block;
}

blockquote,q {
	quotes: none;
}

blockquote:before,blockquote:after,q:before,q:after {
	content: '';
	content: none;
}

ins {
	background-color: #ff9;
	color: #000;
	text-decoration: none;
}

mark {
	background-color: #ff9;
	color: #000;
	font-style: italic;
	font-weight: bold;
}

del {
	text-decoration: line-through;
}

abbr[title],dfn[title] {
	border-bottom: 1px dotted;
	cursor: help;
}

table {
	border-collapse: collapse;
	border-spacing: 0;
}

hr {
	display: block;
	height: 1px;
	border: 0;
	border-top: 1px solid #ccc;
	margin: 1em 0;
	padding: 0;
}

input,select {
	vertical-align: middle;
}

body {
	font: 12px/18px Lucida Sans, sans-serif;
}

select,input,textarea,button {
	font: 99% sans-serif;
}

pre,code,kbd,samp {
	font-family: monospace, sans-serif;
}

a:hover,a:active,*:focus,input:focus,button:focus {
	outline: none;
}

ul,ol {
	margin-left: 2em;
}

ol {
	list-style-type: decimal;
}

nav ul,nav li {
	margin: 0;
	list-style: none;
	list-style-image: none;
}

small {
	font-size: 85%;
}

strong,th {
	font-weight: bold;
}

td {
	vertical-align: top;
}

sub,sup {
	font-size: 75%;
	line-height: 0;
	position: relative;
}

sup {
	top: -0.5em;
}

sub {
	bottom: -0.25em;
}

pre {
	white-space: pre;
	white-space: pre-wrap;
	word-wrap: break-word;
	padding: 15px;
}

textarea {
	overflow: auto;
}

.ie6 legend,.ie7 legend {
	margin-left: -7px;
}

input[type="radio"] {
	vertical-align: text-bottom;
}

input[type="checkbox"] {
	vertical-align: bottom;
}

.ie7 input[type="checkbox"] {
	vertical-align: baseline;
}

.ie6 input {
	vertical-align: text-bottom;
}

label,input[type="button"],input[type="submit"],input[type="image"],button
	{
	cursor: pointer;
}

button,input,select,textarea {
	margin: 0;
}

input:valid,textarea:valid {
	
}

input:invalid,textarea:invalid {
	border-radius: 1px;
	-moz-box-shadow: 0px 0px 5px red;
	-webkit-box-shadow: 0px 0px 5px red;
	box-shadow: 0px 0px 5px red;
}

.no-boxshadow input:invalid,.no-boxshadow textarea:invalid {
	background-color: #f0dddd;
}

::-moz-selection {
	background: #a1d8f0;
	color: #fff;
	text-shadow: none;
}

::selection {
	background: #a1d8f0;
	color: #fff;
	text-shadow: none;
}

a:link {
	-webkit-tap-highlight-color: #a1d8f0;
}

button {
	width: auto;
	overflow: visible;
}

.ie7 img {
	-ms-interpolation-mode: bicubic;
}

body,select,input,textarea {
	color: #444;
}

a,a:active,a:visited {
	color: #666;
	text-decoration: none;
}

a:hover {
	color: #c00;
}

.ir {
	display: block;
	text-indent: -999em;
	overflow: hidden;
	background-repeat: no-repeat;
	text-align: left;
	direction: ltr;
}

.hidden {
	display: none;
	visibility: hidden;
}

.visuallyhidden {
	border: 0;
	clip: rect(0, 0, 0, 0);
	height: 1px;
	margin: -1px;
	overflow: hidden;
	padding: 0;
	position: absolute;
	width: 1px;
}

.visuallyhidden.focusable:active,.visuallyhidden.focusable:focus {
	clip: auto;
	height: auto;
	margin: 0;
	overflow: visible;
	position: static;
	width: auto;
}

.invisible {
	visibility: hidden;
}

.clearfix:before,.clearfix:after {
	content: "\0020";
	display: block;
	height: 0;
	overflow: hidden;
}

.clearfix:after {
	clear: both;
}

.clearfix {
	zoom: 1;
}

@media all and (orientation:portrait) {
}

@media all and (orientation:landscape) {
}

@media screen and (max-device-width: 480px) {
	/* html { -webkit-text-size-adjust:none; -ms-text-size-adjust:none; } */
}

@media print {
	* {
		background: transparent !important;
		color: black !important;
		text-shadow: none !important;
		filter: none !important;
		-ms-filter: none !important;
	}
	a,a:visited {
		color: #444 !important;
		text-decoration: underline;
	}
	a[href]:after {
		content: " (" attr(href) ")";
	}
	abbr[title]:after {
		content: " (" attr(title) ")";
	}
	.ir a:after,a[href ^="javascript:"]:after,a[href ^="#"]:after {
		content: "";
	}
	pre,blockquote {
		border: 1px solid #999;
		page-break-inside: avoid;
	}
	thead {
		display: table-header-group;
	}
	tr,img {
		page-break-inside: avoid;
	}
	@page {
		margin: 0.5cm;
	}
	p,h2,h3 {
		orphans: 3;
		widows: 3;
	}
	h2,h3 {
		page-break-after: avoid;
	}
}

body {
	background: #eff3f6;
}

.box {
	background: #fefefe;
	border: 1px solid #C3D4DB;
	border-top: 1px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-moz-box-shadow: rgba(0, 0, 0, 0.15) 0 0 1px;
	-webkit-box-shadow: rgba(0, 0, 0, 0.15) 0 0 1px;
	box-shadow: rgba(0, 0, 0, 0.15) 0 0 1px;
	color: #444;
	font: normal 12px/14px Arial, Helvetica, Sans-serif;
	margin: 0 auto 30px;
	overflow: hidden;
}

.box.login {
	height: 260px;
	width: 332px;
	position: absolute;
	left: 50%;
	top: 50%;
	margin: -130px 0 0 -166px;
}

.boxBody {
	background: #fefefe;
	border-top: 1px solid #dde0e8;
	border-bottom: 1px solid #dde0e8;
	padding: 10px 20px;
}

.box footer {
	background: #eff4f6;
	border-top: 1px solid #fff;
	padding: 22px 26px;
	overflow: hidden;
	height: 32px;
}

.box label {
	display: block;
	font: 14px/22px Arial, Helvetica, Sans-serif;
	margin: 10px 0 0 6px;
}

.box footer label {
	float: left;
	margin: 4px 0 0;
}

.box footer input[type=checkbox] {
	vertical-align: sub;
	*vertical-align: middle;
	margin-right: 10px;
}

.box input[type=text],.box input[type=password],.txtField,.cjComboBox {
	border: 6px solid #F7F9FA;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
	-moz-box-shadow: 2px 3px 3px rgba(0, 0, 0, 0.06) inset, 0 0 1px #95a2a7
		inset;
	-webkit-box-shadow: 2px 3px 3px rgba(0, 0, 0, 0.06) inset, 0 0 1px
		#95a2a7 inset;
	box-shadow: 2px 3px 3px rgba(0, 0, 0, 0.06) inset, 0 0 1px #95a2a7 inset;
	margin: 3px 0 4px;
	padding: 8px 6px;
	width: 270px;
	display: block;
}

.box input[type=text]:focus,.box input[type=password]:focus,.txtField:focus,.cjComboBox:focus
	{
	border: 6px solid #f0f7fc;
	-moz-box-shadow: 2px 3px 3px rgba(0, 0, 0, 0.04) inset, 0 0 1px #0d6db6
		inset;
	-webkit-box-shadow: 2px 3px 3px rgba(0, 0, 0, 0.04) inset, 0 0 1px
		#0d6db6 inset;
	box-shadow: 2px 3px 3px rgba(0, 0, 0, 0.04) inset, 0 0 1px #0d6db6 inset;
	color: #333;
}

.cjComboBox {
	width: 294px;
}

.cjComboBox.small {
	padding: 3px 2px 3px 6px;
	width: 100px;
	border-width: 3px !important;
}

.txtField.small {
	padding: 3px 6px;
	width: 200px;
	border-width: 3px !important;
}

.rLink {
	padding: 0 6px 0 0;
	font-size: 11px;
	float: right;
}

.box a {
	color: #999;
}

.box a:hover,.box a:focus {
	text-decoration: underline;
}

.box a:active {
	color: #f84747;
}

.btnLogin {
	-moz-border-radius: 2px;
	-webkit-border-radius: 2px;
	border-radius: 15px;
	background: #a1d8f0;
	background: -moz-linear-gradient(top, #badff3, #7acbed);
	background: -webkit-gradient(linear, left top, left bottom, from(#badff3),
		to(#7acbed) );
	-ms-filter:
		"progid:DXImageTransform.Microsoft.gradient(startColorStr='#badff3', EndColorStr='#7acbed')";
	border: 1px solid #7db0cc !important;
	cursor: pointer;
	padding: 11px 16px;
	font: bold 11px/14px Verdana, Tahomma, Geneva;
	text-shadow: rgba(0, 0, 0, 0.2) 0 1px 0px;
	color: #fff;
	-moz-box-shadow: inset rgba(255, 255, 255, 0.6) 0 1px 1px,
		rgba(0, 0, 0, 0.1) 0 1px 1px;
	-webkit-box-shadow: inset rgba(255, 255, 255, 0.6) 0 1px 1px,
		rgba(0, 0, 0, 0.1) 0 1px 1px;
	box-shadow: inset rgba(255, 255, 255, 0.6) 0 1px 1px, rgba(0, 0, 0, 0.1)
		0 1px 1px;
	margin-left: 12px;
	float: right;
	padding: 7px 21px;
}

.btnLogin:hover,.btnLogin:focus,.btnLogin:active {
	background: #a1d8f0;
	background: -moz-linear-gradient(top, #7acbed, #badff3);
	background: -webkit-gradient(linear, left top, left bottom, from(#7acbed),
		to(#badff3) );
	-ms-filter:
		"progid:DXImageTransform.Microsoft.gradient(startColorStr='#7acbed', EndColorStr='#badff3')";
}

.btnLogin:active {
	text-shadow: rgba(0, 0, 0, 0.3) 0 -1px 0px;
}

footer#main {
	position: fixed;
	left: 0;
	bottom: 10px;
	text-align: center;
	font: normal 11px/16px Arial, Helvetica, sans-serif;
	width: 100%;
}
</style>
</head>

<body>
	<div>
		<form class="box login" action="loginServletDBA" method="post">
			<fieldset class="boxBody">
				<label>Username</label> <input type="text" name="user" tabindex="1"
					required> <label><a href="#" class="rLink"
					tabindex="5">Forget your password?</a>Password</label> <input
					type="password" name="pwd" tabindex="2" required>
			</fieldset>
			<footer>
				<h3 style="color: red; text-align: center">${msg}</h3>
				<center>
					<input type="submit" class="btnLogin" value="Login" tabindex="4"
						style="margin: 0%; margin-right: 33%;">
				</center>
			</footer>

		</form>
	</div>
	<div style="margin-top:500px" align="center">
		<h3><a href="login.jsp">Not a DBA? Customer Login</a></h3>
	</div>

</body>
</html>
