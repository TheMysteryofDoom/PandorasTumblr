<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<div class="container">
	<div class="row">
		<div class = "twelve columns">
			<h1><%= session.getAttribute("searchResults") %> Search Result/s</h1>
			<% for (int i = 0;i<Integer.parseInt(session.getAttribute("searchResults").toString());i++){ %>
				<form action="/visitUser" method="post">
					<input type="hidden" name="userView" id="userView" value="<%= session.getAttribute("userSearch") %>">
					<%= session.getAttribute("userSearch") %>
					<input type="submit" value="Visit">
				</form>
			<% } %>
		</div>
	</div>
</div>
</body>
</html>