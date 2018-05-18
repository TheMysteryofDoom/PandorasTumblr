<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register | Crumblr</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link href='//fonts.googleapis.com/css?family=Raleway:400,300,600' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="../css/normalize.css">
<link rel="stylesheet" href="../css/skeleton.css">
<link rel="stylesheet" href="../css/custom.css">

<script type="text/javascript" src="../javascript/login.js"></script>
</head>
<body class ="grimoire">
<div id="home" class="container">
	<div class="row">
	<br><br><br><br>
		<div class="twelve columns" align=center>
			<h1 class="whiteFont"><b>Register</b></h1>
			<form action="/register" name="newCrumblrUser" method ="post">
			<input type="text" id="firstName" name="firstName" placeholder="First Name" required="required"><br>
			<input type="text" id="username" name="username" placeholder="Username" required="required"><br>
			<input type="email" id="email" name="email" placeholder="E-Mail" required="required"><br>
			<input type="password" id="password" name="password" placeholder="Password" required="required"><br>
			<input type="password" id="passwordR" name="passwordR" placeholder="Repeat Password"><br>
			<input class="buttonOverride01" type="submit" value="Register">
			</form>
		</div>
	</div>
</div>
</body>
</html>