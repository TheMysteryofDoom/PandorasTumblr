package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CrumblrFollowersRepository extends MongoRepository<CrumblrFollowers, String>{
	
	CrumblrFollowers findByUsername(String username);

}
