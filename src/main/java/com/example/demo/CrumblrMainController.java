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
	
	@Autowired
	private CrumblrCommentRepository comsRepository;
	
	@Autowired 
	private CrumblrFollowersRepository followRepository;
	
	@RequestMapping(value = "crumblrPost" ,method = RequestMethod.POST)
	public String postEntry(@ModelAttribute("CrumblrPost")CrumblrPost crumblrPost, @RequestParam("file") MultipartFile file, HttpServletRequest request, HttpSession session) throws IOException{
		DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime( FormatStyle.MEDIUM )
                .withLocale( Locale.UK )
                .withZone( ZoneId.of("UTC") );
		Instant instant = Instant.now();
        crumblrPost.setDate(dtf.format(instant));
        crumblrPost.setContent(postManagement.URLFinder(crumblrPost.getContent()));
        crumblrPost.encodeFileToBase64Binary(file.getBytes());
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
		postManagement.attachPosts(session, user);
		CrumblrFollowers checker = followRepository.findByUsername(session.getAttribute("username").toString());
		if(checker.getFollowers().contains(user)){
			System.out.println("Totes Following");
			session.setAttribute("following", "true");
		} else {
			System.out.println("Not Following");
			session.setAttribute("following", "false");
		}
		
		return "pages/crumbleboard.jsp";
		
	}
	
	@RequestMapping(value="delete",method = RequestMethod.POST)
	public String deletePost(@RequestParam("postID") String postID, HttpSession session){
		postManagement.delete(postID);
		postManagement.attachPosts(session, session.getAttribute("username").toString());
		return "pages/crumbleboard.jsp";
	}
	
	@RequestMapping(value="comment",method = RequestMethod.POST)
	public String goToCommentsPage(@RequestParam("postID") String postID, HttpSession session){
		postManagement.attachPostByID(session, postID);
		session.setAttribute("comments", comsRepository.findByOnPostId(postID));
		return "pages/comments.jsp";
	}
	
	@RequestMapping(value="comment02",method = RequestMethod.POST)
	public String leaveComment(@ModelAttribute("CrumblrComment")CrumblrComment crumblrComment, HttpSession session){
		comsRepository.save(crumblrComment);
		session.setAttribute("comments", comsRepository.findByOnPostId(crumblrComment.getOnPostId()));
		return "pages/comments.jsp";
	}
	
	@RequestMapping(value="follow",method = RequestMethod.POST)
	public String follow(@RequestParam("pageOwner")String pageOwner, HttpSession session){
		CrumblrFollowers myset = null;
		try{
		myset = followRepository.findByUsername(session.getAttribute("username").toString());
		} catch (Exception e){
			System.out.println("We went here...");
			myset = new CrumblrFollowers();
			myset.setUsername(session.getAttribute("username").toString());
		}
		
		System.out.println("Now Follwoing: "+ pageOwner);
		myset.addFollower(pageOwner);
		session.setAttribute("following", "true");
		postManagement.attachPosts(session, session.getAttribute("currentView").toString());
		followRepository.save(myset);
		return "pages/crumbleboard.jsp";
	}
	
	@RequestMapping(value="unfollow",method = RequestMethod.POST)
	public String unfollow(@RequestParam("pageOwner")String pageOwner,HttpSession session){
		CrumblrFollowers myset;
		try{
		myset = followRepository.findByUsername(session.getAttribute("username").toString());
		} catch (Exception e){
			myset = new CrumblrFollowers();
			myset.setUsername(session.getAttribute("username").toString());
		}
		
		
		myset.removeFollower(pageOwner);
		session.setAttribute("following", "false");
		postManagement.attachPosts(session, session.getAttribute("currentView").toString());
		followRepository.save(myset);
		return "pages/crumbleboard.jsp";
	}

}
