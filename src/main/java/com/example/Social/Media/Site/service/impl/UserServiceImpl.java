package com.example.Social.Media.Site.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Social.Media.Site.model.User;
import com.example.Social.Media.Site.repository.UserRepository;
import com.example.Social.Media.Site.security.JwtProvider;
import com.example.Social.Media.Site.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User registerUser(User user) {
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setGender(user.getGender());
		newUser.setPassword(user.getPassword());
		
		return userRepository.save(newUser);
	}
	
	@Override
	public List<User> findAllUsers() {
		
		return userRepository.findAll();
	}

	@Override
	public User findUserById(Integer userId) throws Exception {
		Optional<User> user = userRepository.findById(userId);
		
		if(user.isPresent()) {
			return user.get();
		}
		
		throw new Exception("User not exist with userId "+ userId);
	}

//	@Override
//	public User findUserByMail(String mail) {
//		User user =userRepository.findByEmail(mail);
//		return user;
//	}

	@Override
	public User followUser(Integer userId1, Integer userId2) throws Exception{
		
		User user1 = findUserById(userId1);
		User user2 = findUserById(userId2);
		
		user2.getFollowers().add(user1.getId());
		user1.getFollowings().add(user2.getId());
		
		userRepository.save(user1);
		userRepository.save(user2);
		
		return user1;
	}

	@Override
	public User updateUser(User user, Integer userId) throws Exception{
		
		Optional<User> user1 = userRepository.findById(userId);
		
		if(user1.isEmpty()) {
			throw new Exception("User not exist with id "+userId);
		}
		
		User oldUser = user1.get();
		
		if(user.getFirstName()!=null) {
			oldUser.setFirstName(user.getFirstName());
		}
		if(user.getLastName()!=null) {
			oldUser.setLastName(user.getLastName());
		}
		if(user.getEmail()!=null) {
			oldUser.setEmail(user.getEmail());
		}
		
		return userRepository.save(oldUser);
	}

	@Override
	public List<User> searchUser(String query) {

		return userRepository.searchUser(query);
	}

	@Override
	public User findUserByJwt(String jwt) {
		
		String email = JwtProvider.getEmailFromJwtToken(jwt);
		
		User user = userRepository.findByEmail(email);
		return user;
	}

}
