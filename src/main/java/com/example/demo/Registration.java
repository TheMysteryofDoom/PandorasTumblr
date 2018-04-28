package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class Registration {
	//Place @Service on all business logic
	//Place @Autowired on Bean Declaration
	
	@Autowired
	private CrumblrUserRepository repository;
	
	public void Register(CrumblrUser me){
		//System.out.println(me.getUsername());
		repository.save(me);
	}
}
