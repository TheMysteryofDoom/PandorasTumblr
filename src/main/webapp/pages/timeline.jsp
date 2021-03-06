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
<title>Crumbleboard | Crumblr</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link href='//fonts.googleapis.com/css?family=Raleway:400,300,600' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="../css/normalize.css">
<link rel="stylesheet" href="../css/skeleton.css">
<link rel="stylesheet" href="../css/custom.css">
<%@ include file="../javascript/SessionCheck2.jsp" %>
</head>
<body class ="deepOrange">
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
<div class ="container">
	<div class="row">
		<div class="twelve columns" align="center">
		<h1>Your Timeline</h1>
		<br>
		</div>
	</div>
 	<div class="row">
 		<div class="four columns whiteout postFrame"> <!-- Left Half of the Screen -->
 			
 			<p><%= session.getAttribute("currentView") %></p>
 			<p><%= session.getAttribute("name") %></p>
 			<% if (!(session.getAttribute("currentView").equals(session.getAttribute("username")))){ 
	 			if (session.getAttribute("following").toString().equals("false")){%>
		 			<form action="/follow" method="post">
			 			<input type="hidden" name="pageOwner" id="pageOwner" value="<%= session.getAttribute("currentView") %>">
			 			<input class="u-max-full-width" type="image" id="submit" src="../images/FollowButton.png">
		 			</form>
		 			<%}else{ %>
			 		<form action="/unfollow" method="post">
			 			<input type="hidden" name="pageOwner" id="pageOwner" value="<%= session.getAttribute("currentView") %>">
			 			<input class="u-max-full-width" type="image" id="submit" src="../images/FollowButtonFollowing.png">
		 			</form>
 			<%}}%>
 			<form action="/search" method="post">
				<input type="text" name="search" id="search" placeholder="Search Crumblr"/><input class="button" type="submit" value="Search"/>
			</form>
 		</div>
 		<div class="eight columns"> <!-- Right Half of the Screen -->
 			<% 
 			List<CrumblrPost> crumbleWall = new ArrayList<CrumblrPost>();
			crumbleWall = (ArrayList)session.getAttribute("posts");
			String content = "";
			String owner = "";
			String date = "";
			String image = "";
			Collections.reverse(crumbleWall);
			//int i = 0;
			if (crumbleWall.iterator().hasNext()){
			for(CrumblrPost posts : crumbleWall){
				//Post Loop;
				content = posts.getContent();
				owner = posts.getOwner();
				date = posts.getDate();
				try{
					image = posts.getImage();
				} catch (Exception e){
					image = "";
				}
 			%> <!-- Post Writer End -->
 			<div class="row postFrame whiteout"> <!-- Post Block -->
	 			<% if (!(image.equals(""))){ %>
	 				<img class="u-max-full-width" src="data:image/jpg;base64, <%=image%>" alt="[File Not Considered an Image]" />
	 			<% } %>
	 			<p><%= content %><br>
	 			Posted by <%= posts.getOwner() %> at <%= posts.getDate() %> UTC</p>
	 			<div class="row">
	 				<div class="six columns">
		 			<!-- Comment Button space -->
		 			<form action="/comment" method="post">
		 			<input type="hidden" name="postID" id="postID" value="<%= posts.getId() %>">
		 			<input class="button" type="submit" value="comment">
		 			</form>
		 			<!-- End of Comment Button space -->
		 			</div>
		 			<!-- Delete Button space -->
		 			<% if (posts.getOwner().equals(session.getAttribute("username").toString())){ %>
		 			<div class="six columns">
		 			<form action="/delete" method="post">
		 			<input type="hidden" name="postID" id="postID" value="<%= posts.getId() %>">
		 			<input class="button" type="submit" value="delete this post">
		 			</form>
		 			</div>
		 			<%} %>
		 			<!-- End of Delete Button space -->
		 		</div>
 			</div> <!-- Post Block -->
 			<br>
 			<%}} %> <!-- End of Post Loop -->
 		</div>
 	</div>
</div>

</body>
</html>