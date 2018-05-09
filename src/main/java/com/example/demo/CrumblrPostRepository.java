package com.example.demo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CrumblrPostRepository extends MongoRepository<CrumblrPost, String> {
	
	List<CrumblrPost> findByOwner(String owner);
	CrumblrPost findByContent(String Content);
}
