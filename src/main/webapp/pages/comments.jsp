<%@page import="java.util.Collections"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<%@ page import ="com.example.demo.CrumblrPost"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Comments | Crumblr</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link href='//fonts.googleapis.com/css?family=Raleway:400,300,600' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="../css/normalize.css">
<link rel="stylesheet" href="../css/skeleton.css">
<link rel="stylesheet" href="../css/custom.css">
<%@ include file="../javascript/SessionCheck2.jsp" %>
</head>
<body class ="deepOrange">
<div class="container deepOrange">
	<form action="/visitUser" method="post">
	<input type="hidden" name="userView" id="userView" value="<%= session.getAttribute("username") %>">
	<input class="buttonOverride01" type="submit" value="<%= session.getAttribute("username") %>">
	</form>
</div>
<div class ="container">
	<div class="row">
		<div class="twelve columns" align="center">
		<h1><%= session.getAttribute("currentView") %>'s Crumblr</h1>
		<br>
		</div>
	</div>
 	<div class="row">
 		<div class="four columns whiteout postFrame"> <!-- Left Half of the Screen -->
 			
 			<p><%= session.getAttribute("currentView") %></p>
 			<p>Description<p>
 			<form action="/search" method="post">
				<input type="text" name="search" id="search" placeholder="Search Crumblr"/><input class="button" type="submit" value="Search"/>
			</form>
 			<form action="/logout" method="post">
				<input class="button" type="submit" value="Sign Out"/>
			</form>
 		</div>
 		<div class="eight columns"> <!-- Right Half of the Screen -->
 			<!-- Post is Written Here -->
 			<% 
 			//List<CrumblrPost> crumbleWall = new ArrayList<CrumblrPost>();
 			CrumblrPost commentTarget = (CrumblrPost)session.getAttribute("commentOn");
			//crumbleWall = (ArrayList)session.getAttribute("posts");
			String postID = commentTarget.getId();
			String content = commentTarget.getContent();
			String owner = commentTarget.getOwner();;
			String date = commentTarget.getDate();
			String image = "";
				try{
					image = commentTarget.getImage();
				} catch (Exception e){
					image = "";
				}
 			%> <!-- Post Writer End -->
 			<div class="row postFrame whiteout"> <!-- Post Block -->
 			<% if (!(image.equals(""))){ %>
 				<img class="u-max-full-width" src="data:image/jpg;base64, <%=image%>" alt="[File Not Considered an Image]" />
 			<% } %>
 			<p><%= content %><br>
 			Posted by <%= owner %> at <%= date %> UTC</p>
 			<!-- Delete Button space -->
 			<% if (owner.equals(session.getAttribute("username").toString())){ %>
 			<form action="/delete" method="post">
 			<input type="hidden" name="postID" id="postID" value="<%= postID %>">
 			<input class="button" type="submit" value="delete this post">
 			</form>
 			<%} %>
 			<!-- End of Delete Button space -->
 			</div> <!-- Post Block -->
 			<br>
 			<% %> <!-- End of Post Loop -->
 			<!-- Comment Area -->
 			<div class="row whiteout postFrame">
	 			<!-- Display's if on own blog -->
	 			<h5>Comment:</h5>
	 			<form action ="/comment02" modelAttribute="CrumblrComment" method="post">
	 				<input type="hidden" name="onPostId" id="onPostId" value="<%= postID %>">
	 				<input type="hidden" id ="writer" name="writer" value="<%= session.getAttribute("username") %>">
	 				<input class="u-max-full-width" type="text" name="text" id="text">
	 				<input class="buttonOverride01" type="submit" value="Comment">
	 			</form>
 			</div>
 			<!-- End of Comment Area -->
 		</div>
 	</div>
</div>

</body>
</html>