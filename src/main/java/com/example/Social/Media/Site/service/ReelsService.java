package com.example.Social.Media.Site.service;

import java.util.List;

import com.example.Social.Media.Site.model.Reels;
import com.example.Social.Media.Site.model.User;

public interface ReelsService {

	public Reels createReels(Reels reels, User user) throws Exception;
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUsersReels(Integer userId) throws Exception;
}
