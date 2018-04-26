<% if(session.getAttribute("username") == null){
	 String site = new String("./home.jsp");
     response.setStatus(response.SC_MOVED_TEMPORARILY);
     response.setHeader("Location", site); 
	}
	%>