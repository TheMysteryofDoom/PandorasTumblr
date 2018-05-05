package com.example.demo;

import java.io.IOException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.data.annotation.Id;

public class CrumblrPost {
	
	@Id
	public String id;

	public String owner;
	public String content;
	public String date;
	public String image;
	
	public CrumblrPost() {
	}

	@Override
	public String toString() {
        return String.format(
                "CrumblrPost[id=%s, content='%s', image='%s', date='%s']",
                id, content, image, date);
    }
	
	public void encodeFileToBase64Binary(byte[] bytes){
        String encodedfile = null;
        try {
            encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        image = encodedfile;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
