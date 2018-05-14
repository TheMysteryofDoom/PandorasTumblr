package com.example.demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
		//List<CrumblrPost> postSet = repository.findByOwner(session.getAttribute("username").toString());
		//Collections.reverse(postSet);
		//session.setAttribute("posts", postSet);
		session.setAttribute("posts", repository.findByOwner(session.getAttribute("username").toString()));
	}
	
	public void attachPosts(HttpSession session, String user){
		session.setAttribute("posts", repository.findByOwner(user));
	}
	
	public void attachPostByID(HttpSession session, String postID){
		CrumblrPost commentTarget = repository.findById(postID).get();
		session.setAttribute("commentOn", commentTarget);
		//Problematic Area (Class cast exception)
	}
	
	public String URLFinder(String postText) {
        String [] parts = postText.split("\\s+");
        StringBuilder finalOutput = new StringBuilder();
        for( String item : parts ) try {
            URL url = new URL(item);
            // If possible then replace with anchor...
            finalOutput.append("<a href=\"" + url + "\">"+ url + "</a> " );
            //System.out.print("<a href=\"" + url + "\">"+ url + "</a> " );    
        } catch (MalformedURLException e) {
        	finalOutput.append(item + " " );
            //System.out.print( item + " " );
        }
        return finalOutput.toString();
    }
	
	public void delete(String id){
		repository.deleteById(id);
	}

}
