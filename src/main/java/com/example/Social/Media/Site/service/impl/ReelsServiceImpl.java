package com.example.Social.Media.Site.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Social.Media.Site.model.Reels;
import com.example.Social.Media.Site.model.User;
import com.example.Social.Media.Site.repository.ReelsRepository;
import com.example.Social.Media.Site.service.ReelsService;
import com.example.Social.Media.Site.service.UserService;

@Service
public class ReelsServiceImpl implements ReelsService{

	@Autowired
	private ReelsRepository reelsRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Reels createReels(Reels reels, User user) throws Exception {
		
		Reels createReels = new Reels();
		
		//User user = userService.findUserById(userId);
		
		createReels.setTitle(reels.getTitle());
		createReels.setVideo(reels.getVideo());
		createReels.setUser(user);
		
		return reelsRepository.save(createReels);
	}

	@Override
	public List<Reels> findAllReels() {
		
		return reelsRepository.findAll();
	}

	@Override
	public List<Reels> findUsersReels(Integer userId) throws Exception {

		userService.findUserById(userId);
		return reelsRepository.findByUserId(userId);
	}

}
