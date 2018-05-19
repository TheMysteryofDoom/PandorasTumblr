package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CrumblrUserRepository extends MongoRepository<CrumblrUser, String> {
	
	CrumblrUser findByUsername(String username);
	CrumblrUser findByFirstName(String firstName);
	CrumblrUser findByEmail(String email);

}
