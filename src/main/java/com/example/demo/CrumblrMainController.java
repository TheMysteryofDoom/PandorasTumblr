package com.example.demo;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CrumblrMainController {
	
	@Autowired
	private PostManagement postManagement;
	
	@Autowired
	private CrumblrUserRepository repository;
	
	@RequestMapping(value = "crumblrPost" ,method = RequestMethod.POST)
	public String postEntry(@ModelAttribute("CrumblrPost")CrumblrPost crumblrPost, @RequestParam("file") MultipartFile file, HttpServletRequest request, HttpSession session) throws IOException{
		//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime( FormatStyle.MEDIUM )
                .withLocale( Locale.UK )
                .withZone( ZoneId.of("UTC") );
		//LocalDateTime now = LocalDateTime.now();
		Instant instant = Instant.now();
		//======================================
        crumblrPost.setDate(dtf.format(instant));
        crumblrPost.setContent(postManagement.URLFinder(crumblrPost.getContent()));
        //======================================
        //System.out.println(file.getOriginalFilename());
        //System.out.println(file.getSize());
        crumblrPost.encodeFileToBase64Binary(file.getBytes());
		//========
		//System.out.println(crumblrPost.getOwner());
		//System.out.println(crumblrPost.getContent());
		//========
		postManagement.savePost(crumblrPost);
		postManagement.attachPosts(session);

		return "pages/crumbleboard.jsp";
	}
	
	@RequestMapping(value = "search" ,method = RequestMethod.POST)
	public String searchEverything(@RequestParam("search") String search, HttpSession session){
		CrumblrUser searched;
		System.out.println(search);
		int searchResults = 0; //Search Results Counter
		try{
			searched = repository.findByUsername(search);
			session.setAttribute("userSearch", searched.getUsername());
			searchResults++;
		} catch (Exception s){
			System.out.println("Username Not Found");
		}
		
		session.setAttribute("searchResults", searchResults);
		
		return "pages/searchresults.jsp";
		
	}
	
	@RequestMapping(value = "visitUser" ,method = RequestMethod.POST)
	public String visit(@RequestParam("userView") String user, HttpSession session){
		
		session.setAttribute("currentView", user);
		//========================
		//=====This code block is for Post Writing======
		//System.out.println("Let's get the data");
		//List<CrumblrPost> posts = repository.findByOwner(user.getUsername());
		postManagement.attachPosts(session, user);
		//System.out.println("We found the data");;
		
		return "pages/crumbleboard.jsp";
		
	}
	
	@RequestMapping(value="delete",method = RequestMethod.POST)
	public String deletePost(@RequestParam("postID") String postID, HttpSession session){
		postManagement.delete(postID);
		postManagement.attachPosts(session, session.getAttribute("username").toString());
		return "pages/crumbleboard.jsp";
	}

}
