package com.example.Social.Media.Site.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Social.Media.Site.model.User;
import com.example.Social.Media.Site.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
//	@PostMapping("/users")
//	public User registerUser(@RequestBody User user) {
//		
//		return userService.registerUser(user);
//	}
	
	@GetMapping("/api/users")
	public List<User> findAllUser(){
		
		return userService.findAllUsers();
	}
	
	@GetMapping("/api/users/{userId}")
	public User findUserById(@PathVariable("userId") Integer id) throws Exception {
		
		return userService.findUserById(id);
	}
	
//	@GetMapping("/users/{email}")
//	public User findUserByMail(@PathVariable String email) {
//		 
//		return userService.findUserByMail(email);
//	}
	
	@PutMapping("/api/users/")
	public User updateUser(@RequestBody User user, @RequestHeader("Authorization") String jwt) throws Exception {
		
		User reqUser = userService.findUserByJwt(jwt);
		
		return userService.updateUser(user, reqUser.getId());
	}

	@PutMapping("/api/users/follow/{userId2}")
	public User followUser(@RequestHeader("Authorization") String jwt, @PathVariable Integer userId2) throws Exception {
		
		User reqUser = userService.findUserByJwt(jwt);
		
		return userService.followUser(reqUser.getId(), userId2);
	}
	
	@GetMapping("/api/users/search")
	public List<User> searchUser(@RequestParam("query") String query){
		return userService.searchUser(query);
	}
	
	@GetMapping("/api/users/profile")
	public User findUserByJwt(@RequestHeader("Authorization") String jwt) {
		
		User user = userService.findUserByJwt(jwt);
		user.setPassword(null);
		
		return user;
	}
}
