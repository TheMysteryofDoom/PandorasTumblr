package com.example.demo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CrumblrMainController {
	
	@Autowired
	private PostManagement postManagement;
	
	@RequestMapping(value = "crumblrPost" ,method = RequestMethod.POST)
	public String postEntry(@ModelAttribute("CrumblrPost")CrumblrPost crumblrPost, @RequestParam("file") MultipartFile file, HttpServletRequest request, HttpSession session) throws IOException{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		//======================================
        crumblrPost.setDate(dtf.format(now));
        //======================================
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        crumblrPost.encodeFileToBase64Binary(file.getBytes());
		//========
		System.out.println(crumblrPost.getOwner());
		System.out.println(crumblrPost.getContent());
		//========
		postManagement.savePost(crumblrPost);
		postManagement.attachPosts(session);
		
		return "pages/crumbleboard.jsp";
	}

}
