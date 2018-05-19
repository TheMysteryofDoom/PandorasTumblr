package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Authentication {
	
	@Autowired
	private CrumblrUserRepository repository;
	
	public Boolean passCheck(CrumblrUser me){
		CrumblrUser him = repository.findByUsername(me.getUsername());
		System.out.println(him.getPassword());
		if (me.getPassword().equals(him.getPassword())){
			return true; //Password Checked
		} else {
			return false; //Password Wrong
		}
	}
	
	public Boolean passCheck(CrumblrUser me,String email){
		CrumblrUser him = repository.findByEmail(email);
		System.out.println(him.getPassword());
		if (me.getPassword().equals(him.getPassword())){
			System.out.println("By Email");
			return true; //Password Checked
		} else {
			return false; //Password Wrong
		}
	}
	
	public CrumblrUser grabDetails(CrumblrUser me){
		return repository.findByUsername(me.getUsername());
	}

	public CrumblrUser grabDetailsByEmail(String email) {
		// TODO Auto-generated method stub
		return repository.findByEmail(email);
	}

}
