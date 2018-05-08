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
<div class ="container">
	<div class="row">
		<div class="twelve columns">
		<h1><%= session.getAttribute("currentView") %>'s Crumblr</h1>
		</div>
	</div>
 	<div class="row">
 		<div class="four columns"> <!-- Left Half of the Screen -->
 			
 			<p><%= session.getAttribute("currentView") %></p>
 			<p>Description<p>
 			<form action="/logout" method="post">
				<input type="submit" value="Sign Out"/>
			</form>
 		</div>
 		<div class="eight columns"> <!-- Right Half of the Screen -->
 			<% if (session.getAttribute("currentView").equals(session.getAttribute("username"))){ %>
 			<!-- Display's if on own blog -->
 			<form action ="/crumblrPost" modelAttribute="CrumblrPost" enctype="multipart/form-data" method="post">
 				<input type="hidden" id ="owner" name="owner" value="<%= session.getAttribute("username") %>">
 				<input type="file" id="file" name="file">
 				<input type="text" name="content" id="content"> 
 				<input type="submit" value="Post">
 			</form>
 			<hr>
 			<%} %>
 			<p>Post Spaces</p> <!-- Posts Are Written Here -->
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
				//i++;
				content = posts.getContent();
				owner = posts.getOwner();
				date = posts.getDate();
				try{
					image = posts.getImage();
				} catch (Exception e){
					image = "";
				}
 			%> <!-- Post Writer End -->
 			<% if (!(image.equals(""))){ %>
 				<img class="u-max-full-width" src="data:image/jpg;base64, <%=image%>" alt="Picture" />
 			<% } %>
 			<p><%= content %><br>
 			Posted at <%= posts.getDate() %></p>
 			<%}} %> <!-- End of Post Loop -->
 			<p>EndofLine</p>
 		</div>
 	</div>
</div>

</body>
</html>