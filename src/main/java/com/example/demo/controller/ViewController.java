package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ViewController {

	@RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register() {
		
		//Test
		System.out.println("Help");
		
        return "index";
    }
}
