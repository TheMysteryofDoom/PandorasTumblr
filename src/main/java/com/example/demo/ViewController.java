package com.example.demo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {
	
	@Autowired
	private Authentication authentication;
	
	@Autowired
	private Registration registration;
	
	@Autowired
	private CrumblrPostRepository repository;
	
	@Autowired 
	private CrumblrFollowersRepository followRepository;
	
	//@RequestMapping(value = "register")
	@RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(@ModelAttribute("newCrumblrUser")CrumblrUser user) {
		
		System.out.println("This is the View controller Servlet mapped to Register");
		registration.Register(user);
		CrumblrFollowers myFollowers = new CrumblrFollowers(user.getUsername());
		followRepository.save(myFollowers);
        return "index.jsp";
    }
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@ModelAttribute("newCrumblrUser")CrumblrUser user,@RequestParam("username")String email, HttpServletRequest request, HttpSession session) {
		//===================
		session.invalidate();
		//===================
		Boolean correctPass = false;
		System.out.println("This is the View controller Servlet mapped to Login");
		if (email.contains("@")){
			correctPass = authentication.passCheck(user,email);
			//user = authentication.
		} else {
			correctPass = authentication.passCheck(user);
		}
		//Boolean correctPass = authentication.passCheck(user);
		
		if (correctPass == true){
			if (email.contains("@")){
				user = authentication.grabDetailsByEmail(email);
			} else {
				user = authentication.grabDetails(user);
			}
			//========================
			HttpSession newSession = request.getSession();
			session = newSession;
			session.setAttribute("username", user.getUsername());
			session.setAttribute("currentView", user.getUsername());
			session.setAttribute("name", user.getFirstName());
			//========================
			System.out.println("This session belongs to "+session.getAttribute("username").toString());
			//=====This code block is for Post Writing======
			System.out.println("Let's get the data");
			List<CrumblrPost> posts = repository.findByOwner(user.getUsername());
			session.setAttribute("posts", posts);
			System.out.println("We found the data");
			//System.out.println(posts.get(0).getContent());
			
			//==============================================
			return "pages/crumbleboard.jsp";
		} else {
			return "index.jsp";
		}
    }
	
	@RequestMapping(value = "logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request, HttpSession session){
		session.invalidate();
		System.out.println("Logged out");
		return "index.jsp";
	}
	
	@RequestMapping("/") 
    public String home(){
        return "index.jsp"; 
    }  
	
}
