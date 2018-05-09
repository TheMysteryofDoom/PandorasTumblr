package com.example.demo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CrumblrCommentRepository extends MongoRepository<CrumblrComment, String> {
	
	List<CrumblrComment> findByOnPostId(String onPostID);

}
