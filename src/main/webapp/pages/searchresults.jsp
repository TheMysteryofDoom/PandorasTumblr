<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Results | Crumblr</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href='//fonts.googleapis.com/css?family=Raleway:400,300,600' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="../css/normalize.css">
<link rel="stylesheet" href="../css/skeleton.css">
<link rel="stylesheet" href="../css/custom.css">
<%@ include file="../javascript/SessionCheck2.jsp" %>
</head>
<body class="deepOrange">
<div class="container deepOrange">
	<div class="row">
		<div class="three columns">
			<form action="/visitUser" method="post">
			<input type="hidden" name="userView" id="userView" value="<%= session.getAttribute("username") %>">
			<input class="buttonOverride01 u-pull-max-width" type="submit" value="<%= session.getAttribute("username") %>">
			</form>
		</div>
		<div class="three columns">
			<form action="/timeline" method="post">
			<input type="hidden" name="userView" id="userView" value="<%= session.getAttribute("username") %>">
			<input class="buttonOverride01 u-pull-max-width" type="submit" value="Your Timeline">
			</form>
		</div>
		<div class="three columns">
			<form action="/logout" method="post">
				<input class="buttonOverride01 u-pull-max-width" type="submit" value="Sign Out"/>
			</form>
		</div>
	</div>
</div>
<div class="container">
	<div class="row">
		<div class = "twelve columns">
			<h1><%= session.getAttribute("searchResults") %> Search Result/s</h1>
			<% List<String> crumbleWall = new ArrayList<String>();
			crumbleWall = (ArrayList)session.getAttribute("foundUsers");
			
			if (crumbleWall.iterator().hasNext()){
				for(String posts : crumbleWall){
			%>
				<form action="/visitUser" method="post">
					<input type="hidden" name="userView" id="userView" value="<%= posts %>">
					<%= posts %>
					<input type="submit" value="Visit">
				</form>
			<%}} %>
		</div>
	</div>
</div>
</body>
</html>