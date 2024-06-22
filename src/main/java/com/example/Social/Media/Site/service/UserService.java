package com.example.Social.Media.Site.service;

import java.util.List;

import com.example.Social.Media.Site.model.User;

public interface UserService {

	public User registerUser(User user);
	
	public List<User> findAllUsers();
	
	public User findUserById(Integer userId) throws Exception;
	
	//public User findUserByMail(String mail);
	
	public User followUser(Integer user1, Integer user2) throws Exception;
	
	public User updateUser(User user, Integer userId) throws Exception;
	
	public List<User> searchUser(String query);
	
	public User findUserByJwt(String jwt);
	
}
