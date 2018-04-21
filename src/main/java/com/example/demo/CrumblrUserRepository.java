package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CrumblrUserRepository extends MongoRepository<CrumblrUser, String> {
	
	public CrumblrUser findByUsername(String username);

}
