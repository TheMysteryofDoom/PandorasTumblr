package com.example.demo;

import java.net.MalformedURLException;
import java.net.URL;

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
	
	public void attachPosts(HttpSession session, String user){
		session.setAttribute("posts", repository.findByOwner(user));
	}
	
	public String URLFinder(String postText) {
        //String s = args[0];
        // separate input by spaces ( URLs don't have spaces )
        String [] parts = postText.split("\\s+");
        StringBuilder finalOutput = new StringBuilder();
        // Attempt to convert each item into an URL.   
        for( String item : parts ) try {
            URL url = new URL(item);
            // If possible then replace with anchor...
            finalOutput.append("<a href=\"" + url + "\">"+ url + "</a> " );
            //System.out.print("<a href=\"" + url + "\">"+ url + "</a> " );    
        } catch (MalformedURLException e) {
            // If there was an URL that was not it!...
        	finalOutput.append(item + " " );
            //System.out.print( item + " " );
        }

        return finalOutput.toString();
    }

}
