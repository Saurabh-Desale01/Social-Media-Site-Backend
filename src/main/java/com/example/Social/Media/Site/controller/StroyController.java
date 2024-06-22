package com.example.Social.Media.Site.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.Social.Media.Site.model.Story;
import com.example.Social.Media.Site.model.User;
import com.example.Social.Media.Site.service.StoryService;
import com.example.Social.Media.Site.service.UserService;

@RestController
public class StroyController {

	@Autowired
	private StoryService storyService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/story")
	public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt) throws Exception {
		
		User reqUser = userService.findUserByJwt(jwt);
		
		return storyService.createStory(story, reqUser.getId());
	}
	
	@GetMapping("/api/story/user/{userId}")
	public List<Story> findUserStory(@PathVariable Integer userId, @RequestHeader("Authorization") String jwt) throws Exception {
		
		//User reqUser = userService.findUserByJwt(jwt);
		
		return storyService.findStoryByUserId(userId);
	}
}
