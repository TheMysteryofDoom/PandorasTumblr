package com.example.demo;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostManagement {
	
	@Autowired
	private CrumblrPostRepository repository;
	
	public void savePost(CrumblrPost me){
		//System.out.println(me.getUsername());
		repository.save(me);
	}
	
	public void attachPosts(HttpSession session){
		session.setAttribute("posts", repository.findByOwner(session.getAttribute("username").toString()));
	}

}
