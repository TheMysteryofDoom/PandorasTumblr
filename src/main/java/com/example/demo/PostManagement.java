package com.example.demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
	
	@Autowired
	private CrumblrFollowersRepository followingRepo;
	
	public void savePost(CrumblrPost me){
		repository.save(me);
	}
	
	public void attachPosts(HttpSession session){
		session.setAttribute("posts", repository.findByOwner(session.getAttribute("username").toString()));
	}
	
	public void attachPosts(HttpSession session, String user){
		session.setAttribute("posts", repository.findByOwner(user));
	}
	
	public void attachPostByID(HttpSession session, String postID){
		CrumblrPost commentTarget = repository.findById(postID).get();
		session.setAttribute("commentOn", commentTarget);
	}
	
	public String URLFinder(String postText) {
        String [] parts = postText.split("\\s+");
        StringBuilder finalOutput = new StringBuilder();
        for( String item : parts ) try {
            URL url = new URL(item);
            finalOutput.append("<a href=\"" + url + "\">"+ url + "</a> " );
        } catch (MalformedURLException e) {
        	finalOutput.append(item + " " );
        }
        return finalOutput.toString();
    }
	
	public void delete(String id){
		repository.deleteById(id);
	}
	
	public void attachFollowedUserPosts(HttpSession session){
		
		CrumblrFollowers following = followingRepo.findByUsername(session.getAttribute("username").toString());
		
		List<CrumblrPost> everything = repository.findAll();
		List<CrumblrPost> finale = new ArrayList<CrumblrPost>();
		if (following.getFollowers().iterator().hasNext() && everything.iterator().hasNext()){
			for (CrumblrPost post: everything){
				if (following.getFollowers().contains(post.getOwner())){
					finale.add(post);
				}
			}
		}
		session.setAttribute("posts",finale);
	}

}
