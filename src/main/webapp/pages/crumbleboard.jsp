<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crumbleboaed | Crumblr</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link href='//fonts.googleapis.com/css?family=Raleway:400,300,600' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="../css/normalize.css">
<link rel="stylesheet" href="../css/skeleton.css">
<link rel="stylesheet" href="../css/custom.css">

</head>
<body>

<div class ="container">
	<div class="row">
		<div class="twelve columns">
		<h1>User's Crumblr</h1>
		</div>
	</div>
 	<div class="row">
 		<div class="four columns">
 			<p>[Username Space]</p>
 			<p>Description<p>
 			<form action="/logout" method="post">
				<input type="submit" value="Sign Out"/>
			</form>
 		</div>
 		<div class="eight columns">
 			<form>
 				<input type="file" id="pictureFile" name="pictureFile">
 				<input type="text" name="blogPost" id="blogPost"> 
 				<input type="submit" value="Post">
 			</form>
 			<hr>
 			<p>Post Spaces</p>
 			<p>Description<p>
 		</div>
 	</div>
</div>

</body>
</html>