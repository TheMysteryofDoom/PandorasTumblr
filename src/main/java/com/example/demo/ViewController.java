package com.example.demo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {
	
	@Autowired
	private Authentication authentication;
	
	@Autowired
	private Registration registration;
	
	@Autowired
	private CrumblrPostRepository repository;
	
	//@RequestMapping(value = "register")
	@RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(@ModelAttribute("newCrumblrUser")CrumblrUser user) {
		
		System.out.println("This is the View controller Servlet mapped to Register");
		registration.Register(user);
        return "pages/home.jsp";
    }
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@ModelAttribute("newCrumblrUser")CrumblrUser user, HttpServletRequest request, HttpSession session) {
		//===================
		session.invalidate();
		//===================
		System.out.println("This is the View controller Servlet mapped to Login");
		Boolean correctPass = authentication.passCheck(user);
		
		if (correctPass == true){
			user = authentication.grabDetails(user);
			//========================
			HttpSession newSession = request.getSession();
			session = newSession;
			session.setAttribute("username", user.getUsername());
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
			return "pages/home.jsp";
		}
    }
	
	@RequestMapping(value = "logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request, HttpSession session){
		session.invalidate();
		System.out.println("Logged out");
		return "pages/home.jsp";
	}
}
