package com.example.Social.Media.Site.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Social.Media.Site.model.Story;
import com.example.Social.Media.Site.model.User;
import com.example.Social.Media.Site.repository.StoryRepository;
import com.example.Social.Media.Site.service.StoryService;
import com.example.Social.Media.Site.service.UserService;

@Service
public class StoryServiceImpl implements StoryService{

	@Autowired
	private StoryRepository storyRepository;
	
	@Autowired
	private UserService userService;
	@Override
	public Story createStory(Story story, Integer userId) throws Exception {
		
		Story createdStory = new Story();
		
		User user = userService.findUserById(userId);
		
		createdStory.setCaption(story.getCaption());
		createdStory.setImage(story.getImage());
		createdStory.setUser(story.getUser());
		createdStory.setCreatedAt(LocalDateTime.now());
		createdStory.setUser(user);
		
		return storyRepository.save(createdStory);
	}

	@Override
	public List<Story> findStoryByUserId(Integer userId) throws Exception {
		
		User user = userService.findUserById(userId);
		
		return storyRepository.findByUserId(userId);
	}

}
