package com.example.demo;

import org.springframework.data.annotation.Id;

public class CrumblrComment {
	
	@Id
	public String id;
	
	public String onPostId;
	public String writer;
	public String text;
	
	public CrumblrComment(){
		
	}
	
	@Override
	public String toString() {
        return String.format(
                "CrumblrComment[id=%s, onPostId='%s', writer='%s', text='%s']",
                id, onPostId, writer, text);
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOnPostId() {
		return onPostId;
	}

	public void setOnPostId(String onPostId) {
		this.onPostId = onPostId;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	
}
