<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crumbleboard | Crumblr</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link href='//fonts.googleapis.com/css?family=Raleway:400,300,600' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="../css/normalize.css">
<link rel="stylesheet" href="../css/skeleton.css">
<link rel="stylesheet" href="../css/custom.css">
<%@ include file="../javascript/SessionCheck2.jsp" %>
</head>
<body>

<div class ="container">
	<div class="row">
		<div class="twelve columns">
		<h1><%= session.getAttribute("username") %>'s Crumblr</h1>
		</div>
	</div>
 	<div class="row">
 		<div class="four columns">
 			<p><%= session.getAttribute("username") %></p>
 			<p>Description<p>
 			<form action="/logout" method="post">
				<input type="submit" value="Sign Out"/>
			</form>
 		</div>
 		<div class="eight columns">
 			<form action ="/crumblrPost" modelAttribute="CrumblrPost" enctype="multipart/form-data" method="post">
 				<input type="hidden" id ="owner" name="owner" value="<%= session.getAttribute("username") %>">
 				<input type="file" id="file" name="file">
 				<input type="text" name="content" id="content"> 
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