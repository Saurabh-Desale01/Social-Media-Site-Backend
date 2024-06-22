package com.example.Social.Media.Site.service;

import java.util.List;

import com.example.Social.Media.Site.model.Story;

public interface StoryService {

	public Story createStory(Story story, Integer userId) throws Exception;
	
	public List<Story> findStoryByUserId(Integer userId) throws Exception;
}
