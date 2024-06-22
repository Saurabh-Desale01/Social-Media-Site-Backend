package com.example.Social.Media.Site.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.Social.Media.Site.model.Reels;
import com.example.Social.Media.Site.model.User;
import com.example.Social.Media.Site.service.ReelsService;
import com.example.Social.Media.Site.service.UserService;

@RestController
public class ReelsController {

	@Autowired
	private ReelsService reelsService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/reels")
	public Reels createReel(@RequestBody Reels reels, @RequestHeader("Authorization") String jwt) throws Exception {
		
		User reqUser = userService.findUserByJwt(jwt);
		
		Reels createdReels = reelsService.createReels(reels, reqUser);
		
		return createdReels;
	}
	
	@GetMapping("/api/reels")
	public List<Reels> findAllReels() throws Exception {
		
		return reelsService.findAllReels();
	}
	
	@GetMapping("/api/reels/user/{userId}")
	public List<Reels> findAllReels(@PathVariable Integer userId) throws Exception {
		
		return reelsService.findUsersReels(userId);
	}
}
