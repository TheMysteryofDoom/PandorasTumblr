<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up | Crumblr</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link href='//fonts.googleapis.com/css?family=Raleway:400,300,600' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="../css/normalize.css">
<link rel="stylesheet" href="../css/skeleton.css">
<link rel="stylesheet" href="../css/custom.css">

<script type="text/javascript" src="../javascript/login.js"></script>
</head>
<body class ="grimoire">
<div class="container">
	<div class="row">
		<form>
			<input type="text" id="search" name="search"> <input type="submit" value="Search">
		</form>
	</div>
</div>
<div id="home" class="container">
	<br><br><br><br>
	<div class="row">
		<div class="twelve columns" align="center">
			<img src="../images/crumblr.png">
			<p class="whiteFont"><b>Let's all play together.<br>
			You'll want to stay forever.</b></p>
			<form action="pages/login.jsp">
			<input class="buttonOverride01" type="submit" value="Log In">
			</form>
			<form action="pages/register.jsp">
			<input class="buttonOverride01" type="submit" value="Register">
			</form>
			<button class="buttonOverride01">Register with Facebook</button>
		</div> 	
	</div>
</div>
</body>
</html>