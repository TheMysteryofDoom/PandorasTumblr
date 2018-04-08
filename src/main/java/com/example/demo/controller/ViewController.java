package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.demo.model.CrumblrUser;


@Controller
public class ViewController {
	
	//@RequestMapping(value = "register")
	@RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(@ModelAttribute("newCrumblrUser")CrumblrUser user) {
		
		//Test
		System.out.println("This is the View controller Servlet mapped to Register");
		System.out.println(user.getUsername());
		
        return "pages/index.html";
        //Done: Where does the return lead to.
        //To Do: Why is Post not supported.
    }
}
