package com.example.demo;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;

public class CrumblrFollowers {
	
	@Id
	public String id;
	
	public String username;
	
	public ArrayList<String> following;

	public CrumblrFollowers() {
	}
	
	public CrumblrFollowers(String user) {
		this.username = user;
	}
	
	public void addFollower(String input){
		try {
		following.add(input);
		} catch (NullPointerException h){
			following = new ArrayList<String>();
			following.add(input);
		}
		
	}
	
	public void removeFollower(String input){
		following.remove(input);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ArrayList<String> getFollowers() {
		return following;
	}

	public void setFollowers(ArrayList<String> followers) {
		this.following = followers;
	}
	

}
