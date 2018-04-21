package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ViewController {
	
	
	@Autowired
	private Registration registration;
	
	//@RequestMapping(value = "register")
	@RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(@ModelAttribute("newCrumblrUser")CrumblrUser user) {
		
		
		//Test
		System.out.println("This is the View controller Servlet mapped to Register");
		System.out.println(user.getUsername());
		
		registration.Register(user);
		//Registration.Register(user);
		
        return "pages/crumbleboard.jsp";
    }
}
